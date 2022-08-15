package com.tuti.api.ebs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class EBSRequest implements Serializable {

    @SerializedName("UUID")
    private final String uuid = generateUUID();
    private final String tranDateTime = getDate();
    private final String applicationId = "TutiPay";

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    private String authenticationType;

    public String getLast4PanDigits() {
        return last4PanDigits;
    }

    public void setLast4PanDigits(String last4PanDigits) {
        this.last4PanDigits = last4PanDigits;
    }

    private String last4PanDigits;

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    private int listSize;

    public String getMerchantAccountType() {
        return merchantAccountType;
    }

    public void setMerchantAccountType(String merchantAccountType) {
        this.merchantAccountType = merchantAccountType;
    }

    public String getMerchantAccountReference() {
        return merchantAccountReference;
    }

    public void setMerchantAccountReference(String merchantAccountReference) {
        this.merchantAccountReference = merchantAccountReference;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCity() {
        return merchantCity;
    }

    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    private String mobileNo, merchantAccountType, merchantAccountReference, merchantName, merchantCity, idType, idNo, merchantCategoryCode, postalCode;
    public void setPanCategory(String panCategory) {
        this.panCategory = panCategory;
    }

    private String panCategory;

    @SerializedName("PAN")
    private String pan;

    private String expDate, IPIN, newIPIN, originalTranUUID, otp, ipin,  entityId, voucherNumber;
    private Float tranAmount;
    private String tranCurrencyCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userName;
    private String password;

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    private String userPassword;

    public String getTranCurrency() {
        return tranCurrency;
    }

    public void setTranCurrency(String tranCurrency) {
        this.tranCurrency = tranCurrency;
    }

    private String tranCurrency;
    private String toCard;
    private String toAccount;
    private String payeeId;
    private String paymentInfo;
    private String serviceProviderId;
    private String merchantID;


    public String getQuickPayToken() {
        return quickPayToken;
    }

    public void setQuickPayToken(String quickPayToken) {
        this.quickPayToken = quickPayToken;
    }

    @SerializedName("token")
    private String quickPayToken;

    public List<PaymentDetails> getPaymentDetailsList() {
        return paymentDetailsList;
    }

    public void setPaymentDetailsList(List<PaymentDetails> paymentDetailsList) {
        this.paymentDetailsList = paymentDetailsList;
    }

    @SerializedName("paymentDetails")
    private List<PaymentDetails> paymentDetailsList;

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    private String QRCode;

    private String phoneNo;

    public String getOrigUUID() {
        return origUUID;
    }

    public void setOrigUUID(String origUUID) {
        this.origUUID = origUUID;
    }

    public String getOrigTranID() {
        return origTranID;
    }

    public void setOrigTranID(String origTranID) {
        this.origTranID = origTranID;
    }

    private String origUUID;
    private String origTranID;
    private String phoneNumber;

    @SerializedName("pan")
    private String otherPan;

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public void setDynamicFees(float dynamicFees) {
        this.dynamicFees = dynamicFees;
    }

    private float dynamicFees;

    private String entityType = "Phone No";
    private final String entityGroup = "0";
    private String registrationType = "01";
    public  void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getOriginalTransactionId() {
        return originalTransactionId;
    }

    public void setOriginalTransactionId(String originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }

    private String originalTransactionId;

    public void setServiceProviderId(String serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void setIPIN(String IPIN) {
        this.IPIN = IPIN;
    }

    public void setMerchantID(String id) {
        this.merchantID = id;
    }

    public void setNewIPIN(String newIPIN) {
        this.newIPIN = newIPIN;
    }

    public void setToCard(String toCard) {
        this.toCard = toCard;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getExpDate() {
        return this.expDate;
    }

    public void setTranAmount(Float tranAmount) {
        this.tranAmount = tranAmount;
    }

    public void setTranCurrencyCode(String tranCurrencyCode) {
        this.tranCurrencyCode = tranCurrencyCode;
    }

    public String getUuid() {
        return uuid;
    }

    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss", Locale.US);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getIpin() {
        return ipin;
    }

    public void setIpin(String ipin) {
        this.ipin = ipin;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public String getOriginalTranUUID() {
        return originalTranUUID;
    }

    public void setOriginalTranUUID(String originalTranUUID) {
        this.originalTranUUID = originalTranUUID;
    }

    public String getOtherPan() {
        return otherPan;
    }

    public void setOtherPan(String otherPan) {
        this.otherPan = otherPan;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}


