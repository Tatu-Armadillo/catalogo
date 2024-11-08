package br.com.fiap.catalogo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import br.com.fiap.catalogo.model.Product;

@Service
@EnableTransactionManagement
public class BatchExecutation {

    private final PlatformTransactionManager platformTransactionManager;
    private final JobLauncher jobLauncher;
    private final ProductItemWriter productItemWriter;

    @Autowired
    public BatchExecutation(
            PlatformTransactionManager platformTransactionManager,
            JobLauncher jobLauncher,
            ProductItemWriter productItemWriter) {
        this.platformTransactionManager = platformTransactionManager;
        this.jobLauncher = jobLauncher;
        this.productItemWriter = productItemWriter;
    }

    public JobExecution executeJob(JobRepository jobRepository, MultipartFile file) throws Exception {
        Job job = processCatalogo(jobRepository, file);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("fileName", file.getOriginalFilename())
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

        return jobLauncher.run(job, jobParameters);
    }

    public Job processCatalogo(JobRepository jobRepository, MultipartFile file) throws Exception {
        return new JobBuilder("importCatalogo", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(createStep(jobRepository, file))
                .build();
    }

    public Step createStep(JobRepository jobRepository, MultipartFile file) throws Exception {
        ItemReader<Product> itemReader = createItemReader(file);

        return new StepBuilder("stepBuilder", jobRepository)
                .<Product, Product>chunk(25, platformTransactionManager)
                .reader(itemReader)
                .writer(productItemWriter)
                .taskExecutor(new SimpleAsyncTaskExecutor())
                .build();
    }

    public ItemReader<Product> createItemReader(MultipartFile file) throws Exception {
        BeanWrapperFieldSetMapper<Product> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Product.class);

        return new FlatFileItemReaderBuilder<Product>()
                .name("productItemReader")
                .resource(new InputStreamResource(file.getInputStream()))
                .delimited()
                .names("name", "price", "modelo", "fabricante", "detalhes")
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

}
