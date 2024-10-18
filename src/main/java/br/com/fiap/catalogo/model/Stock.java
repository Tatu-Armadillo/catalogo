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
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product", foreignKey = @ForeignKey(name = "fk_products_stocks"))
    private Product product;

}
