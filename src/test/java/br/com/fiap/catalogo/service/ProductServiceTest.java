package br.com.fiap.catalogo.service;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.fiap.catalogo.mock.ProductMock;
import br.com.fiap.catalogo.mock.StockMock;
import br.com.fiap.catalogo.model.Product;
import br.com.fiap.catalogo.model.Stock;
import br.com.fiap.catalogo.repository.ProductRepository;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private StockService stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository, stockService);
    }

    @Test
    void testShowProducts() {
        when(this.productRepository.findAllByNameOrModeloOrFabricante(anyString(), any()))
                .thenReturn(new PageImpl<>(List.of(ProductMock.mock())));

        final var response = this.productService.showProducts(Pageable.ofSize(1), "");

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void testSave() {

        when(this.productRepository.saveAndFlush(any(Product.class))).thenReturn(ProductMock.mock());
        when(this.stockService.save(any(Stock.class))).thenReturn(StockMock.mock());

        final var response = this.productService.save(ProductMock.mock());

        assertNotNull(response);
        assertNotNull(response.getStock());
    }

}
