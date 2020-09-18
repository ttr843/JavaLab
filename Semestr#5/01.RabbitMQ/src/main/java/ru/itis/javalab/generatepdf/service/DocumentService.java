package ru.itis.javalab.generatepdf.service;

import com.itextpdf.text.Document;
import ru.itis.javalab.generatepdf.model.User;

/**
 * 18.09.2020
 * DocumentService
 *
 * @author Ruslan Pashin
 * github ttr843
 * @version v1.0
 */
public interface DocumentService {

    public void createDocument(String typeOfDocument, User user);

    public void addContentInDocument(Document document, String typeOfDocument, User user);
}
