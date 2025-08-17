package com.example.E_Wallet.Service;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Entity.Wallet;
import com.example.E_Wallet.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements GenericService<User> {
    private UserRepository userRepository;
    WalletService walletService;

    @Override
    public User FindById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void Delete(UUID id){
        Optional<User> byId=userRepository.findById(id);
        if (byId.isPresent()){
            userRepository.delete(byId.get());
        }
        else {
            throw new IllegalArgumentException("User not Found!!"+id);
        }
    }

    @Override
    public User Save(User entity) {
        Wallet wallet=walletService.Save(entity.getUserWallet());
        if (entity.getUserId()!=null){
            throw new IllegalArgumentException("Invalid User id");
        }
        else{
            entity.setUserWallet(wallet);
            return userRepository.save(entity);
        }
    }
}
