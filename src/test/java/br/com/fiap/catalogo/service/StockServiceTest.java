package br.com.fiap.catalogo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.catalogo.exception.BusinessException;
import br.com.fiap.catalogo.exception.NotFoundException;
import br.com.fiap.catalogo.mock.StockMock;
import br.com.fiap.catalogo.model.Stock;
import br.com.fiap.catalogo.repository.StockRepository;

public class StockServiceTest {

    @InjectMocks
    private StockService stockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        stockService = new StockService(stockRepository);
    }

    @Test
    void testReplenishWithQuantityGreaterThanZero() {

        when(this.stockRepository.findStockByProductProductCode(anyString())).thenReturn(Optional.of(StockMock.mock()));
        when(this.stockRepository.saveAndFlush(any(Stock.class))).thenReturn(StockMock.mock());

        final var response = this.stockService.replenish(StockMock.mock());

        assertNotNull(response);
    }

    @Test
    void testReplenishWithQuantityLessThanZero() {

        final var entity = StockMock.mock();
        entity.setQuantity(0);
        entity.setStockCode("teste");

        final var persited = StockMock.mock();
        persited.setQuantity(0);

        when(this.stockRepository.findStockByProductProductCode(anyString())).thenReturn(Optional.of(persited));

        final var error = assertThrows(BusinessException.class,
                () -> this.stockService.replenish(entity));

        assertEquals("the teste is an out of stock product", error.getMessage());
    }

    @Test
    void testSave() {
        when(this.stockRepository.save(any(Stock.class))).thenReturn(StockMock.mock());

        final var response = this.stockService.save(StockMock.mock());

        assertNotNull(response);
        assertTrue(response.getQuantity() == 0);
    }

    @Test
    void testFindStockByProductSuccess() {
        when(this.stockRepository.findStockByProductProductCode(anyString())).thenReturn(Optional.of(StockMock.mock()));

        final var response = this.stockService.findStockByProduct("");

        assertNotNull(response);
    }

    @Test
    void testFindStockByProductFailed() {
        when(this.stockRepository.findStockByProductProductCode(anyString())).thenReturn(Optional.empty());

        final var error = assertThrows(NotFoundException.class,
                () -> this.stockService.findStockByProduct(""));

        assertEquals("Not Found Stock by product with code = ", error.getMessage());
    }

}
