package com.example.simpleparser.controller;

import com.example.simpleparser.service.CSVUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CSVUploaderController {

    private final CSVUploaderService csvUploaderService;

    @Autowired
    public CSVUploaderController(CSVUploaderService csvUploaderService){
        this.csvUploaderService = csvUploaderService;
    }

    @PostMapping(path = "/uploadcsv", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private ResponseEntity<String> uploadCSV(@RequestPart FilePart filePart){
        csvUploaderService.uploadCSV(filePart);
        return ResponseEntity.ok("File berhasil diunggah!");
    }
}
