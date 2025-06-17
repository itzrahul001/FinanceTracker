package com.example.finance.service;

import com.example.finance.model.Category;
import com.example.finance.model.Transaction;
import com.example.finance.model.TransactionType;
import com.example.finance.repository.CategoryRepository;
import com.example.finance.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FinanceService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void saveTransaction(Transaction txn) {
        // Ensure the Category is saved before saving the Transaction
        Category category = txn.getCategory();
        if (category != null && category.getId() == null) {
            categoryRepository.save(category);
        }
        transactionRepository.save(txn);
    }


    public List<Transaction> getAllTransaction(){
        return transactionRepository.findAll();
    }

    public double getTotalIncome(){
        return transactionRepository.findByType(TransactionType.INCOME).stream().mapToDouble(Transaction::getAmount).sum();

    }

    public  double getTotalExpense(){
        return transactionRepository.findByType(TransactionType.EXPENSE).stream().mapToDouble(Transaction::getAmount).sum();
    }
}
