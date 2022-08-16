package com.tuti.api

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
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID
import com.tuti.api.ebs.LastTransactions
import java.util.HashMap
import com.tuti.model.PayeeID
import java.text.NumberFormat
import java.text.DecimalFormat
import com.tuti.api.ebs.ErrorDetails
import com.tuti.api.ebs.EBSResponse
import com.google.gson.Gson
import com.tuti.api.authentication.SignInRequest
import com.tuti.api.TutiApiClient.ResponseCallable
import com.tuti.api.authentication.SignInResponse
import com.tuti.api.TutiApiClient.ErrorCallable
import com.tuti.model.Operations
import com.tuti.api.authentication.SignUpRequest
import com.tuti.api.authentication.SignUpResponse
import com.tuti.api.ebs.EBSRequest
import java.lang.Runnable
import com.tuti.api.TutiApiClient
import com.tuti.api.data.*
import okhttp3.*
import kotlin.Throws
import java.io.IOException
import kotlin.jvm.Volatile
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.Exception
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class TutiApiClient {
    private var serverURL: String? = null
    private var isSingleThreaded = false
    private var authToken: String? = null
    fun getAuthToken(): String? {
        return authToken
    }

    fun getServerURL(): String? {
        return serverURL
    }

    fun setAuthToken(authToken: String?) {
        this.authToken = authToken
    }

    fun isSingleThreaded(): Boolean {
        return isSingleThreaded
    }

    fun setSingleThreaded(singleThreaded: Boolean) {
        isSingleThreaded = singleThreaded
    }

    @Deprecated("")
    constructor(isDevelopment: Boolean) {
        serverURL = getServerURL(isDevelopment)
    }

    constructor(token: String?) {
        authToken = token
    }

    constructor() {
        serverURL = getServerURL(false)
    }

    private fun getServerURL(development: Boolean): String {
        val developmentHost = "https://staging.app.2t.sd/consumer/"
        val productionHost = "https://staging.app.2t.sd/consumer/"
        return if (development) developmentHost else productionHost
    }

    fun SignIn(
        credentials: SignInRequest?,
        onResponse: ResponseCallable<SignInResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.SIGN_IN,
            credentials,
            SignInResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    /**
     * OneTimeSignIn allows tutipay users to sign in via a code we send to their phone numbers
     * Notice: this method ONLY works for tutipay registered devices, at the moment
     * it doesn't support a sign in from a new device, as it relies on the user
     * signing a message via their private key
     * @param credentials
     * @param onResponse
     * @param onError
     */
    fun OneTimeSignIn(
        credentials: SignInRequest?,
        onResponse: ResponseCallable<SignInResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.SINGLE_SIGN_IN,
            credentials,
            SignInResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
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
        onResponse: ResponseCallable<SignInResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.GENERATE_LOGIN_OTP,
            credentials,
            SignInResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    /**
     * RefreshToken used to refresh an existing token to keep user's session valid.
     * @param credentials
     * @param onResponse a method that is used to handle successful cases
     * @param onError a method to handle on error cases
     */
    fun RefreshToken(
        credentials: SignInRequest?,
        onResponse: ResponseCallable<SignInResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.REFRESH_TOKEN,
            credentials,
            SignInResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    /**
     *
     * @param signUpRequest
     * @param onResponse
     * @param onError
     */
    fun Signup(
        signUpRequest: SignUpRequest?,
        onResponse: ResponseCallable<SignUpResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.SIGN_UP,
            signUpRequest,
            SignUpResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    /**
     * VerifyFirebase used to verify a verification ID token that was sent to a user. It sets is_activiated
     * flag as true for the selected user. This is basically an in-background operation, and as though it shouldn't
     * block the UI, nor does the implementation should care too much about the returned object.
     * @param signUpRequest
     * @param onResponse
     * @param onError
     */
    fun VerifyFirebase(
        signUpRequest: SignUpRequest?,
        onResponse: ResponseCallable<SignUpResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.VERIFY_FIREBASE,
            signUpRequest,
            SignUpResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun sendEBSRequest(
        URL: String?,
        ebsRequest: EBSRequest?,
        onResponse: ResponseCallable<EBSResponse?>?,
        onError: ErrorCallable<EBSResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            URL,
            ebsRequest,
            EBSResponse::class.java,
            EBSResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun getCards(onResponse: ResponseCallable<Cards?>?, onError: ErrorCallable<TutiResponse?>?) {
        sendRequest(
            RequestMethods.GET,
            serverURL + Operations.GET_CARDS,
            null,
            Cards::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun getPublicKey(
        ebsRequest: EBSRequest?,
        onResponse: ResponseCallable<TutiResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.PUBLIC_KEY,
            ebsRequest,
            TutiResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun getIpinPublicKey(
        ebsRequest: Any?,
        onResponse: ResponseCallable<TutiResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.IPIN_key,
            ebsRequest,
            TutiResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun addCard(
        card: Any?,
        onResponse: ResponseCallable<String?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.ADD_CARD,
            card,
            String::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun editCard(
        card: Card?,
        onResponse: ResponseCallable<String?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.PUT,
            serverURL + Operations.EDIT_CARD,
            card,
            String::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun deleteCard(
        card: Card?,
        onResponse: ResponseCallable<String?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.DELETE,
            serverURL + Operations.DELETE_CARD,
            card,
            String::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun billInquiry(
        request: Any?,
        onResponse: ResponseCallable<TutiResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.BILL_INQUIRY,
            request,
            TutiResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun generatePaymentToken(
        request: PaymentToken?,
        onResponse: ResponseCallable<TutiResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.GeneratePaymentToken,
            request,
            TutiResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun getPaymentToken(
        request: PaymentToken?,
        onResponse: ResponseCallable<TutiResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.GET,
            serverURL + Operations.GetPaymentToken,
            request,
            TutiResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun quickPayment(
        request: EBSRequest?,
        onResponse: ResponseCallable<TutiResponse?>?,
        onError: ErrorCallable<TutiResponse?>?
    ) {
        sendRequest(
            RequestMethods.POST,
            serverURL + Operations.QuickPayment,
            request,
            TutiResponse::class.java,
            TutiResponse::class.java,
            onResponse,
            onError,
            null
        )
    }

    fun sendRequest(
        method: RequestMethods,
        URL: String?,
        requestToBeSent: Any?,
        ResponseType: Type,
        ErrorType: Type,
        onResponse: ResponseCallable<*>?,
        onError: ErrorCallable<*>?,
        headers: Map<String?, String?>?
    ): Thread {

        // create a runnable to run it in a new thread (so main thread never hangs)
        val runnable = Runnable {
            val okHttpClient = getOkHttpInstance()
            val gson = getGsonInstance()
            val requestBody: RequestBody = RequestBody.create(gson!!.toJson(requestToBeSent), JSON)
            val requestBuilder: Builder = Builder().url(URL)
            if (authToken != null) requestBuilder.header("Authorization", authToken)

            //add additional headers set by the user
            if (headers != null) {
                for (key in headers.keys) {
                    requestBuilder.header(key, headers[key])
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
                okHttpClient!!.newCall(request).execute().use { rawResponse ->
                    // check for http errors
                    val responseCode = rawResponse.code
                    val responseBody = rawResponse.body!!.string()
                    val responseData = ResponseData(responseCode, responseBody, rawResponse.headers)
                    if (responseCode in 400..599) {
                        // call onError if request has failed
                        onError?.call(
                            parseResponse(responseBody, rawResponse, ErrorType),
                            null,
                            responseData
                        )
                    } else {
                        // call onResponse if request has succeeded
                        if (onResponse != null) {
                            onResponse.call(
                                parseResponse(responseBody, rawResponse, ResponseType),
                                responseData
                            )
                        }
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                onError?.call(null, exception, null)
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
    private fun parseResponse(
        responseAsString: String,
        rawResponse: Response,
        ResponseType: Type
    ): Any {
        val gson = getGsonInstance()
        return if (isTypeStringOrNull(ResponseType)) responseAsString else gson!!.fromJson<Any>(
            responseAsString,
            ResponseType
        )
    }

    private fun isTypeStringOrNull(type: Type?): Boolean {
        return type === String::class.java || type == null
    }

    interface ResponseCallable<T> {
        fun call(objectReceived: T, rawResponse: ResponseData?)
    }

    interface ErrorCallable<T> {
        fun call(errorReceived: T, exception: Exception?, rawResponse: ResponseData?)
    }

    companion object {
        val JSON: MediaType = get.get("application/json; charset=utf-8")

        @Volatile
        private var okHttpClient: OkHttpClient? = null

        @Volatile
        private var gson: Gson? = null
        private fun getLogger(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            return logging
        }

        @Synchronized
        private fun getOkHttpInstance(): OkHttpClient? {
            if (okHttpClient == null) {
                okHttpClient =
                    Builder().addInterceptor(getLogger()).connectTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS)
                        .build()
            }
            return okHttpClient
        }

        @Synchronized
        private fun getGsonInstance(): Gson? {
            if (gson == null) {
                gson = Gson()
            }
            return gson
        }
    }
}