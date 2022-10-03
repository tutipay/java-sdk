package com.tuti.api.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DueAmount implements Serializable {
    public String getDueAmount() {
        return dueAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    @SerializedName("due_amount")
    String dueAmount;

    @SerializedName("total_amount")
    String totalAmount;

    @SerializedName("min_amount")
    String minAmount;

    @SerializedName("paid_amount")
    String paidAmount;
}
