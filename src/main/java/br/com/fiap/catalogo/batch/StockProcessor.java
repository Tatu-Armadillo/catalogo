package br.com.fiap.catalogo.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import br.com.fiap.catalogo.model.Product;
import br.com.fiap.catalogo.model.Stock;
import br.com.fiap.catalogo.service.StockService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StockProcessor implements ItemProcessor<Product, Product> {

    private final StockService stockService;
    
    @Override
    @Nullable
    public Product process(@NonNull Product item) throws Exception {
        final var stock = this.stockService.save(new Stock(item));
        return stock.getProduct();
    }

}
