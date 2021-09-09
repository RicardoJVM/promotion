package com.example.promotion.service;

import com.example.promotion.model.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> findAllCumpleaneros(String birthday);
}
