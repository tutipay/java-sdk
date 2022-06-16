package com.tuti.api.data;

public class Card {
    private String name;
    private String expiryDate;
    private String PAN;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }
}
