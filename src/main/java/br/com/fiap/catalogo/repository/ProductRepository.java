package br.com.fiap.catalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.catalogo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
