package ru.itis.javalab.FakeInstagram.service.implementations;

import org.springframework.stereotype.Service;
import ru.itis.javalab.FakeInstagram.repository.interfaces.DocumentRepository;
import ru.itis.javalab.FakeInstagram.service.interfaces.FilesService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import lombok.SneakyThrows;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.FakeInstagram.model.Document;


@Service
public class FilesServiceImpl implements FilesService {

    private final static String FILES_PATH = "E:\\itis\\JAVA_ENTERPRISE\\Javalab\\Semestr#4\\00.Semestrovka(FakeInstagram)\\files";
    private final static String CONVERTED_FILES_PATH = "E:\\itis\\JAVA_ENTERPRISE\\Javalab\\Semestr#4\\00.Semestrovka(FakeInstagram)\\converted_files";

    @Autowired
    private DocumentRepository documentsRepository;

    @Override
    public void init() {
        try (Stream<Path> filesPaths = Files.walk(Paths.get(FILES_PATH))) {
            filesPaths.filter(filePath -> filePath.toFile().isFile()).forEach(
                    filePath -> {
                        File file = filePath.toFile();
                        Document document = null;
                        try {
                            document = Document.builder()
                                    .path(filePath.toString())
                                    .size(file.length())
                                    .type(Files.probeContentType(filePath))
                                    .build();
                        } catch (IOException e) {
                            throw new IllegalArgumentException(e);
                        }
                        documentsRepository.save(document);
                    }
            );
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Transactional
    @Override
    public void convert() {
        List<Document> documents = documentsRepository.findAll();

        for (Document document : documents) {
            String newFileName = CONVERTED_FILES_PATH + "/" + document.getFileName() + ".jpg";
            if (document.getType().equals("application/pdf")) {
                convertPdfToJpg(document, newFileName);
                document.setType("image/jpeg");
            }
        }
    }

    @SneakyThrows
    private void convertPdfToJpg(Document document, String newFileName) {
        PDDocument pdf = PDDocument.load(document.getSourceFile());
        PDFRenderer renderer = new PDFRenderer(pdf);
        BufferedImage image = renderer.renderImageWithDPI(0, 300, ImageType.RGB);
        ImageIOUtil.writeImage(image, newFileName, 300);
        pdf.close();
        File resultFile = new File(newFileName);
        document.setSourceFile(resultFile);
    }

}
