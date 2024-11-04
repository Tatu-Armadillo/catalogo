package br.com.fiap.catalogo.record.stock;

import br.com.fiap.catalogo.model.Stock;

public record ViewStockRecord(
        String stockCode,
        String productCode,
        String productName,
        Integer quantity) {

    public static ViewStockRecord toRecord(final Stock entity) {
        return new ViewStockRecord(
                entity.getStockCode(),
                entity.getProduct().getProductCode(),
                entity.getProduct().getName(),
                entity.getQuantity());
    }

}
