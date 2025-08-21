package com.example.E_Wallet.DTO;

import com.example.E_Wallet.Entity.Wallet;

import java.awt.event.WindowListener;

public class WalletDTO {
    private double WalletBalance;

    public double getWalletBalance() {
        return WalletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        WalletBalance = walletBalance;
    }

    public WalletDTO toDto(Wallet wallet){
        WalletDTO dto=new WalletDTO();
        dto.WalletBalance=wallet.getWalletBalance();
        return dto;
    }

    public Wallet toEntity(WalletDTO dto){
        Wallet wallet=new Wallet();
        wallet.setWalletBalance(dto.getWalletBalance());
        return wallet;
    }
}
