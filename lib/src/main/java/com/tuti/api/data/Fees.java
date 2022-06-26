package com.tuti.api.data;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Fees implements Serializable {

    @SerializedName("mohe_fees")
    float moheFees;
    @SerializedName("p2p_fees")
    float p2pFees;
    @SerializedName("custom_fees")
    float customFees;
    @SerializedName("special_payment_fees")
    float specialPaymentFees;

    public float getMoheFees() {
        return moheFees;
    }

    public float getP2pFees() {
        return p2pFees;
    }

    public float getCustomFees() {
        return customFees;
    }

    public float getSpecialPaymentFees() {
        return specialPaymentFees;
    }

}
