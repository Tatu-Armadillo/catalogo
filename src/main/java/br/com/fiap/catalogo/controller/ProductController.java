package br.com.fiap.catalogo.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.catalogo.record.product.NewProductRecord;
import br.com.fiap.catalogo.record.product.SimpleProductRecord;
import br.com.fiap.catalogo.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/product")
@Tag(name = "Products", description = "Endpoints for Managing products")
@AllArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Object> showProducts(
            @PageableDefault(sort = "name", direction = Direction.ASC) final Pageable pageable,
            @RequestParam(required = false, defaultValue = "") final String filter) {
        final var response = this.service.showProducts(pageable, filter)
                .map(SimpleProductRecord::toRecord);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> save(@RequestBody final NewProductRecord record) {
        final var response = this.service.save(NewProductRecord.toEntity(record));
        return ResponseEntity.ok(response);
    }

}
