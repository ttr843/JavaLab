package ru.itis.javalab.FakeInstagram.repository.interfaces;

import ru.itis.javalab.FakeInstagram.model.Document;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Long, Document> {
    List<Document> findAll();
}
