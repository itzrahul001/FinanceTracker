package com.example.finance.repository;

import com.example.finance.model.Transaction;
import com.example.finance.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction ,Long> {
    List<Transaction> findByType(TransactionType type);
    List<Transaction> findByCategory_Name(String name);

}
