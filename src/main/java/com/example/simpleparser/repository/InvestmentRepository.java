package com.example.simpleparser.repository;

import com.example.simpleparser.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
    Optional<Investment> findBySidAndStockCode(String sid, String stockCode);
}
