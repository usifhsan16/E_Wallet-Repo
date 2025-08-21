package com.example.E_Wallet.Service;

import jakarta.transaction.Transaction;

import java.util.UUID;

public interface GenericService <T> {

    void Delete(UUID id);



}
