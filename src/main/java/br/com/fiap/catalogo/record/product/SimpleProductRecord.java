package br.com.fiap.catalogo.record.product;

import br.com.fiap.catalogo.model.Product;

public record SimpleProductRecord(
        String name,
        String code,
        Double price,
        String modelo,
        String fabricante,
        String detalhes) {

    public static SimpleProductRecord toRecord(final Product entity) {
        return new SimpleProductRecord(
                entity.getName(),
                entity.getProductCode(),
                entity.getPrice(),
                entity.getModelo(),
                entity.getFabricante(),
                entity.getDetalhes());
    }
}
