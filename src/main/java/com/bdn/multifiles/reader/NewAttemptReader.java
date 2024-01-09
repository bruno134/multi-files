package com.bdn.multifiles.reader;

import com.bdn.multifiles.model.ContainerFile;
import com.bdn.multifiles.model.SimpleFile;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

public class NewAttemptReader implements ItemStreamReader {

    private Resource resource;

    private MultiResourceItemReader<SimpleFile> delegate;

    public NewAttemptReader(MultiResourceItemReader<SimpleFile> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object read() throws Exception {
        List<SimpleFile> records = new ArrayList<>();
        SimpleFile record = (SimpleFile) delegate.read();

        ContainerFile containerFile = new ContainerFile();
        containerFile.setRecords(records);
      //  containerFile.setName(resource.getFilename());

        return record;
    }
//
//    @Override
//    public void setResource(Resource resource) {
//    System.out.println("Resource: " + (resource==null?"NULOOOO":"NOT NULL!!!"));
//       // super.setResource(resource);
//        this.resource = resource;
//    }
}
