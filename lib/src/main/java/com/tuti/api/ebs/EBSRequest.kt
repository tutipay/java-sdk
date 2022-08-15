package com.tuti.api.ebs


import com.google.gson.annotations.SerializedName
import com.tuti.api.ebs.PaymentDetails
import java.text.DateFormat
import java.text.SimpleDateFormat
import com.tuti.api.ebs.LastTransactions
import com.tuti.model.PayeeID
import java.text.NumberFormat
import java.text.DecimalFormat
import com.tuti.api.ebs.ErrorDetails
import com.tuti.api.ebs.EBSResponse
import com.tuti.api.data.PaymentToken
import com.google.gson.Gson
import com.tuti.api.data.Fees
import com.tuti.api.authentication.SignInRequest
import com.tuti.api.TutiApiClient.ResponseCallable
import com.tuti.api.authentication.SignInResponse
import com.tuti.api.TutiApiClient.ErrorCallable
import com.tuti.api.data.TutiResponse
import com.tuti.api.data.RequestMethods
import com.tuti.model.Operations
import com.tuti.api.authentication.SignUpRequest
import com.tuti.api.authentication.SignUpResponse
import com.tuti.api.ebs.EBSRequest
import com.tuti.api.data.Cards
import java.lang.Runnable
import okhttp3.OkHttpClient
import com.tuti.api.TutiApiClient
import okhttp3.RequestBody
import com.tuti.api.data.ResponseData
import kotlin.Throws
import java.io.IOException
import kotlin.jvm.Volatile
import okhttp3.logging.HttpLoggingInterceptor
import java.io.Serializable
import java.util.*
import java.util.concurrent.TimeUnit

class EBSRequest : Serializable {
    @SerializedName("UUID")
    val uuid = generateUUID()
    private val tranDateTime = date
    private val applicationId = "TutiPay"
    var authenticationType: String? = null
    var last4PanDigits: String? = null
    var listSize = 0
    var mobileNo: String? = null
    var merchantAccountType: String? = null
    var merchantAccountReference: String? = null
    var merchantName: String? = null
    var merchantCity: String? = null
    var idType: String? = null
    var idNo: String? = null
    var merchantCategoryCode: String? = null
    private val postalCode: String? = null
    fun setPanCategory(panCategory: String?) {
        this.panCategory = panCategory
    }

    private var panCategory: String? = null

    @SerializedName("PAN")
    private var pan: String? = null
    var expDate: String? = null
    private var IPIN: String? = null
    private var newIPIN: String? = null
    var originalTranUUID: String? = null
    var otp: String? = null
    var ipin: String? = null
    var entityId: String? = null
    var voucherNumber: String? = null
    private var tranAmount: Float? = null
    private var tranCurrencyCode: String? = null
    var userName: String? = null
    var password: String? = null
    var userPassword: String? = null
    var tranCurrency: String? = null
    private var toCard: String? = null
    private var toAccount: String? = null
    private var payeeId: String? = null
    private var paymentInfo: String? = null
    private var serviceProviderId: String? = null
    var merchantID: String? = null

    @SerializedName("token")
    var quickPayToken: String? = null

    @SerializedName("paymentDetails")
    var paymentDetailsList: List<PaymentDetails>? = null
    var qRCode: String? = null
    var phoneNo: String? = null
    var origUUID: String? = null
    var origTranID: String? = null
    var phoneNumber: String? = null

    @SerializedName("pan")
    var otherPan: String? = null
    fun setDynamicFees(dynamicFees: Float) {
        this.dynamicFees = dynamicFees
    }

    private var dynamicFees = 0f
    var entityType = "Phone No"
    private val entityGroup = "0"
    var registrationType = "01"
    var originalTransactionId: String? = null
    fun setServiceProviderId(serviceProviderId: String?) {
        this.serviceProviderId = serviceProviderId
    }

    fun setToAccount(toAccount: String?) {
        this.toAccount = toAccount
    }

    fun setPayeeId(payeeId: String?) {
        this.payeeId = payeeId
    }

    fun setPaymentInfo(paymentInfo: String?) {
        this.paymentInfo = paymentInfo
    }

    fun setIPIN(IPIN: String?) {
        this.IPIN = IPIN
    }

    fun setNewIPIN(newIPIN: String?) {
        this.newIPIN = newIPIN
    }

    fun setToCard(toCard: String?) {
        this.toCard = toCard
    }

    fun setPan(pan: String?) {
        this.pan = pan
    }

    fun setTranAmount(tranAmount: Float?) {
        this.tranAmount = tranAmount
    }

    fun setTranCurrencyCode(tranCurrencyCode: String?) {
        this.tranCurrencyCode = tranCurrencyCode
    }

    val date: String
        get() {
            val dateFormat: DateFormat = SimpleDateFormat("ddMMyyHHmmss", Locale.US)
            val date = Date()
            return dateFormat.format(date)
        }

    fun generateUUID(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }
}