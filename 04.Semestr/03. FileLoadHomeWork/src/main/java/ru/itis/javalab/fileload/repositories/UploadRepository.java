package ru.itis.javalab.fileload.repositories;

import ru.itis.javalab.fileload.models.Upload;

import java.util.Optional;

public interface UploadRepository extends CrudRepository<Long, Upload> {
    Optional<Upload> findByOriginalName(String name);
}
