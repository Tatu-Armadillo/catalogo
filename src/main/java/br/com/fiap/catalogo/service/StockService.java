package br.com.fiap.catalogo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.fiap.catalogo.exception.BusinessException;
import br.com.fiap.catalogo.exception.NotFoundException;
import br.com.fiap.catalogo.model.Stock;
import br.com.fiap.catalogo.repository.StockRepository;
import br.com.fiap.catalogo.utils.GenetedIdentifyKeyCode;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Stock replenish(final Stock entity) {
        final var persisted = this.findStockByProduct(entity.getProduct().getIdentifiyKeyNumber());
        persisted.setLastUpdate(LocalDateTime.now());
        persisted.setQuantity(persisted.getQuantity() + entity.getQuantity());
        if (persisted.getQuantity() < 0) {
            throw new BusinessException(
                    "the " + entity.getCodeNumber() + " is an out of stock product");
        }

        return this.stockRepository.saveAndFlush(persisted);
    }

    public Stock save(final Stock entity) {
        entity.setQuantity(0);
        entity.setLastUpdate(LocalDateTime.now());
        entity.setCodeNumber(GenetedIdentifyKeyCode.createCodeNumber());
        return this.stockRepository.save(entity);
    }

    private Stock findStockByProduct(final String productKeyIdentify) {
        return this.stockRepository.findStockByProductIdentifiyKeyNumber(productKeyIdentify)
                .orElseThrow(
                        () -> new NotFoundException("Not Found Stock by product with key = " + productKeyIdentify));
    }

}
