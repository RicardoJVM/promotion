package com.example.promotion.service;

import com.example.promotion.model.Cliente;
import com.example.promotion.model.Promotion;
import com.example.promotion.model.Type;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PromotionService {
    public Promotion findPromotionByType(Type type);
    public Boolean sendPromoEmailsToBirthdayBoys(List<Cliente> birthdayBoys,Integer discountValue);
    public List<String> leerContenidoDeTxt() throws IOException;
    public List<String> estrofasEnOrdenInverso() throws IOException;
    public String estrofasYPalabraMasRepetida() throws IOException;
    public String reemplazoDePalabraMasRepetidaPoryou() throws IOException;
    public Boolean generarTxtDesdeString(String nombreArchivo,List<String> contenidoArchivo);
}
