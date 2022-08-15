package com.tuti.api.data

import com.google.common.base.Objects
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

class Card {
    var name: String? = null

    @SerializedName("exp_date")
    var expiryDate: String? = null

    @SerializedName("pan")
    var pAN: String? = null

    // new_* are for editing_card api
    @SerializedName("new_pan")
    var newPan: String? = null

    @SerializedName("new_expdate")
    var newExpDate: String? = null

    @SerializedName("new_name")
    var newName: String? = null
    override fun toString(): String {
        return "Card{" +
                "name='" + name + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", PAN='" + pAN + '\'' +
                '}'
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val card = o as Card
        return Objects.equal(name, card.name) && Objects.equal(
            expiryDate,
            card.expiryDate
        ) && Objects.equal(
            pAN, card.pAN
        )
    }

    override fun hashCode(): Int {
        return Objects.hashCode(name, expiryDate, pAN)
    }
}