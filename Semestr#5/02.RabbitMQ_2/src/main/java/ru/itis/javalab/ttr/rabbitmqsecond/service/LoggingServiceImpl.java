package ru.itis.javalab.ttr.rabbitmqsecond.service;

import ru.itis.javalab.ttr.rabbitmqsecond.model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.UUID;

public class LoggingServiceImpl implements LoggingService {
    @Override
    public File createTxt() {
        File file = new File(UUID.randomUUID().toString() + ".txt");
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write("Users data: ");
            writer.flush();
            return file;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void addData(File file, User user) {
        try(FileWriter writer = new FileWriter(file, true))
        {
            writer.append('\n');
            writer.append(user.toString());
            writer.flush();
        }
        catch(IOException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void addData(File file, String string) {
        try(FileWriter writer = new FileWriter(file, true))
        {
            writer.append('\n');
            writer.append(string);
            writer.flush();
        }
        catch(IOException e){
            throw new IllegalArgumentException(e);
        }
    }
}
