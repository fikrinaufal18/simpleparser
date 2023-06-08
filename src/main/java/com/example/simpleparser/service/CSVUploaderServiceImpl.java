package com.example.simpleparser.service;

import com.example.simpleparser.entity.Investment;
import com.example.simpleparser.kafka.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CSVUploaderServiceImpl implements CSVUploaderService {

    private final KafkaProducer kafkaProducer;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public CSVUploaderServiceImpl(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    public void uploadCSV(FilePart filePart) {
        String SPLIT_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        filePart.content()
                .map(DataBuffer::asInputStream)
                .map(inputStream -> {
                    try {
                        return inputStream.readAllBytes();
                    } catch (IOException e){
                        throw new RuntimeException(e);
                    }
                })
                .map(String::new)
                .flatMapIterable(str -> {
                    List<String> strings = new ArrayList<>(Arrays.asList(str.split("\n")));
                    strings.remove(0);
                    return strings;
                })
                .map(row -> row.trim().split(SPLIT_REGEX))
                .doOnNext(columns -> {
                    if (columns.length == 10){
                        Investment investment = new Investment();
                        investment.setInvestor_name(columns[0]);
                        investment.setSid(columns[1]);
                        investment.setBook_price(columns[2].isEmpty() ? null : Integer.parseInt(columns[2]));
                        investment.setBook_qty(columns[3].isEmpty() ? null : Integer.parseInt(columns[3]));
                        investment.setBook_total(columns[4].isEmpty() ? null : Integer.parseInt(columns[4]));
                        investment.setOffering_price(columns[5].isEmpty() ? null : Integer.parseInt(columns[5]));
                        investment.setOffering_qty(columns[6].isEmpty() ? null : Integer.parseInt(columns[6]));
                        investment.setOffering_total(columns[7].isEmpty() ? null : Integer.parseInt(columns[7]));
                        investment.setAllotment_qty(columns[8].isEmpty() ? null : Integer.parseInt(columns[8]));
                        investment.setAllotment_total(columns[9].isEmpty() ? null : Integer.parseInt(columns[9]));

                        String[] stock_codes = {"BBCA", "TLKM", "BBRI"};
                        Random random = new Random();
                        String random_stock_code = stock_codes[random.nextInt(stock_codes.length)];
                        investment.setStockCode(random_stock_code);
                        String investmentString;
                        try {
                            investmentString = objectMapper.writeValueAsString(investment);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        kafkaProducer.sendMessage("investment_topic", investmentString);
                    }
                })
                .subscribe();
    }
}
