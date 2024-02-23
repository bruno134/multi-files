package com.bdn.multifiles.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SimpleFileJobConfig {

    @Bean
    public Job simpleFileJob(JobRepository jobRepository, PlatformTransactionManager transactionManager, Step simpleFileStep){
        System.out.println("Instantiating simpleFileJob");
        return new JobBuilder("simpleFileJob", jobRepository)
                .start(simpleFileStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }



}
