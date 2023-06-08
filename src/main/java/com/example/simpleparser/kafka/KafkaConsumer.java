package com.example.simpleparser.kafka;

import com.example.simpleparser.entity.Investment;
import com.example.simpleparser.service.InvestmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final InvestmentService investmentService;

    @Autowired
    public KafkaConsumer(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }


    @KafkaListener(topics = "investment_topic", groupId = "investment-consumer-group")
    public void listen(String message) throws JsonProcessingException {
        Investment investment = objectMapper.readValue(message, Investment.class);
        System.out.println(investment);
        investmentService.saveDataIfNotExists(investment);
    }
}
