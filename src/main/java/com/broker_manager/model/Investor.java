package com.broker_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "amount_invested")
    private double amountInvested;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Investor investor = (Investor) o;
        return Double.compare(investor.amountInvested, amountInvested) == 0 && Objects.equals(id, investor.id) && Objects.equals(fullName, investor.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, amountInvested);
    }
}
