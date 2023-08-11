package com.broker_manager.model;

import com.broker_manager.model.enums.Operation;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated
    private Operation operation;

    @Column(name = "brocker_id")
    private int brockerId;

    @Column(name = "chief_brocker_id")
    private int chiefBrockerId;

    private boolean status;

    @Column(name = "when_opened")
    private Date whenOpened;

    @Column(name = "when_closed")
    private Date whenClosed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ticket")
    @ToString.Exclude
    private List<StockInTicket> ticketStocks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return brockerId == ticket.brockerId && chiefBrockerId == ticket.chiefBrockerId && status == ticket.status && Objects.equals(id, ticket.id) && Objects.equals(operation, ticket.operation) && Objects.equals(whenOpened, ticket.whenOpened) && Objects.equals(whenClosed, ticket.whenClosed) && Objects.equals(user, ticket.user) && Objects.equals(ticketStocks, ticket.ticketStocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, operation, brockerId, chiefBrockerId, status, whenOpened, whenClosed, user, ticketStocks);
    }
}
