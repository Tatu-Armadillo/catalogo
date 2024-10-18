package br.com.fiap.catalogo.record.product;

import br.com.fiap.catalogo.model.Product;

public record NewProductRecord(
                String name,
                Double price,
                String modelo,
                String fabricante,
                String detalhes) {

        public static Product toEntity(final NewProductRecord record) {
                return new Product(
                                record.name(),
                                record.price(),
                                record.modelo(),
                                record.fabricante(),
                                record.detalhes());
        }

}
