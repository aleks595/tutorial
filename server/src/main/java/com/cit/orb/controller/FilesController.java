package com.cit.orb.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.util.mime.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FilesController {

    /*@RequestMapping(path = "/upload/", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
    }

    @RequestMapping(path = "/uploadMultiPart/", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam("files") List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            byte[] bytes = file.getBytes();
        }
        return (new ResponseEntity<>("Successful", null, HttpStatus.OK));
    }
*/

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/{fileName:.+}", method = RequestMethod.GET)
    public void download(@PathVariable("fileName") String fileName, HttpServletResponse response)throws IOException {
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        Path file = Paths.get("/home/user/Desktop/", fileName);
        if (Files.exists(file)) {
            System.out.println(fileName);
            fileName = URLEncoder.encode(fileName, "UTF8").replace("+", "%20");
            response.setHeader("Content-disposition", "attachment; filename*=UTF-8''" +  fileName);
            response.setContentType(mediaType.getType());
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        }
    }

    /*HttpServletRequest request;
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadDir = "/uploads/";
        String realPath = request.getServletContext().getRealPath(uploadDir);
        File transferFile = new File(realPath + "/" + file.getOriginalFilename());
        file.transferTo(transferFile);
    }*/

/*
    @PostMapping("/upload")
    public void fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
       try (OutputStream out = new FileOutputStream(file.getOriginalFilename())) {
           IOUtils.copy(file.getInputStream(), out);
       }
       file.getInputStream().close();
    }

    @PostMapping("/upload")
    public void fileBuff(@RequestParam("file") MultipartFile file) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(file.getOriginalFilename())) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = file.getInputStream().read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            file.getInputStream().close();
        }
    }
*/
}


