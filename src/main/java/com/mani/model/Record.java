package com.mani.model;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.ToString;

@ToString
public class Record {

    @JacksonXmlProperty(isAttribute = true)
    private String reference;

    @JacksonXmlProperty(localName = "mutation")
    private String mutation;
    @JacksonXmlProperty(localName = "endBalance")
    private String endBalance;
    @JacksonXmlProperty(localName = "description")
    private String description;
    @JacksonXmlProperty(localName = "accountNumber")
    private String accountNumber;
    @JacksonXmlProperty(localName = "startBalance")
    private String startBalance;

    public Record() {
    }

    public Record(String reference, String mutation, String endBalance, String description, String accountNumber, String startBalance) {
        this.reference = reference;
        this.mutation = mutation;
        this.endBalance = endBalance;
        this.description = description;
        this.accountNumber = accountNumber;
        this.startBalance = startBalance;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public String getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(String endBalance) {
        this.endBalance = endBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }
}
