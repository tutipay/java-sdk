package com.tuti.api.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class DueAmount (
    @SerialName("due_amount")
    val dueAmount: String? = null,

    @SerialName("total_amount")
    val totalAmount: String? = null,

    @SerialName("min_amount")
    val minAmount: String? = null,

    @SerialName("paid_amount")
    val paidAmount: String? = null,
)