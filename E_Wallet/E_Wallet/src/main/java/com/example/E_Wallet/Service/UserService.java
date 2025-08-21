package com.example.E_Wallet.Service;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.example.E_Wallet.DTO.UserDTO;
import com.example.E_Wallet.DTO.WalletDTO;
import com.example.E_Wallet.Entity.Transactions;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Entity.Wallet;
import com.example.E_Wallet.Repository.UserRepository;
import com.example.E_Wallet.Repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements GenericService<User> {
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final TransactionsService transactionsService;
    private final WalletRepository walletRepository;


    public User findUser(UUID id){
        return userRepository.findById(id).orElse(null);
    }

    public UserDTO FindById(UUID id) {
        User user=userRepository.findById(id).orElse(null);
        UserDTO dto= new UserDTO();
        dto=dto.toDTO(user);
        WalletDTO walletDTO= new WalletDTO();
        walletDTO=walletDTO.toDto(user.getUserWallet());
        dto.setUserWallet(walletDTO);
        return dto;
    }

    public User Save(UserDTO userDTO) {
        Wallet wallet=userDTO.getUserWallet().toEntity(userDTO.getUserWallet());
        walletService.Save(wallet);
        User entity= userDTO.toEntity(userDTO);
        entity.setUserWallet(wallet);
        if (entity.getUserId()!=null){
            throw new IllegalArgumentException("Invalid User id");
        }
        else{
            entity.setUserWallet(wallet);
            return userRepository.save(entity);
        }
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


    public UserDTO WithdrawMoney(UUID id,double Amount){
        User entity=userRepository.findById(id).orElse(null);
        if (entity==null){
            throw new IllegalArgumentException("Invalid Id");
        } else if (entity.getUserWallet().getWalletBalance()<Amount) {
            throw new IllegalArgumentException("You have less than "+Amount+" in your account");
        } else {
            //Creating Transaction
            Transactions transactions= new Transactions();
            transactions.setUser(entity);
            transactions.setTransactionAmount(Amount);
            transactions.setTransactionType("Withdraw");
            transactionsService.Save(transactions);
            entity.getUserTransactions().add(transactions);

            //Updating wallet
            Wallet wallet=entity.getUserWallet();
            wallet.setWalletBalance(wallet.getWalletBalance()-Amount);
            walletRepository.save(wallet);

            //Returning DTOs
            WalletDTO walletDTO=new WalletDTO();
            walletDTO=walletDTO.toDto(entity.getUserWallet());
            UserDTO userDTO=new UserDTO();
            userDTO=userDTO.toDTO(entity);
            userDTO.setUserWallet(walletDTO);
            return userDTO;
        }

    }

    public UserDTO Deposit(UUID id,double Amount){
        User entity= userRepository.findById(id).orElse(null);
        if(entity==null){
            throw new IllegalArgumentException("Invalid Id");
        }
        else{
            //Creating Transaction
            Transactions transaction= new Transactions();
            transaction.setUser(entity);
            transaction.setTransactionAmount(Amount);
            transaction.setTransactionType("Deposit");
            transactionsService.Save(transaction);
            entity.getUserTransactions().add(transaction);

            //Updating wallet
            Wallet wallet=entity.getUserWallet();
            wallet.setWalletBalance(wallet.getWalletBalance()+Amount);
            walletRepository.save(wallet);

            //Returning DTOs
            WalletDTO walletDTO=new WalletDTO();
            walletDTO=walletDTO.toDto(entity.getUserWallet());
            UserDTO dto=new UserDTO();
            dto=dto.toDTO(entity);
            dto.setUserWallet(walletDTO);
            return dto;
        }

    }
}
