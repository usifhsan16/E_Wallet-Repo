package com.example.E_Wallet.Service;

import com.example.E_Wallet.DTO.WalletDTO;
import com.example.E_Wallet.Entity.Wallet;
import com.example.E_Wallet.Repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class WalletService implements GenericService<Wallet>{
    private final WalletRepository walletRepository;

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


    public Wallet Save(Wallet entity) {
          return walletRepository.save(entity);
    }
}
