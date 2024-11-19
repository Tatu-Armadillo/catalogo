package br.com.fiap.catalogo.mock;

import java.util.UUID;

import br.com.fiap.catalogo.model.Product;

public class ProductMock {
    
    public static Product mock() {
        final var product = new Product();
        product.setId(1L);
        product.setName("name");
        product.setPrice(1.0D);
        product.setModelo("modelo");
        product.setFabricante("fabricante");
        product.setDetalhes("detalhes");
        product.setProductCode(UUID.randomUUID().toString());
        return product;
    }

}
