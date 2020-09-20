package com.mani.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;


@Getter
@Setter
@ToString
public class Transaction {

    @JsonProperty(value="Reference")
    @JacksonXmlProperty(isAttribute = true)
    private Long reference;
    @JacksonXmlProperty(localName = "accountNumber")
    @JsonProperty(value="AccountNumber")
    private String accountNumber;
    @JsonProperty(value="Description")
    @JacksonXmlProperty(localName = "description")
    private String description;
    @JacksonXmlProperty(localName = "startBalance")
    @JsonProperty(value="Start Balance")
    private BigDecimal startBalance;
    @JsonProperty(value="Mutation")
    @JacksonXmlProperty(localName = "mutation")
    private String mutation;
    @JacksonXmlProperty(localName = "endBalance")
    @JsonProperty(value="End Balance")
    private BigDecimal endBalance;

    public Transaction(Long reference, String accountNumber, String description, BigDecimal startBalance, String mutation, BigDecimal endBalance) {
        this.reference = reference;
        this.accountNumber = accountNumber;
        this.description = description;
        this.startBalance = startBalance;
        this.mutation = mutation;
        this.endBalance = endBalance;
    }

    public Transaction() {
    }

    public Long getReference() {
        return reference;
    }

    public void setReference(Long reference) {
        this.reference = reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public String getMutation() {
        return mutation;
    }

    public void setMutation(String mutation) {
        this.mutation = mutation;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(reference, that.reference) &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(description, that.description) &&
                Objects.equals(startBalance, that.startBalance) &&
                Objects.equals(mutation, that.mutation) &&
                Objects.equals(endBalance, that.endBalance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, accountNumber, description, startBalance, mutation, endBalance);
    }
}
