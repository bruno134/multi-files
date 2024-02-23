package com.bdn.multifiles.reader;

import org.springframework.batch.extensions.excel.poi.PoiItemReader;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;

import com.bdn.multifiles.model.SimpleFile;

@Configuration
public class ExcelFileReader {

@Bean
public ItemReader<SimpleFile> excelReader() {
    PoiItemReader<SimpleFile> reader = new PoiItemReader<>();    
    // reader.setResource(new PathResource("/Users/brunodn/dev/java/projetos/spring batch/multi-files/src/main/resources/files/simple.xlsx"));
    reader.setResource(new ClassPathResource("files/simple.xlsx"));
    reader.setRowMapper(rs -> {
        
        String[] fields = rs.getCurrentRow();
        
        SimpleFile simpleFile = new SimpleFile();

        simpleFile.setName(fields[0]);
        simpleFile.setLastName(fields[1]);
        simpleFile.setAge(fields[2]);
        simpleFile.setFileName("simple.xlsx");
        return simpleFile;
    });
    reader.setLinesToSkip(2);
    return reader;
}
   


}


