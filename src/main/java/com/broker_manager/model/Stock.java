package com.broker_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "price_buy")
    private double priceBuy;

    @Column(name = "price_sell")
    private double priceSell;

    @OneToMany(mappedBy = "stock")
    @ToString.Exclude
    private List<StockInTicket> ticketStocks;

    @OneToMany(mappedBy = "stock")
    private List<StockInBankAccount> stockInBankAccounts;

    @OneToOne(mappedBy = "stock")
    private BankAccountTransaction transaction;


}
