package com.example.E_Wallet.Service;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.example.E_Wallet.DTO.UserDTO;
import com.example.E_Wallet.DTO.WalletDTO;
import com.example.E_Wallet.Entity.Transactions;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Entity.Wallet;
import com.example.E_Wallet.ExceptionHandle.WrongDataException;
import com.example.E_Wallet.Repository.UserRepository;
import com.example.E_Wallet.Repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements GenericService<User> {
    private final UserRepository userRepository;
    private final WalletService walletService;
    private final TransactionsService transactionsService;
    private final WalletRepository walletRepository;


    public UserDTO FindById(UUID id) {
        User user=userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new WrongDataException("User does not Exist in the system");
        }
        UserDTO dto= new UserDTO();
        dto=dto.toDTO(user);
        WalletDTO walletDTO= new WalletDTO();
        walletDTO=walletDTO.toDto(user.getUserWallet());
        dto.setUserWallet(walletDTO);
        return dto;
    }

    public User Save(User entity) {
        walletService.Save(entity.getUserWallet());
        if (entity.getUserId()!=null){
            throw new WrongDataException("Invalid User id");
        }
        else{
            return userRepository.save(entity);
        }
    }

    @Override
    @Transactional
    public void Delete(UUID id){
        User user=userRepository.findById(id).orElse(null);
        if(user==null){
            throw new WrongDataException("User not Found!!"+id);
        }
        else {
            userRepository.delete(user);
        }
    }


    public UserDTO WithdrawMoney(UUID id,double Amount){
        User entity=userRepository.findById(id).orElse(null);
        if (entity==null){
            throw new WrongDataException("This Id "+id+" does not exist");
        } else if (entity.getUserWallet().getWalletBalance()<Amount) {
            throw new WrongDataException("You have less than "+Amount+" in your account");
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
            throw new WrongDataException("Id doesnt exist in the system");
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

    public UserDTO TransferMoney(UUID withdrawerId,UUID depositerID,double Amount){
        if(withdrawerId==depositerID){
            throw new IllegalArgumentException("You cant transfer money to the same user");
        }
        UserDTO dto=Deposit(depositerID,Amount);
        return WithdrawMoney(withdrawerId,Amount);
    }

    public List<Transactions> GetUserTransactions(UUID id){
        User user=userRepository.findById(id).orElse(null);
        if (user==null){
            throw new WrongDataException("User does not exist");
        }
        else {
            return user.getUserTransactions();
        }
    }
}
