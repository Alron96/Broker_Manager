package com.broker_manager.model;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "price_buy")
    private double priceBuy;

    @Column(name = "price_sell")
    private double priceSell;
}
