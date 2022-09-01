package com.tuti.api.ebs;

import com.tuti.model.PayeeID;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EBSResponse implements Serializable {

    private String responseMessage;

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    private String responseStatus;
    private Integer responseCode;
    private String tranDateTime;
    private String terminalId;
    private Integer systemTraceAuditNumber;
    private String clientId;
    private String PAN;
    private String expDate;
    private Float tranAmount;
    private String EBSServiceName;
    private String workingKey;
    private String toCard;
    private String toAccount;
    private String referenceNumber;
    private String approvalCode;
    private Float tranFee;
    private Float additionalAmount;
    private Float acqTranFee;
    private Float issuerTranFee;
    private String pubKeyValue;
    private String tranCurrency;
    private String paymentInfo;
    private String fromAccount;
    private String financialInstitutionId;

    private String UUID;
    private String merchantAccountType;
    private String merchantAccountReference;
    private String merchantName;
    private String merchantCity;
    private String merchantID;
    private String generatedQR;
    private String merchantCategoryCode;
    private String postalCode;
    private String currencyCode;

    public List<LastTransactions> getLastTransactions() {
        return lastTransactions;
    }

    public void setLastTransactions(List<LastTransactions> lastTransactions) {
        this.lastTransactions = lastTransactions;
    }

    private List<LastTransactions> lastTransactions;

    public String getFinancialInstitutionId() {
        return financialInstitutionId;
    }

    public void setFinancialInstitutionId(String financialInstitutionId) {
        this.financialInstitutionId = financialInstitutionId;
    }

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

    public String getGeneratedQR() {
        return generatedQR;
    }

    public void setGeneratedQR(String generatedQR) {
        this.generatedQR = generatedQR;
    }

    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }

    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    private String countryCode;

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    private String QRCode;

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    private String transactionId;

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    private String voucherNumber;
    private String voucherCode;

    private String message;
    private String code, mbr;

    private ErrorMessage errorMessage;

    private HashMap<String, Double> balance;

    public void setBillInfo(HashMap<String, String> billInfo) {
        this.billInfo = billInfo;
    }

    private HashMap<String, String> billInfo;


    /**
     * @return the due amount for the payeeI
     * @param payeeId the payeeId to get the due amount for
     * @return
     */
    public String getDueAmount(PayeeID payeeId) {
        switch (payeeId){
            case Zain: // zain
                return billInfo.get("totalAmount"); // FIXME(adonese): Zain also has an `unbilledAmount` field like mtn but we are using totalAmount here just for testing
            case MTN: // mtn
                return billInfo.get("unbilledAmount"); // FIXME(adonese): This doesn't seem to be correct..
            case Sudani: //sudani
                return billInfo.get("billAmount");
            case Invoice: // e-invoice
                return billInfo.get("amount_due");
            case Mohe: // mohe
            case MoheArab: // mohe-arab
                return billInfo.get("dueAmount");
            case Customs: // Customs
                return billInfo.get("AmountToBePaid");
            case E15: // e-15
                return billInfo.get("TotalAmount");
            default:
                return "";
        }
    }

    public EBSResponse() {
    }

    public HashMap<String, String> getBillInfo() {
        return billInfo;
    }

    public HashMap<String, Double> getBalance() {
        return balance;
    }

    /**
     * get the current balance available to noebs user
     * @return
     */
    public String getAvailableBalance() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        try {
            return formatter.format(this.getBalance().get("available"));
        }catch (Exception e) {
            return "0";
        }
    }

    /**
     * getLeger or the balance that is yet to be reconciled with noebs user
     * @return
     */
    public String getLeger() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        try {
            return formatter.format(this.getBalance().get("leger"));
        }catch (Exception e) {
            return "0";
        }
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getTranCurrency() {
        return tranCurrency;
    }

    public Float getAcqTranFee() {
        return acqTranFee;
    }

    public Float getIssuerTranFee() {
        return issuerTranFee;
    }

    public String getPubKeyValue() {
        return pubKeyValue;
    }

    public Float getAdditionalAmount() {
        return additionalAmount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public Float getTranFee() {
        return tranFee;
    }

    public String getToCard() {
        return toCard;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setTranDateTime(String tranDateTime) {
        this.tranDateTime = tranDateTime;
    }

    public String getTranDateTime() {
        // we have to set the locale as this will fail in a non en-US ones!
        Date data = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        if (this.tranDateTime == null ) {
            // create time from this moment
            data = new Date();
            // Always return a result, never fail!
            return fmt.format(data);
        }
        try {
            Date date = format.parse(this.tranDateTime);
            data = date;
        } catch (ParseException e) {
            e.printStackTrace();
            data = new Date();
            // Always return a result, never fail!
            return fmt.format(data);
        }
        return fmt.format(data);

    }

    public String getTerminalId() {
        return terminalId;
    }

    public Integer getSystemTraceAuditNumber() {
        return systemTraceAuditNumber;
    }

    public String getClientId() {
        return clientId;
    }

    public String getPAN() {
        return PAN;
    }

    public Float getTranAmount() {
        return tranAmount;
    }
    public String dynamicFees;

    public String getEBSServiceName() {
        return EBSServiceName;
    }

    public String getWorkingKey() {
        return workingKey;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getEbsError() {
        return errorMessage.getEbsMessage();
    }

    public Integer getEbsCode() {
        return errorMessage.getEbsCode();
    }

    /**
     * @param code error code, get it from onError error.getErrorCode()
     * @return String text of the error that occurred.
     * <p>
     * This method is particularly interesting in that it does magic things:
     * - it checks if the error code is *NOT* zero (to handle ebs case)
     * - the other case will be for non-ebs errors.
     */
    public String getError(Integer code) {
        if (code >= 400 && code < 500) {
            // a validation error - and other form of errors
            return this.getCode();
        } else {
            return errorMessage.getMessage();
        }
    }

    /*
     * A helper functions that works for both classes of errors; whether they are ebs or not
     */
    public Integer getEbsCode(Integer code) {
        if (errorMessage.isEbsError()) {
            return errorMessage.getEbsCode();
        } else {
            return errorMessage.getCode();
        }
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getMbr() {
        return mbr;
    }

    public void setMbr(String mbr) {
        this.mbr = mbr;
    }


    public String getDynamicFees() {
        return dynamicFees;
    }

    public void setDynamicFees(String dynamicFees) {
        this.dynamicFees = dynamicFees;
    }
}


/**
 * This class implements (marshals) noebs *all* error classes
 * whether they were stemmed from EBS errors, or other kind of errors
 */
class ErrorMessage implements Serializable{
    private String message;
    private Integer code;
    private String status;
    private ErrorDetails details;

    public ErrorMessage(Integer code) {

    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    String getEbsMessage() {
        return details.getResponseMessage();
    }

    Integer getEbsCode() {
        return details.getResponseCode();
    }

    public String getEbsStatus() {
        return details.getResponseStatus();
    }

    boolean isEbsError() {
        if (details.getResponseCode() == null) {
            return false;
        } else {
            return details.getResponseCode() != 0;
        }
    }
}

class ErrorDetails implements Serializable {
    private String responseMessage;
    private String responseStatus;
    private Integer responseCode;

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public Integer getResponseCode() {
        return responseCode;
    }
}

// there's also a one for validations errors, ones of 400 error code