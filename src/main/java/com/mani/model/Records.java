package com.mani.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;


public class Records {

    @JacksonXmlProperty(localName = "record")
    @JacksonXmlCData
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Transaction> record;

    public List<Transaction> getRecord ()
    {
        return record;
    }

    public void setRecord (List<Transaction> record)
    {
        this.record = record;
    }

    public Records(List<Transaction> record) {
        this.record = record;
    }

    public Records() {
    }
}
