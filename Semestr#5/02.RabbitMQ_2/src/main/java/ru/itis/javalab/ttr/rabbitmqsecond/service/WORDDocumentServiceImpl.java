package ru.itis.javalab.ttr.rabbitmqsecond.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.*;
import ru.itis.javalab.ttr.rabbitmqsecond.model.User;
import ru.itis.javalab.ttr.rabbitmqsecond.utils.FreeMarkerUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class WORDDocumentServiceImpl implements DocumentService<XWPFDocument> {
    @Override
    public void CreateDocument(User user) {
        XWPFDocument document = new XWPFDocument();
        String filename =  user.getPassportID() + ".docx";
        try(FileOutputStream out = new FileOutputStream(filename);
        ) {
            addContent(document,user);
            document.write(out);
            out.close();
            document.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void addContent(XWPFDocument document, User user) {
        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Statement");
        titleRun.setColor("000000");
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);
        XWPFParagraph subTitle = document.createParagraph();
        subTitle.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun subTitleRun = subTitle.createRun();
        Map<String,Object> data = new HashMap<>();
        data.put("user",user);
        try {
            String out = FreeMarkerUtil.getContract("statement.ftl",data);
            subTitleRun.setText(out);
            subTitleRun.setColor("000000");
            subTitleRun.setFontFamily("Courier");
            subTitleRun.setFontSize(16);
            subTitleRun.setTextPosition(20);
            subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
