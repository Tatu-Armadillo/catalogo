package br.com.fiap.catalogo.service;

import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import br.com.fiap.catalogo.model.Order;
import br.com.fiap.catalogo.model.Product;
import br.com.fiap.catalogo.model.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class OrderCreatedEventListener implements Consumer<Order> {

    private final StockService stockService;
    private final UpdateOrderService updateOrderService;
    

    @Override
    public void accept(Order order) {
        String orderId = order.getIdOrder();
        log.info("Produto (ordem) recebida ID: " + orderId);
        try {
            order.getProductQuantities().forEach((productCode, quantity) -> {
                this.stockService.replenish(new Stock(
                        quantity*-1,
                        new Product(productCode)));

            });
            this.updateOrderService.updateOrderStatus(orderId, "CONFIRMED");
        } catch (Exception e) {
            log.info(e.toString());
            this.updateOrderService.updateOrderStatus(orderId, "CANCELED");
        } finally {
            log.info("Mensagem processada");
        }

    }

}
