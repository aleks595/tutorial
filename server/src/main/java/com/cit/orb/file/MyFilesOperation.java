package com.cit.orb.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MyFilesOperation {

    public String fileName;
    public String path = "/home/user/Desktop/";

    public MyFilesOperation(String fileName) {
        this.fileName = fileName;
    }

    public void createFile(String text) throws IOException {
        if (java.nio.file.Files.exists(Paths.get(path))) {
            if (!java.nio.file.Files.exists(Paths.get(path + fileName))) {
                Path testFile = java.nio.file.Files.createFile(Paths.get(path + fileName));
            }
            try (FileOutputStream fos = new FileOutputStream(path + fileName)) {
                byte[] buffer = text.getBytes();
                fos.write(buffer, 0, buffer.length);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public List<String> readeFile(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path+fileName));
        return lines;
    }
}




