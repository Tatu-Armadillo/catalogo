package br.com.fiap.catalogo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stocks", schema = "challenge")
@Data
public class Stock {

    @Id
    @Column(name = "id_stock")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "code_number")
    private String codeNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product", foreignKey = @ForeignKey(name = "fk_products_stocks"))
    private Product product;

    public Stock(final Product product) {
        this.product = product;
    }

}
