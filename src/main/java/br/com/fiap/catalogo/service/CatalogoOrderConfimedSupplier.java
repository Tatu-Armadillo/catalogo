package br.com.fiap.catalogo.service;

import java.util.function.Supplier;

import org.springframework.stereotype.Component;

import br.com.fiap.catalogo.model.Product;
import lombok.extern.slf4j.Slf4j;

// @Component
@Slf4j
public class CatalogoOrderConfimedSupplier implements Supplier<Product>{

    @Override
    public Product get(){
        log.info("PRODUTO ENVIADO");
        return new Product();
    }

}
