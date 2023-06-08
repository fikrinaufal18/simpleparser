package com.example.simpleparser.service;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

@Service
public interface CSVUploaderService {
    void uploadCSV(FilePart filePart);
}
