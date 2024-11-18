package br.com.fiap.catalogo.mock;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.fiap.catalogo.model.Stock;

public class StockMock {

    public static Stock mock() {
        final Stock stock = new Stock();
        stock.setId(1L);
        stock.setLastUpdate(LocalDateTime.now());
        stock.setQuantity(0);
        stock.setStockCode(UUID.randomUUID().toString());
        stock.setProduct(ProductMock.mock());
        return stock;
    }

}
