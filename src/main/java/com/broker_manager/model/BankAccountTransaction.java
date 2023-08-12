package com.broker_manager.model;

import com.broker_manager.model.enums.Operation;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bank_account_transaction")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "sender_bank_account_id")
    private int senderBankAccountId;

    @Column(name = "recipient_bank_account_id")
    private int recipientBankAccountId;

    @Column(name = "sender_brocker_id")
    private int senderBrockerId;

    @Enumerated
    private Operation operation;

    @Column(name = "transfer_amount")
    private double transferAmount;

    @Column(name = "when_done")
    private Date whenDone;

    @Column(name = "amount_stock")
    private int amountStock;

    @Column(name = "price_stock")
    private double priceStock;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;
}
