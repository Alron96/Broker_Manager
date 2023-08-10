package com.broker_manager.models;

import com.broker_manager.models.enums.Department;
import com.broker_manager.models.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Email cannot be empty")
    @Column(unique = true, updatable = false)
    @Email(message = "Email is not correct")
    private String email;

    @NotBlank(message = "PhoneNumber cannot be empty")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(length = 1000)
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Enumerated
    private Department department;

    @Enumerated
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_bank_account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_account_id")
    )
    @ToString.Exclude
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<BankAccountTransaction> bankAccountTransactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(fullName, user.fullName) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(password, user.password) && Objects.equals(department, user.department) && Objects.equals(role, user.role) && Objects.equals(bankAccounts, user.bankAccounts) && Objects.equals(tickets, user.tickets) && Objects.equals(bankAccountTransactions, user.bankAccountTransactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, phoneNumber, password, department, role, bankAccounts, tickets, bankAccountTransactions);
    }
}
