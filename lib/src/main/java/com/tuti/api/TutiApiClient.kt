package com.tuti.api

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.tuti.api.authentication.SignInRequest
import com.tuti.api.authentication.SignInResponse
import com.tuti.api.authentication.SignUpRequest
import com.tuti.api.authentication.SignUpResponse
import com.tuti.api.data.*
import com.tuti.api.ebs.EBSRequest
import com.tuti.api.ebs.EBSResponse
import com.tuti.model.*
import com.tuti.util.IPINBlockGenerator
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class TutiApiClient {
    var serverURL: String? = null
        private set
    var isSingleThreaded = false
    var authToken: String = ""
    var ebsKey: String =
            "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBANx4gKYSMv3CrWWsxdPfxDxFvl+Is/0kc1dvMI1yNWDXI3AgdI4127KMUOv7gmwZ6SnRsHX/KAM0IPRe0+Sa0vMCAwEAAQ=="

    @Deprecated("")
    constructor(isDevelopment: Boolean) {
        serverURL = getServerURL(isDevelopment)
    }

    constructor() {
        serverURL = getServerURL(false)
    }

    private fun getServerURL(development: Boolean): String {
        val developmentHost = "https://staging.app.2t.sd/api/consumer/"
        val productionHost = "https://staging.app.2t.sd/api/consumer/"
        return if (development) developmentHost else productionHost
    }

    private fun fillRequestFields(card: Card, ipin: String, amount: Float): EBSRequest {
        val request = EBSRequest()
        val encryptedIPIN: String = IPINBlockGenerator.getIPINBlock(ipin, ebsKey, request.uuid)
        request.tranAmount = amount
        request.setTranCurrencyCode("SDG")
        request.pan = card.PAN
        request.expDate = card.expiryDate
        request.setIPIN(encryptedIPIN)
        return request
    }

    @Deprecated(message = "Replace with SignIn with new kotlin classes instead.", replaceWith = ReplaceWith("SignIn"))
    fun SignIn(
            credentials: SignInRequest,
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.SIGN_IN,
                credentials,
                onResponse,
                onError
        )
    }

    fun SignIn(
            credentials: com.tuti.model.SignInRequest,
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.SIGN_IN,
                credentials,
                onResponse,
                onError
        )
    }

    /** ChangePassword changes an existing user password. Should
     * be accessed behind a jwt active session
     */
    fun ChangePassword(
            credentials: SignInRequest?,
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
     * GenerateOtpSignIn service used to request an otp to be sent to the user's registered sms phone number
     *
     * @param credentials
     * @param onResponse
     * @param onError
     */
    fun GenerateOtpInsecure(
            credentials: SignInRequest?,
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.GENERATE_LOGIN_OTP_INSECURE,
                credentials,
                onResponse,
                onError
        )
    }


    fun VerifyOtp(
            credentials: com.tuti.model.SignInRequest,
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.VERIFY_OTP,
                credentials,
                onResponse,
                onError
        )
    }

    fun Otp2FA(
            credentials: EBSRequest,
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.OTP_2FA,
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
            onResponse: (SignInResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.REFRESH_TOKEN,
                credentials,
                onResponse,
                onError
        )
    }

    @Deprecated(message = "Replace with SignUp with new kotlin classes instead.", replaceWith = ReplaceWith("SignUp"))
            /**
             * @param signUpRequest
             * @param onResponse
             * @param onError
             */
    fun Signup(
            signUpRequest: SignUpRequest?,
            onResponse: (SignUpResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.SIGN_UP,
                signUpRequest,
                onResponse,
                onError
        )
    }

    fun Signup(
            signUpRequest: SignupRequest,
            onResponse: (SignUpResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (SignUpResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (SignUpResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (EBSResponse) -> Unit,
            onError: (EBSResponse?, Exception?) -> Unit
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
            onResponse: (Cards) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.IPIN_key,
                ebsRequest,
                onResponse,
                onError
        )
    }

    fun addBeneficiary(
            beneficiary: Beneficiary,
            onResponse: (TutiResponse?) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {

        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.BENEFICIARY,
                beneficiary.toNoebs(),
                onResponse,
                onError,
        )
    }

    fun getBeneficiaries(
            card: Any?,
            onResponse: (List<NoebsBeneficiary>) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.GET,
                serverURL + Operations.BENEFICIARY,
                card,
                onResponse,
                onError,
        )
    }

    fun deleteBeneficiary(
            card: Any?,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.DELETE,
                serverURL + Operations.BENEFICIARY,
                card,
                onResponse,
                onError,
        )
    }

    fun addCard(
            card: Any?,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (String) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
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
            onResponse: (String) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.DELETE,
                serverURL + Operations.DELETE_CARD,
                card,
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
            card: Card,
            ipin: String,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = EBSRequest()
        val encryptedIPIN: String = IPINBlockGenerator.getIPINBlock(ipin, ebsKey, request.uuid)
        request.pan = card.PAN
        request.expDate = card.expiryDate
        request.setIPIN(encryptedIPIN)

        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.GET_BALANCE,
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
            billInfo: BillInfo,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.Get_Bills,
                billInfo,
                onResponse,
                onError,
        )
    }

    fun cardTransfer(
            card: Card,
            ipin: String,
            receiverCard: Card,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = fillRequestFields(card, ipin, amount)
        request.setToCard(receiverCard.PAN)
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.CARD_TRANSFER,
                request,
                onResponse,
                onError,
        )
    }

    fun purchaseBashairCredit(
            card: Card,
            ipin: String,
            bashairType: BashairTypes,
            paymentValue: String,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = fillRequestFields(card, ipin, amount)
        request.setPayeeId(TelecomIDs.Bashair.payeeID)
        request.setPaymentInfo(bashairType.bashairInfo(paymentValue))
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.BILL_PAYMENT,
                request,
                onResponse,
                onError,
        )
    }

    fun payE15Invoice(
            card: Card,
            ipin: String,
            invoice: String,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {

        val request = fillRequestFields(card, ipin, amount)
        request.setPayeeId(TelecomIDs.E15.payeeID)
        request.setPaymentInfo(E15(true, invoice, ""))
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.BILL_PAYMENT,
                request,
                onResponse,
                onError
        )
    }

    fun payCustomsInvoice(
            card: Card,
            ipin: String,
            bankCode: String,
            declarantCode: String,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = fillRequestFields(card, ipin, amount)
        request.setPayeeId(TelecomIDs.CUSTOMS.payeeID)
        request.setPaymentInfo(Customs(bankCode, declarantCode))

        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.BILL_PAYMENT,
                request,
                onResponse,
                onError,
        )
    }

    fun payMOHEArabFees(
            card: Card,
            ipin: String,
            courseId: CourseID,
            admissionType: AdmissionType,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = fillRequestFields(card, ipin, amount)
        request.setPayeeId(TelecomIDs.CUSTOMS.payeeID)
        request.setPaymentInfo(MOHEArab("", "", courseId, admissionType))
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.BILL_PAYMENT,
                request,
                onResponse,
                onError,
        )
    }

    fun payMOHEFees(
            card: Card,
            ipin: String,
            seatNumber: String,
            courseId: CourseID,
            admissionType: AdmissionType,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = fillRequestFields(card, ipin, amount)
        request.setPayeeId(TelecomIDs.CUSTOMS.payeeID)
        request.setPaymentInfo(MOHE(seatNumber, courseId, admissionType))
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.BILL_PAYMENT,
                request,
                onResponse,
                onError
        )
    }

    fun payEInvoice(
            card: Card,
            ipin: String,
            customerRef: String,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = fillRequestFields(card, ipin, amount)
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

    fun buyPhoneCredit(
            card: Card,
            ipin: String,
            mobile: String,
            operator: Operator,
            carrierPlan: CarrierPlan,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = fillRequestFields(card, ipin, amount)

        when (operator) {
            Operator.ZAIN -> run {
                when (carrierPlan) {
                    CarrierPlan.PREPAID -> run {
                        request.setPayeeId(TelecomIDs.ZAIN.payeeID)
                    }
                    CarrierPlan.POSTPAID -> run {
                        request.setPayeeId(TelecomIDs.ZAIN_BILL.payeeID)
                    }
                }
            }

            Operator.SUDANI -> run {
                when (carrierPlan) {
                    CarrierPlan.PREPAID -> run {
                        request.setPayeeId(TelecomIDs.SUDANI.payeeID)
                    }
                    CarrierPlan.POSTPAID -> run {
                        request.setPayeeId(TelecomIDs.SUDANI_BILL.payeeID)
                    }
                }
            }

            Operator.MTN -> run {
                when (carrierPlan) {
                    CarrierPlan.PREPAID -> run {
                        request.setPayeeId(TelecomIDs.MTN.payeeID)
                    }
                    CarrierPlan.POSTPAID -> run {
                        request.setPayeeId(TelecomIDs.MTN_BILL.payeeID)
                    }
                }
            }
        }

        request.setPaymentInfo("MPHONE=$mobile")
        sendRequest(
                RequestMethods.POST,
                serverURL + Operations.BILL_PAYMENT,
                request,
                onResponse,
                onError,
        )
    }

    fun buyNECCredit(
            card: Card,
            ipin: String,
            meterNumber: String,
            amount: Float,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {
        val request = fillRequestFields(card, ipin, amount)
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

    fun guessBillerId(
            mobile: String,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            onResponse: (PaymentToken) -> Unit,
            onError: (PaymentToken?, Exception?) -> Unit
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
            onResponse: (PaymentToken) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
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
            card: Card,
            oldIPIN: String,
            newIPIN: String,
            onResponse: (TutiResponse) -> Unit,
            onError: (TutiResponse?, Exception?) -> Unit
    ) {

        val request = EBSRequest()

        val oldIPINEncrypted: String = IPINBlockGenerator.getIPINBlock(oldIPIN, ebsKey, request.uuid)
        val newIPINEncrypted: String = IPINBlockGenerator.getIPINBlock(newIPIN, ebsKey, request.uuid)

        request.expDate = card.expiryDate
        request.setIPIN(oldIPINEncrypted)
        request.setNewIPIN(newIPINEncrypted)
        request.pan = card.PAN

        sendRequest(
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
            crossinline onResponse: (ResponseType) -> Unit,
            crossinline onError: (ErrorType?, Exception?) -> Unit,
            headers: Map<String, String>? = null,
            vararg params: String
    ): Thread {
        // create a runnable to run it in a new thread (so main thread never hangs)
        val finalURL = if (params.isEmpty()) URL else URL + "?" + params[0] + "=" + params[1]
        val runnable = {
            val requestBody: RequestBody = gson.toJson(requestToBeSent).toRequestBody(JSON)
            val requestBuilder: Request.Builder = Request.Builder().url(finalURL)
            requestBuilder.header("Authorization", authToken)

            //add additional headers set by the user
            if (headers != null) {
                for (key in headers.keys) {
                    headers[key]?.let { requestBuilder.header(key, it) }
                }
            }

            //check for http method set by the user
            when (method) {
                RequestMethods.POST -> requestBuilder.post(requestBody)
                RequestMethods.DELETE -> requestBuilder.delete(requestBody)
                RequestMethods.PUT -> requestBuilder.put(requestBody)
                else -> requestBuilder.get()
            }

            val request: Request = requestBuilder.build()
            try {
                okHttpClient.newCall(request).execute().use { rawResponse ->
                    // check for http errors
                    val responseCode = rawResponse.code
                    val responseBody = rawResponse.body?.string() ?: ""
                    if (responseCode in 400..599) {
                        // call onError if request has failed
                        onError(parseResponse(responseBody), null)
                    } else {
                        // call onResponse if request has succeeded
                        onResponse(parseResponse(responseBody))
                    }
                }
            } catch (exception: Exception) {
                when (exception) {
                    is JsonSyntaxException -> {
                        exception.printStackTrace()
                        onError(null, exception)
                    }
                    else -> {
                        throw exception
                    }
                }
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


    inline fun <reified ResponseType> parseResponse(responseAsString: String): ResponseType {
        var type = object : TypeToken<List<NoebsBeneficiary>>() {}.type

        return when (ResponseType::class.java) {
            String::class.java -> {
                responseAsString as ResponseType
            }
            else -> {
//                gson.fromJson(
//                        responseAsString,
//                        ResponseType::class.java
//                )
                Json.decodeFromString(responseAsString)
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

        val okHttpClient: OkHttpClient =
                OkHttpClient.Builder().addInterceptor(logger).connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
        val gson = Gson()

    }
}