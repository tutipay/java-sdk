package com.tuti.api

import com.google.gson.Gson
import com.tuti.api.authentication.SignInRequest
import com.tuti.api.authentication.SignInResponse
import com.tuti.api.authentication.SignUpRequest
import com.tuti.api.authentication.SignUpResponse
import com.tuti.api.data.*
import com.tuti.api.ebs.EBSRequest
import com.tuti.api.ebs.EBSResponse
import com.tuti.model.BillInfo
import com.tuti.model.Operations
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class TutiApiClient {
    var serverURL: String? = null
        private set
    var isSingleThreaded = false
    var authToken: String = ""

    @Deprecated("")
    constructor(isDevelopment: Boolean) {
        serverURL = getServerURL(isDevelopment)
    }

    constructor(token: String) {
        authToken = token
    }

    constructor() {
        serverURL = getServerURL(false)
    }

    private fun getServerURL(development: Boolean): String {
        val developmentHost = "https://staging.app.2t.sd/api/consumer/"
        val productionHost = "https://staging.app.2t.sd/api/consumer/"
        return if (development) developmentHost else productionHost
    }

    fun SignIn(
        credentials: SignInRequest,
        onResponse: (SignInResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.SIGN_IN,
            credentials,
            onResponse,
            onError
        )
    }

    fun ChangePassword(
        credentials: SignInRequest?,
        onResponse: (SignInResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.ChangePassword,
            credentials,
            onResponse,
            onError,
        )
    }

    /**
     * OneTimeSignIn allows tutipay users to sign in via a code we send to their phone numbers
     * Notice: this method ONLY works for tutipay registered devices, at the moment
     * it doesn't support a sign in from a new device, as it relies on the user
     * signing a message via their private key
     *
     * @param credentials
     * @param onResponse
     * @param onError
     */
    fun OneTimeSignIn(
        credentials: SignInRequest?,
        onResponse: (SignInResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.SINGLE_SIGN_IN,
            credentials,
            onResponse,
            onError
        )
    }

    /**
     * GenerateOtpSignIn service used to request an otp to be sent to the user's registered sms phone number
     *
     * @param credentials
     * @param onResponse
     * @param onError
     */
    fun GenerateOtpSignIn(
        credentials: SignInRequest?,
        onResponse: (SignInResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.GENERATE_LOGIN_OTP,
            credentials,
            onResponse,
            onError
        )
    }

    /**
     * RefreshToken used to refresh an existing token to keep user's session valid.
     *
     * @param credentials
     * @param onResponse  a method that is used to handle successful cases
     * @param onError     a method to handle on error cases
     */
    fun RefreshToken(
        credentials: SignInRequest?,
        onResponse: (SignInResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.REFRESH_TOKEN,
            credentials,
            onResponse,
            onError
        )
    }

    /**
     * @param signUpRequest
     * @param onResponse
     * @param onError
     */
    fun Signup(
        signUpRequest: SignUpRequest?,
        onResponse: (SignUpResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.SIGN_UP,
            signUpRequest,
            onResponse,
            onError
        )
    }

    fun SignupWithCard(
        signUpRequest: Card?,
        onResponse: (SignUpResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.SIGN_UP_WITH_CARD,
            signUpRequest,
            onResponse,
            onError,
        )
    }

    /**
     * VerifyFirebase used to verify a verification ID token that was sent to a user. It sets is_activiated
     * flag as true for the selected user. This is basically an in-background operation, and as though it shouldn't
     * block the UI, nor does the implementation should care too much about the returned object.
     *
     * @param signUpRequest
     * @param onResponse
     * @param onError
     */
    fun VerifyFirebase(
        signUpRequest: SignUpRequest?,
        onResponse: (SignUpResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.VERIFY_FIREBASE,
            signUpRequest,
            onResponse,
            onError,
        )
    }

    fun sendEBSRequest(
        URL: String,
        ebsRequest: EBSRequest?,
        onResponse: (EBSResponse, ResponseData) -> Unit,
        onError: (EBSResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            URL,
            ebsRequest,
            onResponse,
            onError,
        )
    }

    fun getCards(
        onResponse: (Cards, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.GET,
            serverURL + Operations.GET_CARDS,
            null,
            onResponse,
            onError,
        )
    }

    fun getPublicKey(
        ebsRequest: EBSRequest?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.PUBLIC_KEY,
            ebsRequest,
            onResponse,
            onError,
        )
    }

    fun getIpinPublicKey(
        ebsRequest: Any?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.IPIN_key,
            ebsRequest,
            onResponse,
            onError
        )
    }

    fun addCard(
        card: Any?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.ADD_CARD,
            card,
            onResponse,
            onError,
        )
    }

    fun editCard(
        card: Card?,
        onResponse: (String, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit) {
        sendRequest(
            RequestMethods.PUT,
            serverURL + Operations.EDIT_CARD,
            card,
            onResponse,
            onError,
        )
    }

    fun deleteCard(
        card: Card?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.DELETE,
            serverURL + Operations.DELETE_CARD,
            card,
            onResponse,
            onError,
        )
    }

    fun billInquiry(
        request: Any?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.BILL_INQUIRY,
            request,
            onResponse,
            onError,
        )
    }

    /**
     * @param request
     * @param onResponse
     * @param onError
     */
    fun balanceInquiry(
        request: EBSRequest?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.GET_BALANCE,
            request,
            onResponse,
            onError,
        )
    }

    fun cardTransfer(
        request: EBSRequest?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.CARD_TRANSFER,
            request,
            onResponse,
            onError,
        )
    }

    /**
     * billInfo gets a bill from EBS using a pre-stored data, only send applicable bill fields
     * plus the type of transaction
     *
     * @param billInfo
     * @param onResponse
     * @param onError
     */
    fun billInquiry(
        billInfo: BillInfo?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.Get_Bills,
            billInfo,
            onResponse,
            onError,
        )
    }

    fun bashair(
        request: EBSRequest,
        bashairType: BashairTypes,
        paymentValue: String?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.Bashair.payeeID)
        request.setPaymentInfo(bashairType.bashairInfo(paymentValue!!))
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.BILL_PAYMENT,
            request,
            onResponse,
            onError,
        )
    }

    fun e15(
        request: EBSRequest,
        invoice: String?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.E15.payeeID)
        var operator = Operations.BILL_PAYMENT
        if (request.tranAmount == 0f) {
            operator = Operations.BILL_INQUIRY
        }
        request.setPaymentInfo(E15(request.tranAmount != 0f, invoice!!, ""))
        sendRequest(
            RequestMethods.POST,
            serverURL + operator,
            request,
            onResponse,
            onError
        )
    }

    fun customs(
        request: EBSRequest,
        bankCode: String?,
        declarantCode: String?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.CUSTOMS.payeeID)
        var operator = Operations.BILL_PAYMENT
        if (request.tranAmount == 0f) {
            operator = Operations.BILL_INQUIRY
        }
        request.setPaymentInfo(Customs(bankCode!!, declarantCode!!))
        sendRequest(
            RequestMethods.POST,
            serverURL + operator,
            request,
            onResponse,
            onError,
        )
    }

    fun moheArab(
        request: EBSRequest,
        courseId: CourseID?,
        admissionType: AdmissionType?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.CUSTOMS.payeeID)
        var operator = Operations.BILL_PAYMENT
        if (request.tranAmount == 0f) {
            operator = Operations.BILL_INQUIRY
        }
        request.setPaymentInfo(MOHEArab("", "", courseId!!, admissionType!!))
        sendRequest(
            RequestMethods.POST,
            serverURL + operator,
            request,
            onResponse,
            onError,
        )
    }

    fun mohe(
        request: EBSRequest,
        seatNumber: String?,
        courseId: CourseID?,
        admissionType: AdmissionType?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.CUSTOMS.payeeID)
        var operator = Operations.BILL_PAYMENT
        if (request.tranAmount == 0f) {
            operator = Operations.BILL_INQUIRY
        }
        request.setPaymentInfo(MOHE(seatNumber!!, courseId!!, admissionType!!))
        sendRequest(
            RequestMethods.POST,
            serverURL + operator,
            request,
            onResponse,
            onError
        )
    }

    fun einvoice(
        request: EBSRequest,
        customerRef: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.Einvoice.payeeID)
        request.setPaymentInfo("customerBillerRef=$customerRef")
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.BILL_PAYMENT,
            request,
            onResponse,
            onError
        )
    }

    fun mtnTopup(
        request: EBSRequest,
        mobile: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.MTN.payeeID)
        request.setPaymentInfo("MPHONE=$mobile")
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.BILL_PAYMENT,
            request,
            onResponse,
            onError,
        )
    }

    fun zainTopup(
        request: EBSRequest,
        mobile: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.ZAIN.payeeID)
        request.setPaymentInfo("MPHONE=$mobile")
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.BILL_PAYMENT,
            request,
            onResponse,
            onError,
        )
    }

    fun sudaniTopup(
        request: EBSRequest,
        mobile: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.SUDANI.payeeID)
        request.setPaymentInfo("MPHONE=$mobile")
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.BILL_PAYMENT,
            request,
            onResponse,
            onError
        )
    }

    fun nec(
        request: EBSRequest,
        meterNumber: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.NEC.payeeID)
        request.setPaymentInfo("METER=$meterNumber")
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.BILL_PAYMENT,
            request,
            onResponse,
            onError,
        )
    }

    fun mtnBill(
        request: EBSRequest,
        mobile: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.MTN_BILL.payeeID)
        var operator = Operations.BILL_PAYMENT
        if (request.tranAmount == 0f) {
            operator = Operations.BILL_INQUIRY
        }
        request.setPaymentInfo("MPHONE=$mobile")
        sendRequest(
            RequestMethods.POST,
            serverURL + operator,
            request,
            onResponse,
            onError,
        )
    }

    fun zainBill(
        request: EBSRequest,
        mobile: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.ZAIN_BILL.payeeID)
        var operator = Operations.BILL_PAYMENT
        if (request.tranAmount == 0f) {
            operator = Operations.BILL_INQUIRY
        }
        request.setPaymentInfo("MPHONE=$mobile")
        sendRequest(
            RequestMethods.POST,
            serverURL + operator,
            request,
            onResponse,
            onError,
        )
    }

    fun sudaniBill(
        request: EBSRequest,
        mobile: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        request.setPayeeId(TelecomIDs.SUDANI_BILL.payeeID)
        var operator = Operations.BILL_PAYMENT
        if (request.tranAmount == 0f) {
            operator = Operations.BILL_INQUIRY
        }
        request.setPaymentInfo("MPHONE=$mobile")
        sendRequest(
            RequestMethods.POST,
            serverURL + operator,
            request,
            onResponse,
            onError
        )
    }

    fun guessBillerId(
        mobile: String,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.GET,
            serverURL + Operations.GUESS_Biller,
            null,
            onResponse,
            onError,
            null,
            "mobile",
            mobile
        )
    }

    fun generatePaymentToken(
        request: PaymentToken?,
        onResponse: (TutiResponse, ResponseData) -> Unit,
        onError: (TutiResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.GeneratePaymentToken,
            request,
            onResponse,
            onError,
        )
    }

    fun getPaymentToken(
        uuid: String,
        onResponse: (PaymentToken, ResponseData) -> Unit,
        onError: (PaymentToken?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.GET,
            serverURL + Operations.GetPaymentToken,
            null,
            onResponse,
            onError,
            null,
            "uuid", uuid
        )
    }

    fun quickPayment(
        request: EBSRequest?,
        onResponse: (PaymentToken, ResponseData) -> Unit,
        onError: (PaymentToken?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.QuickPayment,
            request as Any,
            onResponse,
            onError,
        )
    }

    fun changeIPIN(
        request: EBSRequest?,
        onResponse: (EBSResponse, ResponseData) -> Unit,
        onError: (EBSResponse?, Exception?, ResponseData?) -> Unit
    ) {
        sendRequest<EBSResponse, EBSResponse>(
            RequestMethods.POST,
            serverURL + Operations.CHANGE_IPIN,
            request as Any,
            onResponse,
            onError
        )
    }

    inline fun <reified ResponseType, reified ErrorType> sendRequest(
        method: RequestMethods,
        URL: String,
        requestToBeSent: Any?,
        crossinline onResponse: (ResponseType, ResponseData) -> Unit,
        crossinline onError: (ErrorType?, Exception?, ResponseData?) -> Unit,
        headers: Map<String, String>? = null,
        vararg params: String
    ): Thread {
        // create a runnable to run it in a new thread (so main thread never hangs)
        val finalURL = if (params.isEmpty()) URL else URL + "?" + params[0] + "=" + params[1]
        val okHttpClient = okHttpInstance
        val runnable = Runnable {

            val requestBody: RequestBody = RequestBody.create(JSON, gson.toJson(requestToBeSent))
            val requestBuilder: Request.Builder = Request.Builder().url(finalURL)
            requestBuilder.header("Authorization", authToken)

            //add additional headers set by the user
            if (headers != null) {
                for (key in headers.keys) {
                    headers[key]?.let { requestBuilder.header(key, it) }
                }
            }

            //check for http method set by the user
            if (method == RequestMethods.POST) {
                requestBuilder.post(requestBody)
            } else if (method == RequestMethods.DELETE) {
                requestBuilder.delete(requestBody)
            } else if (method == RequestMethods.PUT) {
                requestBuilder.put(requestBody)
            } else {
                requestBuilder.get()
            }
            val request: Request = requestBuilder.build()
            try {
                okHttpClient.newCall(request).execute().use { rawResponse ->
                    // check for http errors
                    val responseCode = rawResponse.code
                    val responseBody = rawResponse.body?.string() ?: ""
                    val responseData = ResponseData(responseCode, responseBody, rawResponse.headers)
                    if (responseCode in 400..599) {
                        // call onError if request has failed

                        onError(parseResponse(responseBody), null, responseData)
                    } else {
                        // call onResponse if request has succeeded
//                        if (onResponse != null) {
                        onResponse(parseResponse(responseBody), responseData)
//                        }
                    }
                }
            } catch (exception: IOException) {
                exception.printStackTrace()
                onError(null, exception, null)
            }
        }

        // unit testing concurrent code on multiple threads is hard
        val thread = Thread(runnable)
        if (isSingleThreaded) {
            thread.run()
        } else {
            thread.start()
        }
        return thread
    }

    @Throws(IOException::class)
    inline fun <reified ResponseType> parseResponse(responseAsString: String): ResponseType {
        return when (ResponseType::class.java) {
            String::class.java -> {
                responseAsString as ResponseType
            }
            else -> {
                gson.fromJson(
                    responseAsString,
                    ResponseType::class.java
                )
            }
        }

    }

    companion object {
        val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
        private val logger: HttpLoggingInterceptor
            get() {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                return logging
            }

        val okHttpInstance: OkHttpClient =
            OkHttpClient.Builder().addInterceptor(logger).connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
        val gson = Gson()

    }
}