package com.tuti.model

import com.tuti.api.data.PaymentToken
import com.tuti.api.ebs.EBSResponse
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SignInRequest(
        @SerialName("password")
        val password: String? = "",
        @SerialName("otp")
        val otp: String? = "",
        @SerialName("mobile")
        val mobile: String,
        @SerialName("authorization")
        val auth: String? = "",
        @SerialName("signature")
        val signature: String? = "",
        val message: String? = "",
)

@kotlinx.serialization.Serializable
data class SignupRequest(
        @SerialName("password")
        val password: String,
        @SerialName("otp")
        val otp: String? = "",
        @SerialName("mobile")
        val mobile: String,
        @SerialName("authorization")
        val auth: String? = "",
        @SerialName("signature")
        val signature: String? = "",
        val message: String? = "",
        val fullname: String,
        val password2: String? = "",
        @SerialName("user_pubkey")
        val pubkey: String,
)


@kotlinx.serialization.Serializable
data class GenerateOTP(
        val mobile: String,
        val password: String? = "",
        @SerialName("fullname") val name: String? = "",
        @SerialName("user_pubkey") val pubkey: String,
)


@kotlinx.serialization.Serializable
data class Notification(
        @SerialName("phone") val phone: String?,
        val type: String?,
        val to: String?,
        val body: String?,
        val date: Long?,
        val title: String?,
        val data: EBSResponse?,
        @SerialName("UUID") val uuid: String?,
        @SerialName("is_read") val isRead: Boolean?,
        @SerialName("call_to_action") val callToAction: String?,
        @SerialName("payment_request") val paymentToken: PaymentToken?,
)

@kotlinx.serialization.Serializable
data class Notifications (
        val notifications: List<Notification> = emptyList()
)
data class NotificationFilters(
        val mobile: String,
        val getAll: Boolean = false,
)