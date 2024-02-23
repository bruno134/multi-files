package com.bdn.multifiles.reader;

import com.bdn.multifiles.model.SimpleFile;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class MultiFileReaderConfig {


    @Bean
    public MultiResourceItemReader<SimpleFile> multiResourceItemReader() throws IOException {
        System.out.println("Instantiating multiResourceItemReader");
        ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        //classpath*:some/package/name/**/*.xml
        Resource[] resources = inputFiles();
        //System.out.println("resources.length = " + inputFiles().length);


//        FlatFileItemReader<SimpleFile> flatFileItemReader = new FlatFileItemReader<>();
        FlatFileItemReader<SimpleFile> flatFileItemReader = new AnotherReader();

        DefaultLineMapper<SimpleFile> mapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokens = new DelimitedLineTokenizer();
        tokens.setNames(new String[] { "name", "lastName", "age"});
        mapper.setLineTokenizer(tokens);
        mapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<SimpleFile>() {{
            setTargetType(SimpleFile.class);
        }});

        flatFileItemReader.setLineMapper(mapper);

//        flatFileItemReader.
//                .delimited()
//                .names(new String[] { "name", "lastName", "age"})
//                .targetType(SimpleFile.class)
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<SimpleFile>() {{
//                    setTargetType(SimpleFile.class);
//                }})
//                .build();

        MultiResourceItemReader<SimpleFile> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setDelegate(flatFileItemReader);
        resourceItemReader.setResources(resources);


        return resourceItemReader;
    }

    private Resource[] inputFiles() throws IOException {

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:/files/*.txt");
        return resources;
    }

}
