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

class User {
    private var id = 0
    private var createdAt: String? = null
    private var updateAt: String? = null
    private var DeletedAt: String? = null
    private var username: String? = null
    private var fullname: String? = null
    private var birthday: String? = null

    @SerializedName("mobile")
    private var mobileNumber: String? = null
    private var email: String? = null

    @SerializedName("is_merchant")
    private var isMerchant = false
    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
        this.createdAt = createdAt
    }

    fun getUpdateAt(): String? {
        return updateAt
    }

    fun setUpdateAt(updateAt: String?) {
        this.updateAt = updateAt
    }

    fun getDeletedAt(): String? {
        return DeletedAt
    }

    fun setDeletedAt(deletedAt: String?) {
        DeletedAt = deletedAt
    }

    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getFullname(): String? {
        return fullname
    }

    fun setFullname(fullname: String?) {
        this.fullname = fullname
    }

    fun getBirthday(): String? {
        return birthday
    }

    fun setBirthday(birthday: String?) {
        this.birthday = birthday
    }

    fun getMobileNumber(): String? {
        return mobileNumber
    }

    fun setMobileNumber(mobileNumber: String?) {
        this.mobileNumber = mobileNumber
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getIsMerchant(): Boolean {
        return isMerchant
    }

    fun setIsMerchant(isMerchant: Boolean) {
        this.isMerchant = isMerchant
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", DeletedAt='" + DeletedAt + '\'' +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", isMerchant=" + isMerchant +
                '}'
    }
}