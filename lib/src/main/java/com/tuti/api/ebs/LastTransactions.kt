package com.tuti.api.ebs;

import java.io.Serializable;

public class LastTransactions implements Serializable {

    private String merchantAccountType;
    private float responseCode;
    private String transactionId;
    private String merchantID;
    private String issuerTranFee;
    private String applicationId;
    private String tranType;
    private String authenticationType;
    private String merchantAccountExpDate;
    private String pan;
    private String tranDateTime;
    private String responseMessage;
    private String merchantAccountReference;
    private float tranAmount;
    private String responseStatus;
    private String acqTranFee;
    private String merchantName;
    private String uuid;
    private String merchantCity;
    private String merchantMobileNo;


    // Getter Methods

    public String getMerchantAccountType() {
        return merchantAccountType;
    }

    public float getResponseCode() {
        return responseCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getIssuerTranFee() {
        return issuerTranFee;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getTranType() {
        return tranType;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public String getMerchantAccountExpDate() {
        return merchantAccountExpDate;
    }

    public String getPan() {
        return pan;
    }

    public String getTranDateTime() {
        return tranDateTime;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getMerchantAccountReference() {
        return merchantAccountReference;
    }

    public float getTranAmount() {
        return tranAmount;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public String getAcqTranFee() {
        return acqTranFee;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getMerchantCity() {
        return merchantCity;
    }

    public String getMerchantMobileNo() {
        return merchantMobileNo;
    }

    // Setter Methods

    public void setMerchantAccountType(String merchantAccountType) {
        this.merchantAccountType = merchantAccountType;
    }

    public void setResponseCode(float responseCode) {
        this.responseCode = responseCode;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public void setIssuerTranFee(String issuerTranFee) {
        this.issuerTranFee = issuerTranFee;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public void setMerchantAccountExpDate(String merchantAccountExpDate) {
        this.merchantAccountExpDate = merchantAccountExpDate;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setTranDateTime(String tranDateTime) {
        this.tranDateTime = tranDateTime;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setMerchantAccountReference(String merchantAccountReference) {
        this.merchantAccountReference = merchantAccountReference;
    }

    public void setTranAmount(float tranAmount) {
        this.tranAmount = tranAmount;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void setAcqTranFee(String acqTranFee) {
        this.acqTranFee = acqTranFee;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    public void setMerchantMobileNo(String merchantMobileNo) {
        this.merchantMobileNo = merchantMobileNo;
    }

}
