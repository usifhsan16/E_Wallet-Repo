package com.example.E_Wallet.Service;

import com.example.E_Wallet.Entity.Transactions;
import com.example.E_Wallet.Entity.User;
import com.example.E_Wallet.Entity.Wallet;
import com.example.E_Wallet.Repository.UserRepository;
import com.example.E_Wallet.Repository.WalletRepository;
import jakarta.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
@RequiredArgsConstructor
public class UserServiceTestWithMock {
    @Mock
    private final UserRepository userRepository;

    @Mock
    private final WalletService walletService;

    @Mock
    private final TransactionsService transactionsService;

    @Mock
    private final WalletRepository walletRepository;

    @InjectMocks
    private final UserService userService;

    private User user;
    private Wallet wallet;
    private Transactions transactions;

    @BeforeEach
    void setup(){
    }
}
