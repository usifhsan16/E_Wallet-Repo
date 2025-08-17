package com.example.E_Wallet.Service;

import com.example.E_Wallet.Entity.Wallet;
import com.example.E_Wallet.Repository.WalletRepository;

import java.util.Optional;
import java.util.UUID;

public class WalletService implements GenericService<Wallet>{
    WalletRepository walletRepository;
    @Override
    public Wallet FindById(UUID id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Override
    public void Delete(UUID id) {
        Optional<Wallet>byId=walletRepository.findById(id);
        if (byId.isPresent()){
            walletRepository.delete(byId.get());
        }
    }

    @Override
    public Wallet Save(Wallet entity) {
        if (entity.getWalletId()!=null){
            throw new IllegalArgumentException("Wallet Already Exist");
        }
        else{
            entity.setWalletBalance(0);
          return walletRepository.save(entity);
        }
    }
}
