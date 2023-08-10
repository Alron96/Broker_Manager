package com.broker_manager.models;

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
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "price_buy")
    private double priceBuy;

    @Column(name = "price_sell")
    private double priceSell;

    @OneToMany(mappedBy = "stock")
    @ToString.Exclude
    private List<StockInTicket> ticketStocks;

    @ManyToMany
    @JoinTable(name = "stock_bank_account",
            joinColumns = @JoinColumn(name = "stock_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_account_id"))
    @ToString.Exclude
    private List<BankAccount> bankAccounts;

    @OneToOne(mappedBy = "stock")
    private BankAccountTransaction transaction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Double.compare(stock.priceBuy, priceBuy) == 0 && Double.compare(stock.priceSell, priceSell) == 0 && Objects.equals(id, stock.id) && Objects.equals(companyName, stock.companyName) && Objects.equals(ticketStocks, stock.ticketStocks) && Objects.equals(bankAccounts, stock.bankAccounts) && Objects.equals(transaction, stock.transaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, priceBuy, priceSell, ticketStocks, bankAccounts, transaction);
    }
}
