package ru.itis.javalab.generatepdf.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import ru.itis.javalab.generatepdf.model.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 18.09.2020
 * DocumentServiceImpl
 *
 * @author Ruslan Pashin
 * github ttr843
 * @version v1.0
 */
public class DocumentServiceImpl implements DocumentService {

    @Override
    public void createDocument(String typeOfDocument, User user) {
        System.out.println("Start create document");
        Document document = new Document();
        String filename = typeOfDocument + "_" + user.getPassportID() + ".pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            document.addTitle(typeOfDocument);
            document.addSubject(typeOfDocument);
            addContentInDocument(document, typeOfDocument, user);
            System.out.println("Content added");
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            System.out.println("can`t create document");
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addContentInDocument(Document document, String typeOfDocument, User user) {
        System.out.println("Start add content");
        Anchor anchor = new Anchor(typeOfDocument);
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);
        catPart.add(new Paragraph("First name: " + user.getFirstName()));
        catPart.add(new Paragraph("Last name: " + user.getLastName()));
        catPart.add(new Paragraph("Age: " + user.getAge()));
        catPart.add(new Paragraph("Passport number: " + user.getPassportID()));
        catPart.add(new Paragraph("Data: " + user.getDate()));
        catPart.add(new Paragraph("I WANT " + typeOfDocument));
        try {
            document.add(catPart);
        } catch (DocumentException e) {
            System.out.println("can`t add content into document");
            throw new IllegalStateException(e);
        }
    }
}
