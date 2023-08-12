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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private double balance;

    private Department department;

    @Enumerated
    private Type type;

    @ManyToMany(mappedBy = "bankAccounts")
    @ToString.Exclude
    private List<User> users;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<StockInBankAccount> stockInBankAccounts;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<BankAccountTransaction> bankAccountTransactions;
}
