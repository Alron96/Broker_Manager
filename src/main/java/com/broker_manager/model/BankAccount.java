package com.broker_manager.model;

import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Type;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;


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
    @Enumerated
    private Department department;

    @Column(name = "type", nullable = false)
    @Enumerated
    private Type type;

    @ManyToMany(mappedBy = "bankAccounts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<User> users;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonManagedReference
    private List<StockInBankAccount> stockInBankAccounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Double.compare(that.balance, balance) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && department == that.department && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance, department, type);
    }
}
