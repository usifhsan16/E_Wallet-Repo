package com.example.E_Wallet.Service;

import java.util.UUID;

public interface GenericService <T> {
    T FindById(UUID id);

    void Delete(UUID id);

    T Save(T entity);


}
