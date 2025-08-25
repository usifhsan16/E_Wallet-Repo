package com.example.E_Wallet.Repository;

import com.example.E_Wallet.Entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, UUID> {
}
