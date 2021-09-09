package com.example.promotion.controller;

import com.example.promotion.model.Cliente;
import com.example.promotion.service.ClienteService;
import com.example.promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins="*",allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class PromotionController {

    @Value("${promotion.discountValue}")
    private Integer discountValue;

    private PromotionService promocionService;
    private ClienteService clienteService;

    public PromotionController(PromotionService promocionService, ClienteService clienteService) {
        this.promocionService = promocionService;
        this.clienteService = clienteService;
    }

    @GetMapping("/promotion/{birthdate}")
    public List<Cliente> getCumpleaneros(@PathVariable String birthdate){
        return clienteService.findAllCumpleaneros(birthdate);
    }

    @GetMapping("/promotion/sendPromoEmail/{birthdate}")
    public Boolean sendPromoEmailsToBirthdayBoys(@PathVariable String birthdate){
        List<Cliente> birthdayBoys = clienteService.findAllCumpleaneros(birthdate);
        Boolean enviado = promocionService.sendPromoEmailsToBirthdayBoys(birthdayBoys,discountValue);
        return enviado;
    }

}
