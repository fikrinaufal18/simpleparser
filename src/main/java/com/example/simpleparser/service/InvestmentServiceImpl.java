package com.example.simpleparser.service;

import com.example.simpleparser.entity.Investment;
import com.example.simpleparser.repository.InvestmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService {
    private final InvestmentRepository investmentRepository;

    public InvestmentServiceImpl(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    @Override
    public void saveDataIfNotExists(Investment investment) {
        Optional<Investment> existingInvestment = investmentRepository.findBySidAndStockCode(investment.getSid(), investment.getStockCode());
        if (existingInvestment.isPresent()){
            System.out.println("data sudah ada");
        } else {
            investmentRepository.save(investment);
        }
    }
}
