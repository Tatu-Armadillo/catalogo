package br.com.fiap.catalogo.record.stock;

import jakarta.validation.constraints.NotBlank;

public record ReplenishStockRecord(
        @NotBlank String productCode,
        Integer quantity) {

}
