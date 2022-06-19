package com.tuti.api.data;

import com.google.gson.annotations.SerializedName;

public class Card {
    private String name;
    @SerializedName("exp_date")
    private String expiryDate;
    @SerializedName("pan")
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
