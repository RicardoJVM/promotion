package com.example.promotion.service;

import com.example.promotion.model.Cliente;
import com.example.promotion.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteDao;

    public ClienteServiceImpl(ClienteRepository clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public List<Cliente> findAllCumpleaneros(String birthday) {
        //Busqueda en bd
        List<Cliente> cumpleaneros = new ArrayList<Cliente>();
        Cliente cumpleanero1 = new Cliente(1L,"Ricardo","Valencia","ricardini162@gmail.com","09/21");
        Cliente cumpleanero2 = new Cliente(2L,"Juan","Perez","ricardini010@hotmail.com","09/21");
        cumpleaneros.add(cumpleanero1);
        cumpleaneros.add(cumpleanero2);
        return cumpleaneros;
    }
}
