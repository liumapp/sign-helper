package com.liumapp.signHelper.demo;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by liumapp on 9/27/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class DemoFromBook {

    private static final String cert_path = "/usr/local/tomcat/project/pfxSigner/data/liumapp.keystore";

    private static final String image_path = "/usr/local/tomcat/project/pfxSigner/data/sign.png";

    private static final String source_pdf = "/usr/local/tomcat/project/pfxSigner/data/test.pdf";

    private static final String output_pdf = "/usr/local/tomcat/project/pfxSigner/data/output.pdf";

    public static final char[] PASSWORD = "111111".toCharArray();//keystory密码

    public static void main(String[] args) throws Exception {

        SignPdfWithoutSignature();

    }

    public static void SignPdfWithoutSignature () throws Exception {

        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document , new FileOutputStream(output_pdf));
        document.open();
        document.add(new Paragraph("hello world"));
        PdfFormField pdfFormField = PdfFormField.createSignature(pdfWriter);
        pdfFormField.setWidget(new Rectangle(72 , 732 , 144 , 780) , PdfAnnotation.HIGHLIGHT_INVERT);
        pdfFormField.setFieldName("mySig");
        pdfFormField.setFlags(PdfAnnotation.FLAGS_PRINT);
        pdfFormField.setPage();
        pdfFormField.setMKBorderColor(BaseColor.BLACK);
        pdfFormField.setMKBackgroundColor(BaseColor.WHITE);
        PdfAppearance pdfAppearance = PdfAppearance.createAppearance(pdfWriter , 72 , 48);
        pdfAppearance.rectangle(0.5f , 0.5f , 71.5f , 47.5f);
        pdfAppearance.stroke();
        pdfFormField.setAppearance(PdfAnnotation.APPEARANCE_NORMAL , pdfAppearance);
        pdfWriter.addAnnotation(pdfFormField);
        document.close();

    }

}
