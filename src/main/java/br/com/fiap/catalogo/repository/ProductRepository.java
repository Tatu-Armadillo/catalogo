package br.com.fiap.catalogo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.catalogo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p "
            + "WHERE (:filter IS NULL OR :filter = '' "
            + "OR p.name LIKE CONCAT('%', :filter, '%') "
            + "OR p.modelo LIKE CONCAT('%', :filter, '%') "
            + "OR p.fabricante LIKE CONCAT('%', :filter, '%'))")
    Page<Product> findAllByNameOrModeloOrFabricante(
            @Param("filter") String filter,
            Pageable pageable);

}
