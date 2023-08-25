package com.broker_manager.service.stockInBankAccount;

import com.broker_manager.model.StockInBankAccount;
import com.broker_manager.repository.StockInBankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class StockInBankAccountService {

    private final StockInBankAccountRepository stockInBankAccountRepository;

    public StockInBankAccountService(StockInBankAccountRepository stockInBankAccountRepository) {
        this.stockInBankAccountRepository = stockInBankAccountRepository;
    }

    public StockInBankAccount create(StockInBankAccount stockInBankAccount) {
        return stockInBankAccountRepository.save(stockInBankAccount);
    }

    public StockInBankAccount update(StockInBankAccount stockInBankAccount) {
        return stockInBankAccountRepository.save(stockInBankAccount);
    }

    public void delete(StockInBankAccount stockInBankAccount) {
        stockInBankAccountRepository.delete(stockInBankAccount);
    }
}
