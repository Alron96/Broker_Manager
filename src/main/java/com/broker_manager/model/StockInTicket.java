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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private int amount;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @OneToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;


}
