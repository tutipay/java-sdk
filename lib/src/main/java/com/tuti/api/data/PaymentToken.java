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

    /**
     * Use getTransaction to get the inner transaction response we get from EBS
     * @return
     */
    public EBSResponse getTransaction() {
        return transaction;
    }

    public boolean isPaymentSuccessful() {
        return isPaid;
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

    public String getCardTobePaid() {
        return cardTobePaid;
    }

    public void setCardTobePaid(String cardTobePaid) {
        this.cardTobePaid = cardTobePaid;
    }

    @SerializedName("toCard")
    private String cardTobePaid;
    private EBSResponse transaction;

    @SerializedName("is_paid")
    private boolean isPaid;

}
