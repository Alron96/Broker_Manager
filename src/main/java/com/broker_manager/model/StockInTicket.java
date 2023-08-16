package com.broker_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "stock_in_ticket")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockInTicket {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "status", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @OneToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @OneToOne
    @JoinColumn(name = "bank_account_transaction_id")
    private BankAccountTransaction bankAccountTransaction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockInTicket stockInTicket = (StockInTicket) o;
        return Objects.equals(id, stockInTicket.id)
                && Objects.equals(amount, stockInTicket.amount)
                && Objects.equals(status, stockInTicket.status)
                && Objects.equals(stock, stockInTicket.stock)
                && Objects.equals(bankAccountTransaction, stockInTicket.bankAccountTransaction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, stock, bankAccountTransaction);
    }
}
