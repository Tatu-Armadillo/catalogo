package br.com.fiap.catalogo.model;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class Order {
    
    private String idOrder;

    private String userEmail;

    private String message;

    private LocalDateTime createDate;

    private Map<String, Integer> productQuantities;

    private String status;
}
