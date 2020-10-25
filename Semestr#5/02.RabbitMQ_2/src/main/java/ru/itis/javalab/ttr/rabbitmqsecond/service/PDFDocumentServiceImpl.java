package ru.itis.javalab.ttr.rabbitmqsecond.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import ru.itis.javalab.ttr.rabbitmqsecond.model.User;
import ru.itis.javalab.ttr.rabbitmqsecond.utils.FreeMarkerUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;


public class PDFDocumentServiceImpl implements DocumentService<Document> {

    @Override
    public void CreateDocument(User user) {
        Document document = new Document();
        String filename =  user.getPassportID() + ".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            addContent(document,user);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("can`t create document");
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addContent(Document document, User user) {
        String statement = "Statement";
        document.addTitle(statement);
        Map<String,Object> data = new HashMap<>();
        data.put("user",user);
        try {
            String out = FreeMarkerUtil.getContract("statement.ftl",data);
            Chapter catPart = new Chapter(new Paragraph(out), 1);
            document.add(catPart);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }


}
