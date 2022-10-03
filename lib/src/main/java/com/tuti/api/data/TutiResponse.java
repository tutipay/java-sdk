package com.tuti.api.data;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tuti.api.ebs.EBSResponse;

import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Base64;

/**
 * TutiResponse class the encapsulates all noebs responses. Reference class to get
 * values for about any field
 */
public class TutiResponse {
    private String message;
    private String code, uuid;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // getEncodedQRToken returns a base64 encode of a generated
    public byte[] getEncodedQRToken() {
        return Base64.getDecoder().decode(result);
    }

    //getRawPaymentToken returns a base64 encoded representation for the payment token.
    // a user can either decode to extract payment info, or use GET /payment_token with
    // the companion uuid to get server's inquiry about the id
    public String getRawPaymentToken() {
        return result;
    }

    // getPaymentTokenUUID in payment token api, we use uuid field to denote
    // the payment token
    public String getPaymentTokenUUID() {
        return uuid;
    }

    /**
     *  getQRToken is a helper method to parse `result` or payment token `token` into a PaymentToken
     *     class that can be used to inquire about the payment details.
     */
    public PaymentToken getQRToken() {
        Gson gson = new Gson();
        byte[] initialArray =this.getEncodedQRToken();
        Reader targetReader = new StringReader(new String(initialArray));
        try {
            PaymentToken pt =  gson.fromJson(targetReader, PaymentToken.class);
            targetReader.close();
            return pt;
        } catch (Exception e) {
            return null;
        }
    }
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUuid() {
        return uuid;
    }

    public String getToken() {
        return token;
    }

    private String token;

    private String result;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    private String authorization;

    public String getStatus() {
        return status;
    }

    // get EBS public key for IPIN generation
    public String getiPINKey() {
        return this.getEbsResponse().getPubKeyValue();
    }

    /**
     * get EBS public key for encryption (used throughout the code to encrypt all transactions except for IPIN generation. Check {@link #getiPINKey()} instead
     */
    public String getKey() {
        return this.getEbsResponse().getPubKeyValue();
    }


    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public DueAmount getDueAmount() {
        return dueAmount;
    }

    @SerializedName("due_amount")
    private DueAmount dueAmount;



    private Fees fees;
    @SerializedName("ebs_response")
    private EBSResponse ebsResponse;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EBSResponse getEbsResponse() {
        return ebsResponse;
    }

    public void setEbsResponse(EBSResponse ebsResponse) {
        this.ebsResponse = ebsResponse;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }
}


