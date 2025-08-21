package com.example.E_Wallet.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "wallet")
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID WalletId;
    private double WalletBalance;

    @OneToOne(mappedBy = "UserWallet")
    private User WalletUser;

    public UUID getWalletId() {
        return WalletId;
    }

    public void setWalletId(UUID walletId) {
        WalletId = walletId;
    }

    public double getWalletBalance() {
        return WalletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        WalletBalance = walletBalance;
    }

    public User getWalletUser() {
        return WalletUser;
    }

    public void setWalletUser(User walletUser) {
        WalletUser = walletUser;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "WalletBalance=" + WalletBalance +
                ", WalletId=" + WalletId +
                '}';
    }
}
