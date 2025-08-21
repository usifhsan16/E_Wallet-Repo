package com.example.E_Wallet.Service;

import com.example.E_Wallet.Entity.Transactions;
import com.example.E_Wallet.Repository.TransactionsRepository;
import jakarta.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class TransactionsService implements GenericService<Transactions>{
    private final TransactionsRepository transactionsRepository;
    public Transactions FindById(UUID id) {
        return transactionsRepository.findById(id).orElse(null);
    }

    @Override
    public void Delete(UUID id) {
        Optional<Transactions> byId=transactionsRepository.findById(id);
        if (byId.isPresent()){
            transactionsRepository.delete(byId.get());
        }
        else{
            throw new IllegalArgumentException("Invalid Id");
        }
    }


    public Transactions Save(Transactions entity) {
        if (entity.getTransactionId()!=null){
           throw new IllegalArgumentException("Invalid id");
        }else {
            return transactionsRepository.save(entity);
        }
    }

    
}
