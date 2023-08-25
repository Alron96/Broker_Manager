package com.broker_manager.model;

import com.broker_manager.model.enums.Operation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BankAccountTransaction that = (BankAccountTransaction) o;
        return Double.compare(that.transferAmount, transferAmount) == 0 && amountStock == that.amountStock && Double.compare(that.priceStock, priceStock) == 0 && Objects.equals(id, that.id) && operation == that.operation && Objects.equals(whenDone, that.whenDone) && Objects.equals(senderBankAccountId, that.senderBankAccountId) && Objects.equals(recipientBankAccountId, that.recipientBankAccountId) && Objects.equals(senderBrokerId, that.senderBrokerId) && Objects.equals(stock, that.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, operation, transferAmount, whenDone, senderBankAccountId, recipientBankAccountId, senderBrokerId, amountStock, priceStock, stock);
    }
}
