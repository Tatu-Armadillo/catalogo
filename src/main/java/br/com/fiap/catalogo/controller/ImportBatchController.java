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
import org.springframework.batch.core.JobExecutionException;

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
    public ResponseEntity<Object> processCatalogo(@RequestParam final MultipartFile file) {
        try {
            if (!file.getOriginalFilename().endsWith(".csv")) {
                return ResponseEntity.badRequest().body("Arquivo diferente do '.csv' ");
            }
            final var jobExecution = this.batchExecutation.executeJob(jobRepository, file);
            return ResponseEntity.ok("Job iniciado com sucesso! ID do Job: " + jobExecution.getJobId());
        } catch (JobExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao iniciar o job: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro inesperado: " + e.getMessage());
        }
    }
}
