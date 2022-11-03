package com.tuti.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BillInfo implements Serializable {
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getDeclarantCode() {
        return declarantCode;
    }

    public void setDeclarantCode(String declarantCode) {
        this.declarantCode = declarantCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(String payeeId) {
        this.payeeId = payeeId;
    }

    public String getFormKind() {
        return formKind;
    }

    public void setFormKind(String formKind) {
        this.formKind = formKind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     * 	Phone         string `json:"phone"`
     * 	Ref           string `json:"ref"`
     * 	SeatNumber    string `json:"seat_number"`
     * 	CourseID      string `json:"course_id"`
     * 	FormKind      string `json:"form_kind"`
     * 	Name          string `json:"name"`
     * 	Bank          string `json:"bank"`
     * 	DeclarantCode string `json:"code"`
     * 	InvoiceNumber string `json:"invoice"`
     * 	PayeeID       string `json:"payee_id"`
     * 	ServiceID     string `json:"service_id"`
    */
    private String phone;
    private String ref;

    @SerializedName("seat_number")
    private String seatNumber;

    @SerializedName("course_id")
    private String courseId;
    private String bank;

    @SerializedName("declarant_code")
    private String declarantCode;

    @SerializedName("service_id")
    private String serviceId;

    @SerializedName("payee_id")
    private String payeeId;

    @SerializedName("form_kind")
    private String formKind;

    private String name;

    @SerializedName("invoice")
    private String invoiceNumber;
}
