package com.example.E_Wallet.Service;

import com.example.E_Wallet.Entity.Transactions;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.ExceptionHandle.WrongDataException;
import com.example.E_Wallet.Repository.TransactionsRepository;
import com.example.E_Wallet.Repository.UserRepository;
import jakarta.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class TransactionsService implements GenericService<Transactions>{
    private final TransactionsRepository transactionsRepository;
    public Transactions FindById(UUID id) {
        Transactions transactions=transactionsRepository.findById(id).orElse(null);
        if(transactions==null){
            throw new WrongDataException("This transaction doesnt exist");
        }
        return transactions;
    }

    @Override
    public void Delete(UUID id) {
        Optional<Transactions> byId=transactionsRepository.findById(id);
        if (byId.isPresent()){
            transactionsRepository.delete(byId.get());
        }
        else{
            throw new WrongDataException("Invalid Id");
        }
    }


    public Transactions Save(Transactions entity) {
        if (entity.getTransactionId()!=null){
           throw new WrongDataException("transaction id already exist");
        }else {
            return transactionsRepository.save(entity);
        }
    }

}
