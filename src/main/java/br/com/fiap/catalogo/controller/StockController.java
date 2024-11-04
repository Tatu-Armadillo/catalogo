package br.com.fiap.catalogo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.catalogo.model.Product;
import br.com.fiap.catalogo.model.Stock;
import br.com.fiap.catalogo.record.stock.ReplenishStockRecord;
import br.com.fiap.catalogo.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/stock")
@Tag(name = "Stocks", description = "Endpoints for Managing stocks")
@AllArgsConstructor
public class StockController {

    private final StockService service;

    @PatchMapping("/replenish")
    @Transactional
    public ResponseEntity<Object> replenish(
            @RequestBody final ReplenishStockRecord record) {
        final var response = this.service.replenish(new Stock(
                record.quantity(),
                new Product(record.productCode())));
        return ResponseEntity.ok(response);
    }

}
