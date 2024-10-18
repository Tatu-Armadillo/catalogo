package br.com.fiap.catalogo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.fiap.catalogo.model.Stock;
import br.com.fiap.catalogo.repository.StockRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public Stock save(final Stock entity) {
        entity.setQuantity(0);
        entity.setLastUpdate(LocalDateTime.now());
        entity.setCodeNumber(createCodeNumber());
        return this.stockRepository.save(entity);
    }

    private String createCodeNumber() {
        return LocalDateTime.now().toString()
                .replace("'T'", "")
                .replace("-", "")
                .replace(":", "")
                .replace(".", "");
    }

}
