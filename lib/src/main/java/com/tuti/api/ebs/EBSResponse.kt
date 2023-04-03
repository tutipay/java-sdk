package com.tuti.api.ebs

import com.tuti.model.PayeeID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat

val json = Json { ignoreUnknownKeys = true }

@Serializable
class EBSResponse {
    val responseMessage: String = ""
    var responseStatus: String = ""
    val responseCode: Int? = null

    @SerialName("tranDateTime")
    private val transactionDateTime: String? = null

    // Always return a result, never fail!
    // we have to set the locale as this will fail in a non en-US ones!
    @SerialName("aaa")
    val tranDateTime: String?
        get() {
            // we have to set the locale as this will fail in a non en-US ones!
            val fmt = SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
            val format = SimpleDateFormat("ddMMyyHHmmss")
            return try {
                val date = format.parse(transactionDateTime)
                fmt.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
                null
            } catch (e: NullPointerException) {
                e.printStackTrace()
                null
            }
        }


    val ID: Int = 0
    val terminalId: String? = null
    val systemTraceAuditNumber: Int? = null
    val clientId: String? = null
    val pan: String? = null
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
    val tranCurrency: String = ""

    @SerialName("paymentInfo")
    val paymentInfo1: String? = null

    @SerialName("bill_to")
    val paymentInfo2: String? = null

    @kotlinx.serialization.Transient
    val paymentInfo = paymentInfo1 ?: paymentInfo2 ?: ""

    var payeeId: String = ""
    var fromAccount: String = ""
    var financialInstitutionId: String = ""
    var UUID: String = ""
    var merchantAccountType: String = ""
    var merchantAccountReference: String = ""
    var merchantName: String? = null
    var merchantCity: String? = null
    var merchantID: String? = null
    var generatedQR: String? = null
    var merchantCategoryCode: String? = null
    var postalCode: String? = null
    var currencyCode: String = ""
    var lastTransactions: List<LastTransactions>? = null
    var countryCode: String = ""
    var qRCode: String = ""
    var transactionId: String = ""
    var voucherNumber: String? = null
    var voucherCode: String? = null
    val message: String = ""
    val code: String = ""
    var mbr: String? = null
    val errorMessage: ErrorMessage = ErrorMessage()
    val balance: HashMap<String, Double> = HashMap()

    @SerialName("billInfo")
    val billInfo1: Map<String, String>? = null

    @SerialName("bill_info2")
    val billInfo2String: String? = null
    val billInfo2 = billInfo2String?.let { json.parseToJsonElement(it).jsonObject.toStringMap() }

    @kotlinx.serialization.Transient
    val billInfo: Map<String, String>? = billInfo1 ?: billInfo2

    /**
     * @return the due amount for the payeeI
     * @param payeeId the payeeId to get the due amount for
     * @return
     */
    fun getDueAmount(payeeId: PayeeID?): String? {
        if (this.billInfo == null) {
            return ""
        }
        return when (payeeId) {
            PayeeID.ZainPostpaid -> billInfo["totalAmount"] // FIXME(adonese): Zain also has an `unbilledAmount` field like mtn but we are using totalAmount here just for testing
            PayeeID.MTNPostpaid -> billInfo["unbilledAmount"] // FIXME(adonese): This doesn't seem to be correct..
            PayeeID.SudaniPostpaid -> billInfo["billAmount"]
            PayeeID.Invoice -> billInfo["amount_due"]
            PayeeID.Mohe, PayeeID.MoheArab -> billInfo["dueAmount"]
            PayeeID.Customs -> billInfo["AmountToBePaid"]
            PayeeID.E15 -> billInfo["TotalAmount"]
            else -> ""
        }
    }

    /**
     * get the current balance available to noebs user
     * @return
     */
    val availableBalance: String
        get() {
            val formatter: NumberFormat = DecimalFormat("#0.00")
            return try {
                formatter.format(balance["available"])
            } catch (e: Exception) {
                "0"
            }
        }

    /**
     * getLeger or the balance that is yet to be reconciled with noebs user
     * @return
     */
    val leger: String
        get() {
            val formatter: NumberFormat = DecimalFormat("#0.00")
            return try {
                formatter.format(balance["leger"])
            } catch (e: Exception) {
                "0"
            }
        }
    var dynamicFees: String? = null
    val ebsError: String
        get() = errorMessage.ebsMessage
    val ebsCode: Int?
        get() = errorMessage.ebsCode

    /**
     * @param code error code, get it from onError error.getErrorCode()
     * @return String text of the error that occurred.
     *
     *
     * This method is particularly interesting in that it does magic things:
     * - it checks if the error code is *NOT* zero (to handle ebs case)
     * - the other case will be for non-ebs errors.
     */
    fun getError(code: Int): String {
        return if (code >= 400 && code < 500) {
            // a validation error - and other form of errors
            this.code
        } else {
            errorMessage.message
        }
    }

    /*
     * A helper functions that works for both classes of errors; whether they are ebs or not
     */
    fun getEbsCode(code: Int?): Int? {
        return if (errorMessage.isEbsError) {
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

@Serializable
data class ErrorMessage(
    val message: String = "",
    val code: Int? = null,
    val status: String = "",
    val details: ErrorDetails? = null,

    ) {
    val ebsMessage: String
        get() = details!!.responseMessage
    val ebsCode: Int?
        get() = details!!.responseCode
    val ebsStatus: String
        get() = details!!.responseStatus
    val isEbsError: Boolean
        get() = if (details!!.responseCode == null) {
            false
        } else {
            details.responseCode != 0
        }
}

@Serializable
data class ErrorDetails(
    val responseMessage: String = "",
    val responseStatus: String = "",
    val responseCode: Int? = null,
)

fun JsonObject.toStringMap(): Map<String, String> {
    val map = mutableMapOf<String, String>()
    this.keys.forEach { key ->
        map[key] = this[key]?.jsonPrimitive?.contentOrNull ?: ""
    }
    return map
}