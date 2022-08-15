package com.tuti.api.data;

import com.google.gson.annotations.SerializedName;
import com.tuti.api.ebs.EBSResponse;

import java.io.Serializable;

/**
 * PaymentToken represents a payment order that a user generates such that it can be charged back
 */
public class PaymentToken implements Serializable {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public String getPaymentNote() {
        return paymentNote;
    }

    public void setPaymentNote(String paymentNote) {
        this.paymentNote = paymentNote;
    }

    public String getToCard() {
        return toCard;
    }

    public void setToCard(String toCard) {
        this.toCard = toCard;
    }

    public EBSResponse getTransaction() {
        return transaction;
    }

    public void setTransaction(EBSResponse transaction) {
        this.transaction = transaction;
    }

    @SerializedName("cart_id")
    private String cartId;

    @SerializedName("UUID")
    private String Uuid;

    @SerializedName("note")
    private String paymentNote;
    private String toCard;
    private EBSResponse transaction;

}
