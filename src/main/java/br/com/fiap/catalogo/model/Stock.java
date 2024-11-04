package br.com.fiap.catalogo.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stocks", schema = "challenge")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @Column(name = "id_stock")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Column(name = "stock_code")
    private String stockCode;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product", foreignKey = @ForeignKey(name = "fk_products_stocks"))
    private Product product;

    public Stock(final Product product) {
        this.product = product;
    }
    
    public Stock(final Integer quantity, final Product product) {
        this.quantity = quantity;
        this.product = product;
    }

}
