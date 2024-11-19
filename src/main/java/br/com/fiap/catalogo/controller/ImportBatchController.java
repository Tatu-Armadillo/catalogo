package br.com.fiap.catalogo.controller;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fiap.catalogo.batch.BatchExecutation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/import")
@Tag(name = "Imports", description = "Endpoints for Managing Imports by batch")
public class ImportBatchController {

    private final JobRepository jobRepository;
    private final BatchExecutation batchExecutation;

    @Autowired
    public ImportBatchController(
            final JobRepository jobRepository,
            final BatchExecutation batchExecutation) {
        this.jobRepository = jobRepository;
        this.batchExecutation = batchExecutation;
    }

    @PostMapping("/products")
    public ResponseEntity<Object> processCatalogo(@RequestParam final MultipartFile file) throws Exception {
        if (!file.getOriginalFilename().endsWith(".csv")) {
            return ResponseEntity.status(415).body("Arquivo diferente do '.csv' ");
        }
        final var jobExecution = this.batchExecutation.executeJob(jobRepository, file);
        return ResponseEntity.ok("Job iniciado com sucesso! ID do Job: " + jobExecution.getJobId());

    }
}
