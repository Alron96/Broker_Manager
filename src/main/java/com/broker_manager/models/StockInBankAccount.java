package com.broker_manager.models;

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
public class StockInBankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private int amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockInBankAccount that = (StockInBankAccount) o;
        return amount == that.amount && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}
