package br.com.fiap.catalogo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products", schema = "challenge")
@Data
public class Product {

    @Id
    @Column(name = "id_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "detalhes")
    private String detalhes;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private Stock stock;

    public Product(String name, Double price, String modelo, String fabricante, String detalhes) {
        this.name = name;
        this.price = price;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.detalhes = detalhes;
    }

}
