package com.tuti.api.ebs

import okhttp3.Headers.get
import okhttp3.logging.HttpLoggingInterceptor.setLevel
import okhttp3.OkHttpClient.Builder.addInterceptor
import okhttp3.OkHttpClient.Builder.connectTimeout
import okhttp3.OkHttpClient.Builder.readTimeout
import okhttp3.OkHttpClient.Builder.writeTimeout
import okhttp3.OkHttpClient.Builder.build
import okhttp3.Request.Builder.url
import okhttp3.Request.Builder.header
import okhttp3.Request.Builder.post
import okhttp3.Request.Builder.delete
import okhttp3.Request.Builder.put
import okhttp3.Request.Builder.get
import okhttp3.Request.Builder.build
import okhttp3.OkHttpClient.newCall
import okhttp3.Call.execute
import okhttp3.Response.code
import okhttp3.Response.body
import okhttp3.ResponseBody.string
import okhttp3.Response.headers
import com.google.gson.annotations.SerializedName
import com.tuti.api.ebs.PaymentDetails
import com.tuti.api.ebs.LastTransactions
import com.tuti.model.PayeeID
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
import java.lang.Exception
import java.text.*
import java.util.*
import java.util.concurrent.TimeUnit

class EBSResponse : Serializable {
    val responseMessage: String? = null
    var responseStatus: String? = null
    val responseCode: Int? = null

    // Always return a result, never fail!
    // we have to set the locale as this will fail in a non en-US ones!
    var tranDateTime: String? = null
        get() {
            // we have to set the locale as this will fail in a non en-US ones!
            var data: Date? = Date()
            val fmt = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
            val format = SimpleDateFormat("ddMMyyHHmmss")
            if (field == null) {
                // create time from this moment
                data = Date()
                // Always return a result, never fail!
                return fmt.format(data)
            }
            try {
                val date = format.parse(field)
                data = date
            } catch (e: ParseException) {
                e.printStackTrace()
                data = Date()
                // Always return a result, never fail!
                return fmt.format(data)
            }
            return fmt.format(data)
        }
    val terminalId: String? = null
    val systemTraceAuditNumber: Int? = null
    val clientId: String? = null
    val pAN: String? = null
    val expDate: String? = null
    val tranAmount: Float? = null
    val eBSServiceName: String? = null
    val workingKey: String? = null
    val toCard: String? = null
    val toAccount: String? = null
    val referenceNumber: String? = null
    val approvalCode: String? = null
    val tranFee: Float? = null
    val additionalAmount: Float? = null
    val acqTranFee: Float? = null
    val issuerTranFee: Float? = null
    val pubKeyValue: String? = null
    val tranCurrency: String? = null
    val paymentInfo: String? = null
    var fromAccount: String? = null
    var financialInstitutionId: String? = null
    var uUID: String? = null
    var merchantAccountType: String? = null
    var merchantAccountReference: String? = null
    var merchantName: String? = null
    var merchantCity: String? = null
    var merchantID: String? = null
    var generatedQR: String? = null
    var merchantCategoryCode: String? = null
    var postalCode: String? = null
    var currencyCode: String? = null
    var lastTransactions: List<LastTransactions>? = null
    var countryCode: String? = null
    var qRCode: String? = null
    var transactionId: String? = null
    var voucherNumber: String? = null
    var voucherCode: String? = null
    val message: String? = null
    val code: String? = null
    var mbr: String? = null
    private val errorMessage: ErrorMessage? = null
    val balance: HashMap<String, Double>? = null
    var billInfo: HashMap<String, String>? = null

    /**
     * @return the due amount for the payeeI
     * @param payeeId the payeeId to get the due amount for
     * @return
     */
    fun getDueAmount(payeeId: PayeeID?): String {
        return when (payeeId) {
            PayeeID.Zain -> billInfo!!["totalAmount"].toString() // FIXME(adonese): Zain also has an `unbilledAmount` field like mtn but we are using totalAmount here just for testing
            PayeeID.MTN -> billInfo!!["unbilledAmount"].toString() // FIXME(adonese): This doesn't seem to be correct..
            PayeeID.Sudani -> billInfo!!["billAmount"].toString()
            PayeeID.Invoice -> billInfo!!["amount_due"].toString()
            PayeeID.Mohe, PayeeID.MoheArab -> billInfo!!["dueAmount"].toString()
            PayeeID.Customs -> billInfo!!["AmountToBePaid"].toString()
            PayeeID.E15 -> billInfo!!["TotalAmount"].toString()
            else -> ""
        }
    }

    val availableBalance: String
        get() {
            val formatter: NumberFormat = DecimalFormat("#0.00")
            return try {
                formatter.format(balance!!["available"])
            } catch (e: Exception) {
                "0"
            }
        }
    var dynamicFees: String? = null
    val ebsError: String?
        get() = errorMessage!!.ebsMessage
    val ebsCode: Int?
        get() = errorMessage!!.ebsCode

    /**
     * @param code error code, get it from onError error.getErrorCode()
     * @return String text of the error that occurred.
     *
     *
     * This method is particularly interesting in that it does magic things:
     * - it checks if the error code is *NOT* zero (to handle ebs case)
     * - the other case will be for non-ebs errors.
     */
    fun getError(code: Int): String? {
        return if (code >= 400 && code < 500) {
            // a validation error - and other form of errors
            this.code
        } else {
            errorMessage!!.message
        }
    }

    /*
     * A helper functions that works for both classes of errors; whether they are ebs or not
     */
    fun getEbsCode(code: Int?): Int? {
        return if (errorMessage!!.isEbsError) {
            errorMessage.ebsCode
        } else {
            errorMessage.code
        }
    }
}

/**
 * This class implements (marshals) noebs *all* error classes
 * whether they were stemmed from EBS errors, or other kind of errors
 */
internal class ErrorMessage(code: Int?) : Serializable {
    val message: String? = null
    val code: Int? = null
    private val status: String? = null
    private val details: ErrorDetails? = null
    val ebsMessage: String?
        get() = details!!.responseMessage
    val ebsCode: Int?
        get() = details!!.responseCode
    val ebsStatus: String?
        get() = details!!.responseStatus
    val isEbsError: Boolean
        get() = if (details!!.responseCode == null) {
            false
        } else {
            details.responseCode != 0
        }
}

internal class ErrorDetails : Serializable {
    val responseMessage: String? = null
    val responseStatus: String? = null
    val responseCode: Int? = null
}