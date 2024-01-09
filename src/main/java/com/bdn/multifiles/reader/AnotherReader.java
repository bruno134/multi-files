package com.bdn.multifiles.reader;

import com.bdn.multifiles.model.ContainerFile;
import com.bdn.multifiles.model.SimpleFile;
import org.springframework.batch.item.ResourceAware;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

public class AnotherReader extends FlatFileItemReader implements ResourceAware {
    private Resource resource;

    private String currentFileName = "";

    protected Object doRead() throws Exception {
//        List<SimpleFile> items = new ArrayList<>();
        SimpleFile item = (SimpleFile) super.doRead();
        if (item != null) {
           // System.out.println("Resource: " + resource.getFilename());
            item.setFileName(resource.getFilename());
        }
        return item;
    }

    @Override
    public void setResource(Resource resource) {
        super.setResource(resource);
        this.resource = resource;
    }



}
