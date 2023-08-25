package com.broker_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "stock_in_bank_account")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockInBankAccount extends BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockInBankAccount stockInBankAccount = (StockInBankAccount) o;
        return Objects.equals(id, stockInBankAccount.id)
                && Objects.equals(amount, stockInBankAccount.amount)
                && Objects.equals(stock, stockInBankAccount.stock)
                && Objects.equals(bankAccount, stockInBankAccount.bankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, stock, bankAccount);
    }
}
