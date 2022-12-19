package com.rana.adarsh.imagetotext.Model;

public class Employee {
    private String swine,numhouse,name,status,date;

    public Employee(String swine, String numhouse, String name, String status, String date) {
        this.swine = swine;
        this.numhouse = numhouse;
        this.name = name;
        this.status = status;
        this.date = date;
    }

    public String getSwine() {
        return swine;
    }

    public void setSwine(String swine) {
        this.swine = swine;
    }

    public String getNumhouse() {
        return numhouse;
    }

    public void setNumhouse(String numhouse) {
        this.numhouse = numhouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}