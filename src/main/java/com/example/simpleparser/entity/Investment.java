package com.example.simpleparser.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "investment", uniqueConstraints = @UniqueConstraint(columnNames = {"sid", "stockCode"}))
public class Investment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String investor_name;
    @Column
    private String sid;
    @Column
    private Integer book_price;
    @Column
    private Integer book_qty;
    @Column
    private Integer book_total;
    @Column
    private Integer offering_price;
    @Column
    private Integer offering_qty;
    @Column
    private Integer offering_total;
    @Column
    private Integer allotment_qty;
    @Column
    private Integer allotment_total;
    @Column
    private String stockCode;

    public Investment(){}

    public Investment(
            String investor_name,
            String sid,
            Integer book_price,
            Integer book_qty,
            Integer book_total,
            Integer offering_price,
            Integer offering_qty,
            Integer offering_total,
            Integer allotment_qty,
            Integer allotment_total,
            String stockCode) {
        this.investor_name = investor_name;
        this.sid = sid;
        this.book_price = book_price;
        this.book_qty = book_qty;
        this.book_total = book_total;
        this.offering_price = offering_price;
        this.offering_qty = offering_qty;
        this.offering_total = offering_total;
        this.allotment_qty = allotment_qty;
        this.allotment_total = allotment_total;
        this.stockCode = stockCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Integer getBook_price() {
        return book_price;
    }

    public void setBook_price(Integer book_price) {
        this.book_price = book_price;
    }

    public Integer getBook_qty() {
        return book_qty;
    }

    public void setBook_qty(Integer book_qty) {
        this.book_qty = book_qty;
    }

    public Integer getBook_total() {
        return book_total;
    }

    public void setBook_total(Integer book_total) {
        this.book_total = book_total;
    }

    public Integer getOffering_price() {
        return offering_price;
    }

    public void setOffering_price(Integer offering_price) {
        this.offering_price = offering_price;
    }

    public Integer getOffering_qty() {
        return offering_qty;
    }

    public void setOffering_qty(Integer offering_qty) {
        this.offering_qty = offering_qty;
    }

    public Integer getOffering_total() {
        return offering_total;
    }

    public void setOffering_total(Integer offering_total) {
        this.offering_total = offering_total;
    }

    public Integer getAllotment_qty() {
        return allotment_qty;
    }

    public void setAllotment_qty(Integer allotment_qty) {
        this.allotment_qty = allotment_qty;
    }

    public Integer getAllotment_total() {
        return allotment_total;
    }

    public void setAllotment_total(Integer allotment_total) {
        this.allotment_total = allotment_total;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", investor_name='" + investor_name + '\'' +
                ", sid='" + sid + '\'' +
                ", book_price=" + book_price +
                ", book_qty=" + book_qty +
                ", book_total=" + book_total +
                ", offering_price=" + offering_price +
                ", offering_qty=" + offering_qty +
                ", offering_total=" + offering_total +
                ", allotment_qty=" + allotment_qty +
                ", allotment_total=" + allotment_total +
                ", stock_code='" + stockCode + '\'' +
                '}';
    }
}
