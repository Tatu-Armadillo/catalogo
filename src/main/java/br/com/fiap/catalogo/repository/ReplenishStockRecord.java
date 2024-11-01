package br.com.fiap.catalogo.repository;

import jakarta.validation.constraints.NotBlank;

public record ReplenishStockRecord(
        @NotBlank String productKeyIdentify,
        Integer quantity) {

}
