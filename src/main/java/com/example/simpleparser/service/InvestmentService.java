package com.example.simpleparser.service;

import com.example.simpleparser.entity.Investment;
import org.springframework.stereotype.Service;

@Service
public interface InvestmentService {
    void saveDataIfNotExists(Investment investment);
}
