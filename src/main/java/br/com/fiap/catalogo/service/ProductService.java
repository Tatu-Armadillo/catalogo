package br.com.fiap.catalogo.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.catalogo.exception.NotFoundException;
import br.com.fiap.catalogo.model.Product;
import br.com.fiap.catalogo.model.Stock;
import br.com.fiap.catalogo.repository.ProductRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final StockService stockService;

    public Page<Product> showProducts(final Pageable pageable, final String filter) {
        return this.productRepository.findAllByNameOrModeloOrFabricante(filter, pageable);
    }

    public Product save(final Product entity) {
        entity.setProductCode(UUID.randomUUID().toString());
        final var product = this.productRepository.saveAndFlush(entity);
        this.stockService.save(new Stock(product));
        return product;
    }

    public Product findById(final Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found Proudct with id = " + id));
    }

}
