package com.tuti.api.data;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

public class Card {
    private String name;
    @SerializedName("exp_date")
    private String expiryDate;
    @SerializedName("pan")
    private String PAN;

    public String getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(String cardIndex) {
        if (!PAN.isEmpty()) {
            this.cardIndex = PAN;
            return;
        }
        this.cardIndex = cardIndex;
    }

    @SerializedName("card_index")
    private String cardIndex;
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
        this.cardIndex = PAN;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", PAN='" + PAN + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equal(name, card.name) && Objects.equal(expiryDate, card.expiryDate) && Objects.equal(PAN, card.PAN);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, expiryDate, PAN);
    }
}
