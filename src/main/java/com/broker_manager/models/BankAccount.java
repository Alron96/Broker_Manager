package com.broker_manager.models;

import com.broker_manager.models.enums.Department;
import com.broker_manager.models.enums.Type;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private double balance;

    private Department department;

    @Enumerated
    private Type type;

    @ManyToMany(mappedBy = "bankAccounts")
    @ToString.Exclude
    private List<User> users;

    @ManyToMany(mappedBy = "bankAccounts")
    @ToString.Exclude
    private List<Stock> stocks;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<BankAccountTransaction> bankAccountTransactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Double.compare(that.balance, balance) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(department, that.department) && Objects.equals(type, that.type) && Objects.equals(users, that.users) && Objects.equals(stocks, that.stocks) && Objects.equals(bankAccountTransactions, that.bankAccountTransactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, balance, department, type, users, stocks, bankAccountTransactions);
    }
}
