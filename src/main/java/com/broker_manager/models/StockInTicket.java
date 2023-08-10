package com.broker_manager.models;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private int amount;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockInTicket that = (StockInTicket) o;
        return amount == that.amount && status == that.status && Objects.equals(id, that.id) && Objects.equals(ticket, that.ticket) && Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, status, ticket, stock);
    }
}
