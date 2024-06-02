package com.java.functional.dto;

import java.time.LocalDate;

public class FinanceAccount {
    private String acctNum;
    private String checkNum;
    private String checkAmount;
    private LocalDate checkDate;
    public FinanceAccount(){

    }
    public FinanceAccount(String acctNum, String checkNum, String checkAmount, LocalDate checkDate) {
        this.acctNum = acctNum;
        this.checkNum = checkNum;
        this.checkAmount = checkAmount;
        this.checkDate = checkDate;
    }

    public String getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(String acctNum) {
        this.acctNum = acctNum;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(String checkAmount) {
        this.checkAmount = checkAmount;
    }

    public LocalDate getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    @Override
    public String toString() {
        return "FinanceAccount{" +
                "acctNum='" + acctNum + '\'' +
                ", checkNum='" + checkNum + '\'' +
                ", checkAmount='" + checkAmount + '\'' +
                ", checkDate=" + checkDate +
                '}';
    }
}
