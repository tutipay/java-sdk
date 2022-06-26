package com.tuti.api.data;

import com.google.gson.annotations.SerializedName;
import com.tuti.api.ebs.EBSResponse;

public class TutiResponse {
    private String message;

    private String code;

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
}
