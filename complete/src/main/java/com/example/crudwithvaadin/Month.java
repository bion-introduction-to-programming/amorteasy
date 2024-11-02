package com.example.crudwithvaadin;

public class Month {

    private String month;
    private Integer year;

    public Month(String month, Integer year) {
        this.month = month;
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}