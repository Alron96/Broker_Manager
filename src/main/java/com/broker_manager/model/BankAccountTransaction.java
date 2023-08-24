package com.broker_manager.model;

import com.broker_manager.model.enums.Operation;
import com.broker_manager.web.AuthorizedUser;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bank_account_transaction")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountTransaction extends BankAccount {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "operation", nullable = false)
    @Enumerated
    private Operation operation;

    @Column(name = "transfer_amount", nullable = false)
    private double transferAmount;

    @Column(name = "when_done", nullable = false)
    private LocalDateTime whenDone;

    @ManyToOne
    @JoinColumn(name = "sender_bank_account_id", nullable = false)
    private BankAccount senderBankAccountId;

    @ManyToOne
    @JoinColumn(name = "recipient_bank_account_id", nullable = false)
    private BankAccount recipientBankAccountId;

    @ManyToOne
    @JoinColumn(name = "sender_broker_id", nullable = false)
    private User senderBrokerId;

    @Column(name = "amount_stock")
    private int amountStock;

    @Column(name = "price_stock")
    private double priceStock;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Transient
    @NotNull
    private BankAccount senderBankAccount;

    @NotNull
    @Transient
    private BankAccount recipientBankAccount;

    @NotNull
    @Transient
    private Double amount;

    @Transient
    private LocalDateTime executionDate;

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    @Transient
    private AuthorizedUser authUser;
    public AuthorizedUser getAuthUser() {
        return authUser;
    }
}
