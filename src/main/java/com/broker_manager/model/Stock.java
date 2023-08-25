package com.broker_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "stock")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_name", nullable = false)
    @NotBlank(message = "Company name cannot be empty")
    @Size(max = 128)
    private String companyName;

    @Column(name = "price_buy", nullable = false)
    @NotBlank(message = "Buy price cannot be empty")
    private double priceBuy;

    @Column(name = "price_sell", nullable = false)
    @NotBlank(message = "Sell price cannot be empty")
    private double priceSell;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id)
                && Objects.equals(companyName, stock.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName);
    }


}
