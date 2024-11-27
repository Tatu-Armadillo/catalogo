package br.com.fiap.catalogo.service;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import br.com.fiap.catalogo.model.Order;
import br.com.fiap.catalogo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedEventListener implements Consumer<Order> {

    private final ProductRepository productRepository;

    @Override
    public void accept(Order order){
        log.info("Produto (ordem) recebida" + order.getIdOrder());
        

    }

}
