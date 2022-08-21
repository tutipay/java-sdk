package com.tuti.api.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tuti.api.ebs.EBSResponse;

import java.io.Reader;
import java.io.StringReader;
import java.util.Base64;

import java.io.Serializable;

/**
 * PaymentToken represents a payment order that a user generates such that it can be charged back
 */
public class PaymentToken implements Serializable {
    private int amount;
    private String token;

    /**
     * ParseQRToken creates a PaymentToken object from a base64 encoded QR code. This is method
     * can be used when showing a tuti encoded QR token to a user, the user can then scan that
     * and creates a [PaymentToken] object from the QR.
     * @param b64Token
     * @return
     */
    public static PaymentToken ParseQRToken(String b64Token) {
        byte[] parsedToken = Base64.getDecoder().decode(b64Token);
        Gson gson = new Gson();

        Reader targetReader = new StringReader(new String(parsedToken));
        try {
            PaymentToken pt =  gson.fromJson(targetReader, PaymentToken.class);
            targetReader.close();
            return pt;
        } catch (Exception e) {
            return null;
        }
    }

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
