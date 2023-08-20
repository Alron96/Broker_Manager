package com.broker_manager.model;

import com.broker_manager.model.enums.Department;
import com.broker_manager.model.enums.Role;
import com.broker_manager.util.validation.NoHtml;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false)
    @NotBlank(message = "Full name cannot be empty")
    @Size(max = 256)
    @NoHtml
    private String fullName;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is not correct")
    @Size(max = 128)
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    @NotBlank(message = "Phone number cannot be empty")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 128)
    private String password;

    @Column(name = "department", nullable = false)
    @Enumerated
    private Department department;

    @Column(name = "role", nullable = false)
    @Enumerated
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_bank_account",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_account_id")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<BankAccount> bankAccounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(fullName, user.fullName)
                && Objects.equals(email, user.email)
                && Objects.equals(phoneNumber, user.phoneNumber)
                && Objects.equals(department, user.department)
                && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, email, phoneNumber, department, role);
    }
}
