package com.bdn.multifiles.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bdn.multifiles.model.SimpleFile;

@Configuration
public class ExcelProcessor {

    @Bean
    public ItemProcessor<SimpleFile, SimpleFile> processorOfExcel() {
        return item -> {
            System.out.println("Processing item: " + item);
            return item;
        };
    }


}
