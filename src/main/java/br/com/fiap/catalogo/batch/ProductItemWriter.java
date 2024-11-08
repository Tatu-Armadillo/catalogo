package br.com.fiap.catalogo.batch;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import br.com.fiap.catalogo.model.Product;

import java.sql.PreparedStatement;
import java.util.UUID;

@Component
public class ProductItemWriter implements ItemWriter<Product> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void write(@NonNull Chunk<? extends Product> products) throws Exception {
        for (Product product : products) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO challenge.products (name, price, modelo, fabricante, detalhes, product_code) VALUES (?, ?, ?, ?, ?, ?)",
                        new String[] { "id_product" });
                ps.setString(1, product.getName());
                ps.setDouble(2, product.getPrice());
                ps.setString(3, product.getModelo());
                ps.setString(4, product.getFabricante());
                ps.setString(5, product.getDetalhes());
                ps.setString(6, UUID.randomUUID().toString());
                return ps;
            }, keyHolder);

            Long productId = keyHolder.getKey().longValue();
            if (productId != null) {
                product.setId(productId); 
            }

            jdbcTemplate.update(
                    "INSERT INTO challenge.stocks (product, quantity, stock_code, last_update) VALUES (?, ?, ?, ?)",
                    product.getId(),
                    0,
                    UUID.randomUUID().toString(),
                    java.time.LocalDateTime.now() 
            );
        }
    }
}
