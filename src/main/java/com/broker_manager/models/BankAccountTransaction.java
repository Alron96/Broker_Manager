package com.broker_manager.models;

import com.broker_manager.models.enums.Operation;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;

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
    private Long id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccountTransaction that = (BankAccountTransaction) o;
        return senderBankAccountId == that.senderBankAccountId && recipientBankAccountId == that.recipientBankAccountId && senderBrockerId == that.senderBrockerId && Double.compare(that.transferAmount, transferAmount) == 0 && amountStock == that.amountStock && Double.compare(that.priceStock, priceStock) == 0 && Objects.equals(id, that.id) && Objects.equals(operation, that.operation) && Objects.equals(whenDone, that.whenDone) && Objects.equals(user, that.user) && Objects.equals(stock, that.stock) && Objects.equals(bankAccount, that.bankAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderBankAccountId, recipientBankAccountId, senderBrockerId, operation, transferAmount, whenDone, amountStock, priceStock, user, stock, bankAccount);
    }
}
