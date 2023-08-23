package com.broker_manager.model;

import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "bank_account")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "department", nullable = false)
    private Department department;

    @Column(name = "type", nullable = false)
    @Enumerated
    private Type type;

    @ManyToMany(mappedBy = "bankAccounts", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<User> users;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<StockInBankAccount> stockInBankAccounts;

    @Transient
    private StockInBankAccount stockInBankAccount;

    public boolean hasStockInBankAccount;
}
