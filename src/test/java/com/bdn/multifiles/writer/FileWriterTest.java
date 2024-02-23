package com.bdn.multifiles.writer;


import com.bdn.multifiles.MultiFilesApplication;
import com.bdn.multifiles.model.SimpleFile;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBatchTest
@SpringJUnitConfig(classes = {MultiFilesApplication.class})
@TestPropertySource("classpath:application-test.properties")
public class FileWriterTest {

    @Autowired
    private FileWriter fileWriter;


    @Test
    void write() throws Exception {


        SimpleFile[] simpleFiles = new SimpleFile[5];

        for(int i = 0; i < 5; i++){
            simpleFiles[i] = new SimpleFile();
            simpleFiles[i].setFileName("file" + i);
            simpleFiles[i].setName("Name " + i);
            simpleFiles[i].setAge(String.valueOf(i+30));
            simpleFiles[i].setLastName("Last Name " + i);
        }

        Chunk<SimpleFile> chunk = new Chunk<>(simpleFiles);

        fileWriter.write(chunk);
    }

}
