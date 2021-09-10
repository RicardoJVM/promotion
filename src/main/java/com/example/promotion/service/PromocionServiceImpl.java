package com.example.promotion.service;

import com.example.promotion.model.Cliente;
import com.example.promotion.model.Promotion;
import com.example.promotion.model.Type;
import com.example.promotion.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

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

    @Override
    public List<String> leerContenidoDeTxt() throws IOException{
        String inputFileName = "src/main/resources/static/Original.txt";
        InputStream is = new FileInputStream(inputFileName);
        String content = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
        content = content.replace("\n\r", "\n\r@");
        List<String> parrafos = Arrays.asList(content.split("@", -1));
        for (String parrafo:parrafos){
            System.out.println(parrafo);
        }
        return parrafos;
    }

    @Override
    public List<String> estrofasEnOrdenInverso() throws IOException {
        List<String> parrafos = leerContenidoDeTxt();
        Collections.reverse(parrafos);
        Boolean guardarYGenerar = generarTxtDesdeString("estrofasEnOrdenInverso",parrafos);
        return parrafos;
    }

    @Override
    public String estrofasYPalabraMasRepetida() throws IOException {
        List<String> parrafos = leerContenidoDeTxt();
        String messageToFile1 = "El numero de parrafos es:"+parrafos.size()+"\n";

        // Enviar String original sin modificar
        String inputFileName = "src/main/resources/static/Original.txt";
        InputStream is = new FileInputStream(inputFileName);
        String content = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
        String masRepetida = palabraMasRepetida(content);

        String messageToFile2 = "La palabra mas repetida es:"+masRepetida+"\n";
        List<String> messageToFile = new ArrayList<>();
        messageToFile.add(messageToFile1);
        messageToFile.add(messageToFile2);
        Boolean guardarYGenerar = generarTxtDesdeString("statistics",messageToFile);
        return messageToFile.toString();
    }

    @Override
    public String reemplazoDePalabraMasRepetidaPoryou() throws IOException {
        // Enviar String original sin modificar
        String inputFileName = "src/main/resources/static/Original.txt";
        InputStream is = new FileInputStream(inputFileName);
        String content = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
        String masRepetida = palabraMasRepetida(content);

        content = content.replace(masRepetida,"you");
        List<String> contentReplaced = new ArrayList<>();
        contentReplaced.add(content);
        Boolean guardarYGenerar = generarTxtDesdeString("finaloutput",contentReplaced);
        return contentReplaced.toString();
    }

    @Override
    public Boolean generarTxtDesdeString(String nombreArchivo,List<String> contenidoArchivo) {
        try {
            String ruta = "src/main/resources/static/"+nombreArchivo+".txt";
            String contenido = contenidoArchivo.toString();
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String palabraMasRepetida(String content)
    {
        String palabras [] = content.split(" ");
        String palabrasB [] = content.split(" ");

        int cantidad = palabras.length;
        String resultado = "";
        int contadorMasRepet =  0;

        for (int i = 0; i < cantidad; i++) {
            int contador = 0;
//          resultado += palabras[i];
            String palabra = palabras[i];

            for (int j = 0; j < cantidad; j++) {

                if (palabra.equalsIgnoreCase(palabrasB[j])){
                    contador++;
                    palabras[j] = "";

                }
            }

            if ((contador > 1)&& (contador > contadorMasRepet)){
                resultado = palabra;
                contadorMasRepet = contador;
                System.out.print(palabras[i]);
            }
            else if ((contador > 1)&& (contador == contadorMasRepet)){
                resultado += " " + palabra;
            }
        }
        if (resultado == "")
            resultado = "No hay palabra repetida";

        return resultado;
    }
}
