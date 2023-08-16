package com.broker_manager.model;

import com.broker_manager.model.enums.Operation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "operation", nullable = false)
    @Enumerated
    private Operation operation;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "when_opened", nullable = false)
    private LocalDateTime whenOpened;

    @Column(name = "when_closed")
    private LocalDateTime whenClosed;

    @ManyToOne
    @JoinColumn(name = "broker_id", nullable = false)
    private User broker;

    @ManyToOne
    @JoinColumn(name = "chief_broker_id", nullable = false)
    private User chiefBroker;

    @OneToMany(mappedBy = "ticket")
    private List<StockInTicket> ticketStocks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id)
                && Objects.equals(operation, ticket.operation)
                && Objects.equals(status, ticket.status)
                && Objects.equals(whenOpened, ticket.whenOpened)
                && Objects.equals(whenClosed, ticket.whenClosed)
                && Objects.equals(ticketStocks, ticket.ticketStocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operation, whenOpened, whenClosed, broker, chiefBroker, ticketStocks);
    }
}
