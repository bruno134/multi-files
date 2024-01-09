package com.bdn.multifiles.model;

import java.util.ArrayList;
import java.util.List;

public class ContainerFile {
    private String name;
    private boolean isOK;
    private List<SimpleFile> records    = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public List<SimpleFile> getRecords() {
        return records;
    }

    public void setRecords(List<SimpleFile> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "ContainerFile{" +
                "name='" + name + '\'' +
                ", isOK=" + isOK +
                ", records=" + records +
                '}';
    }
}
