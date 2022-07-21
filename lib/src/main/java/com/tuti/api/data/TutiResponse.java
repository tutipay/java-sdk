package com.tuti.api.data;

import com.google.gson.annotations.SerializedName;
import com.tuti.api.ebs.EBSResponse;

/**
 * TutiResponse class the encapsulates all noebs responses. Reference class to get
 * values for about any field
 */
public class TutiResponse {
    private String message;
    private String code;

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
