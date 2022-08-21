package com.tuti.api.ebs;

import com.google.gson.annotations.SerializedName;
import com.sun.jersey.core.util.Base64;

import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EBSRequest implements Serializable {

    @SerializedName("UUID")
    private final String uuid = generateUUID();
    private final String tranDateTime = getDate();
    private final String applicationId = "TutiPay";

    private String pubKey;

    public EBSRequest(String pubKey) {
        this.pubKey = pubKey;
        setEncryptedIPIN(pubKey);
    }

    public EBSRequest() {

    }

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

    /**
     * This is used to pass-on a payment token (scanned via eg QR)
     * @param quickPayToken
     */
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

    public void setEncryptedIPIN(String pubKey) {
        this.IPIN = getIPINBlock(ipin, pubKey, this.uuid);
    }

        private String getIPINBlock(String ipin,
                                          String publicKey, String uuid) {
            // clear ipin = uuid +  IPIN
            String cleraIpin = uuid + ipin;

            // prepare public key, get public key from its String representation as
            // base64
            byte[] keyByte = Base64.decode(publicKey);
            // generate public key
            X509EncodedKeySpec s = new X509EncodedKeySpec(keyByte);
            KeyFactory factory = null;
            try {
                factory = KeyFactory.getInstance("RSA");
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Key pubKey = null;
            try {
                pubKey = factory.generatePublic(s);
            } catch (InvalidKeySpecException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                // construct Cipher with encryption algrithm:RSA, cipher mode:ECB and padding:PKCS1Padding
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                // calculate ipin, encryption then encoding to base64
                ipin = (new String(Base64.encode(cipher.doFinal(cleraIpin
                        .getBytes()))));
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (BadPaddingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return ipin;
        }

}


