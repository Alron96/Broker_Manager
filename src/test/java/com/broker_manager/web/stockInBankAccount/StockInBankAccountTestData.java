package com.broker_manager.web.stockInBankAccount;

import com.broker_manager.model.StockInBankAccount;

import java.util.List;

import static com.broker_manager.web.bankAccount.BankAccountTestData.BANK_ACCOUNT_CONSULTING;
import static com.broker_manager.web.stok.StockTestData.STOCK_4;
import static com.broker_manager.web.stok.StockTestData.STOCK_7;

public class StockInBankAccountTestData {
    public static final int STOCK_IN_BANK_ACCOUNT_ID_1 = 1;
    public static final int STOCK_IN_BANK_ACCOUNT_ID_2 = 2;

    public static final StockInBankAccount STOCK_IN_BANK_ACCOUNT_1_CONSULTING = new StockInBankAccount(STOCK_IN_BANK_ACCOUNT_ID_1,
            10, STOCK_4, BANK_ACCOUNT_CONSULTING);
    public static final StockInBankAccount STOCK_IN_BANK_ACCOUNT_2_CONSULTING = new StockInBankAccount(STOCK_IN_BANK_ACCOUNT_ID_2,
            25, STOCK_7, BANK_ACCOUNT_CONSULTING);

    public static List<StockInBankAccount> STOCKS_IN_BANK_ACCOUNT_CONSULTING = List.of(STOCK_IN_BANK_ACCOUNT_1_CONSULTING,
            STOCK_IN_BANK_ACCOUNT_2_CONSULTING);
}
