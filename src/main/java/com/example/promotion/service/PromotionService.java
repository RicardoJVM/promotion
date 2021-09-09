package com.example.promotion.service;

import com.example.promotion.model.Cliente;
import com.example.promotion.model.Promotion;
import com.example.promotion.model.Type;

import java.util.List;

public interface PromotionService {
    public Promotion findPromotionByType(Type type);
    public Boolean sendPromoEmailsToBirthdayBoys(List<Cliente> birthdayBoys,Integer discountValue);
}
