package com.bdn.multifiles.writer;

import com.bdn.multifiles.model.ContainerFile;
import com.bdn.multifiles.model.SimpleFile;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileWriter implements ItemWriter<SimpleFile> {

    @Value("${app.message}")
    private String message;


    @Override
    public void write(Chunk<? extends SimpleFile> chunk) throws Exception {
        List<SimpleFile> lista = new ArrayList<>();
        String currentFileName = "";

        for (SimpleFile item : chunk.getItems()) {
            if(currentFileName.equals(item.getFileName()) || currentFileName.equals("")){
               lista.add(item);
                currentFileName = item.getFileName();
            }else {
                System.out.println("---------   " + message + "     -------------");
                lista.stream().forEach(System.out::println);
                lista  = new ArrayList<>();
                lista.add(item);
                currentFileName = item.getFileName();
            }

        }
        if(lista.size() > 0) {
            System.out.println("----------   " + message + "     ------------");
            lista.stream().forEach(System.out::println);
            System.out.println("-----------   " + message + "     -----------");
        }
    }
}
