package com.bdn.multifiles.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.bdn.multifiles.model.SimpleFile;

@Configuration
public class MultiStepConfig {

    @SuppressWarnings("unchecked")
    @Bean
    public Step multiStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                          ItemReader<SimpleFile> excelReader,
                          ItemProcessor processorOfExcel,
                          ItemWriter<SimpleFile> multiStepWriter){
        System.out.println("Instantiating multiStep");
        StepBuilder stepBuilder = new StepBuilder("multiStep", jobRepository);

        return stepBuilder.chunk(100, transactionManager)
//                .reader(new NewAttemptReader(multiStep))
                .reader(excelReader)
                .processor(processorOfExcel)                
                .writer(multiStepWriter)
                .build();


    }
}
