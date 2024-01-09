package com.bdn.multifiles.step;

import com.bdn.multifiles.model.SimpleFile;
import com.bdn.multifiles.reader.NewAttemptReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MultiStepConfig {

    @Bean
    public Step multiStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                          MultiResourceItemReader<SimpleFile> multiStep,
                          ItemWriter multiStepWriter){

        StepBuilder stepBuilder = new StepBuilder("multiStep", jobRepository);

        return stepBuilder.chunk(100, transactionManager)
//                .reader(new NewAttemptReader(multiStep))
                .reader(multiStep)
                .writer(multiStepWriter)
                .build();


    }
}
