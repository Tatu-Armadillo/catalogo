package br.com.fiap.catalogo.service;

import org.springframework.stereotype.Service;

import br.com.fiap.catalogo.model.Product;
import br.com.fiap.catalogo.model.Stock;
import br.com.fiap.catalogo.repository.ProductRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    private final StockService stockService;


    public Product save(final Product entity) {
        final var product = this.productRepository.saveAndFlush(entity);
        this.stockService.save(new Stock(product));
        return product;
    }


}
