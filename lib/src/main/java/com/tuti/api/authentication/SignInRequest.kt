package com.tuti.api.authentication

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
import java.util.concurrent.TimeUnit

class SignInRequest(private var username: String, private var password: String) {
    fun getMobile(): String {
        return username
    }

    fun setMobile(mobile: String) {
        username = mobile
    }

    private var mobile: String
    fun getSignature(): String? {
        return signature
    }

    fun setSignature(signature: String?) {
        this.signature = signature
    }

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String?) {
        this.message = message
    }

    private var signature: String? = null
    private var message: String? = null
    fun getOldToken(): String? {
        return oldToken
    }

    fun setOldToken(oldToken: String?) {
        this.oldToken = oldToken
    }

    @SerializedName("authorization")
    private var oldToken: String? = null
    fun getUsername(): String {
        return username
    }

    fun setUsername(username: String): SignInRequest {
        this.username = username
        return this
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String): SignInRequest {
        this.password = password
        return this
    }

    /**
     * Use to sign in a user given their mobile number and password.
     * @param mobile
     * @param password
     */
    init {
        username = username
    }
}