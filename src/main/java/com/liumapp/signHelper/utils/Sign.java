package com.liumapp.signHelper.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.stereotype.Component;

@Component
public class Sign{
	
	// 证书密码
	private static final String cert_pwd = "ctid@888";
	//利用keytool生成数字证书
	//keytool -genkey -alias ctidcert -keystore c:/证书名称.keystore -storepass "888" -keypass "888" -keyalg "RSA" -dname "CN=www.123.cn,OU=简称,O=公司名称,L=北京,ST=北京,C=中国"
	private static final String cert_path = "c:/sign.keystore";
	//"D:\\dev\\eclipse-jee-juno-SR2-win32\\eclipse\\workspace\\sign\\src\\machunlin_1.pfx";
	
	public static void main(String[] args) {
		try {
			sign();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}  
		System.out.println("done!!!");
	}
	
	public static void sign() throws Exception{

		KeyStore ks = KeyStore.getInstance("jks");
		ks.load(new FileInputStream(cert_path), cert_pwd.toCharArray());
		String alias = (String)ks.aliases().nextElement();
		PrivateKey key = (PrivateKey)ks.getKey(alias, cert_pwd.toCharArray());
		Certificate[] chain = ks.getCertificateChain(alias);
		PdfReader reader = new PdfReader("C:\\foot.pdf");
		FileOutputStream fout = new FileOutputStream("c:\\signed.pdf");
		PdfStamper stp = PdfStamper.createSignature(reader, fout, '\0');

		PdfSignatureAppearance sap = stp.getSignatureAppearance();
//		sap.setCrypto(key, chain, null, PdfSignatureAppearance.WINCER_SIGNED);
		sap.setReason("I'm the author");
		sap.setLocation("Ma");
		sap.setImage(Image.getInstance("c:/logo.png"));
		sap.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
		Rectangle r = new Rectangle(400, 650, 500,750);
		sap.setVisibleSignature(r, 1, null);
		stp.close();
	}

}
