package com.tuti.model

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class BillInfo (
    /**
     * Phone         string `json:"phone"`
     * Ref           string `json:"ref"`
     * SeatNumber    string `json:"seat_number"`
     * CourseID      string `json:"course_id"`
     * FormKind      string `json:"form_kind"`
     * Name          string `json:"name"`
     * Bank          string `json:"bank"`
     * DeclarantCode string `json:"code"`
     * InvoiceNumber string `json:"invoice"`
     * PayeeID       string `json:"payee_id"`
     * ServiceID     string `json:"service_id"`
     */
    val phone: String? = null,
    val ref: String? = null,

    @SerialName("seat_number")
    val seatNumber: String? = null,

    @SerialName("course_id")
    val courseId: String? = null,
    val bank: String? = null,

    @SerialName("declarant_code")
    val declarantCode: String? = null,

    @SerialName("service_id")
    val serviceId: String? = null,

    @SerialName("payee_id")
    val payeeId: String? = null,

    @SerialName("form_kind")
    val formKind: String? = null,
    val name: String? = null,

    @SerialName("invoice")
    val invoiceNumber: String? = null,
)