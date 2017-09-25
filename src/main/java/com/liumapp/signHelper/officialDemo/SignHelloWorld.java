package com.liumapp.signHelper.officialDemo;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

/**
 * Created by liumapp on 9/25/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class SignHelloWorld {

    public static final String KEYSTORE = "/usr/local/tomcat/project/pfxSigner/data/liumapp.keystore";
    public static final char[] PASSWORD = "111111".toCharArray();
    public static final String SRC = "/usr/local/tomcat/project/pfxSigner/data/test.pdf";
    public static final String DEST = "/usr/local/tomcat/project/pfxSigner/data/output.pdf";
    public static final String IMG = "/usr/local/tomcat/project/pfxSigner/data/sign.png";

    public void sign1(String src, String name, String dest,
                      Certificate[] chain, PrivateKey pk,
                      String digestAlgorithm, String provider, CryptoStandard subfilter,
                      String reason, String location)
            throws GeneralSecurityException, IOException, DocumentException {
        // Creating the reader and the stamper
        PdfReader reader = new PdfReader(src);
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
        // Creating the appearance
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
//        appearance.setVisibleSignature(name);
        // Custom text and custom font
        appearance.setLayer2Text("This document was signed by Bruno Specimen");
        appearance.setLayer2Font(new Font(FontFamily.TIMES_ROMAN));
        // Creating the signature
        PrivateKeySignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, subfilter);
    }

    public void sign2(String src, String name, String dest,
                      Certificate[] chain, PrivateKey pk,
                      String digestAlgorithm, String provider, CryptoStandard subfilter,
                      String reason, String location)
            throws GeneralSecurityException, IOException, DocumentException {
        // Creating the reader and the stamper
        PdfReader reader = new PdfReader(src);
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
        // Creating the appearance
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
        appearance.setVisibleSignature(name);
        // Custom text, custom font, and right-to-left writing
        appearance.setLayer2Text("\u0644\u0648\u0631\u0627\u0646\u0633 \u0627\u0644\u0639\u0631\u0628");
        appearance.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
        appearance.setLayer2Font(new Font(BaseFont.createFont("C:/windows/fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12));
        // Creating the signature
        PrivateKeySignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, subfilter);
    }

    public void sign3(String src, String name, String dest,
                      Certificate[] chain, PrivateKey pk,
                      String digestAlgorithm, String provider, CryptoStandard subfilter,
                      String reason, String location)
            throws GeneralSecurityException, IOException, DocumentException {
        // Creating the reader and the stamper
        PdfReader reader = new PdfReader(src);
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
        // Creating the appearance
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
        appearance.setVisibleSignature(name);
        // Custom text and background image
        appearance.setLayer2Text("This document was signed by Bruno Specimen");
        appearance.setImage(Image.getInstance(IMG));
        appearance.setImageScale(1);
        // Creating the signature
        PrivateKeySignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, subfilter);
    }

    public void sign4(String src, String name, String dest,
                      Certificate[] chain, PrivateKey pk,
                      String digestAlgorithm, String provider, CryptoStandard subfilter,
                      String reason, String location)
            throws GeneralSecurityException, IOException, DocumentException {
        // Creating the reader and the stamper
        PdfReader reader = new PdfReader(src);
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
        // Creating the appearance
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
//        appearance.setVisibleSignature(name);
        // Default text and scaled background image
        appearance.setImage(Image.getInstance(IMG));
        appearance.setImageScale(-1);
        // Creating the signature
        PrivateKeySignature pks = new PrivateKeySignature(pk, digestAlgorithm, provider);
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(appearance, digest, pks, chain, null, null, null, 0, subfilter);
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException, DocumentException {
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(KEYSTORE), PASSWORD);
        String alias = (String)ks.aliases().nextElement();
        PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
        Certificate[] chain = ks.getCertificateChain(alias);
        SignHelloWorld app = new SignHelloWorld();
        app.sign1(SRC, "Signature1", String.format(DEST, 1), chain, pk,
                DigestAlgorithms.SHA256, provider.getName(), CryptoStandard.CMS,
                "Custom appearance example", "Ghent");
        app.sign2(SRC, "Signature1", String.format(DEST, 2), chain, pk,
                DigestAlgorithms.SHA256, provider.getName(), CryptoStandard.CMS,
                "Custom appearance example", "Ghent");
        app.sign3(SRC, "Signature1", String.format(DEST, 3), chain, pk,
                DigestAlgorithms.SHA256, provider.getName(), CryptoStandard.CMS,
                "Custom appearance example", "Ghent");
        app.sign4(SRC, "Signature1", String.format(DEST, 4), chain, pk,
                DigestAlgorithms.SHA256, provider.getName(), CryptoStandard.CMS,
                "Custom appearance example", "Ghent");
    }

}
