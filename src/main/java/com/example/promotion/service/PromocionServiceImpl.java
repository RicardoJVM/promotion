package com.example.promotion.service;

import com.example.promotion.model.Cliente;
import com.example.promotion.model.Promotion;
import com.example.promotion.model.Type;
import com.example.promotion.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromocionServiceImpl implements PromotionService {

    @Value("${spring.mail.username}")
    private String sender;
    private final PromotionRepository promotionDao;
    private final JavaMailSender javaMailSender;

    public PromocionServiceImpl(PromotionRepository promotionDao, JavaMailSender javaMailSender) {
        this.promotionDao = promotionDao;
        this.javaMailSender = javaMailSender;
    }


    @Override
    public Promotion findPromotionByType(Type type) {
        // Busqueda en bd por tipo de promocion
        Promotion result = new Promotion(1L,Type.CUMPLEANOS,"<name> Hoy es su cumpleaños y usted es importante para nosotros, queremos celebralo ofreciendo un <discountValue> % de descuento y delivery gratuito. Valido por 24 hrs");
        return result;
    }

    @Override
    public Boolean sendPromoEmailsToBirthdayBoys(List<Cliente> birthdayBoys, Integer discountValue){

        Promotion promotion = findPromotionByType(Type.CUMPLEANOS);

        for (Cliente cli : birthdayBoys) {
            String fixedMessageName = promotion.getDescription().replace("<name>",cli.getFirstName());
            String fixedMessageDiscount = fixedMessageName.replace("<discountValue>",discountValue.toString());

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(cli.getEmail());
            message.setSubject(promotion.getType().toString());
            message.setText(fixedMessageDiscount);
            javaMailSender.send(message);
        }
        return true;
    }
}
