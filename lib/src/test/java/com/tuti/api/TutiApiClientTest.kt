package com.tuti.api

import com.tuti.api.authentication.SignInRequest
import org.junit.jupiter.api.Assertions.*

import com.tuti.api.ebs.EBSRequest

import org.junit.jupiter.api.Test


import java.util.UUID

internal class TutiApiClientTest {

    @Test
    fun getPaymentToken() {
        val tutiApiClient = TutiApiClient()
        val uuid = UUID.randomUUID()
        tutiApiClient.getPaymentToken(uuid.toString(), null!!, null!!)

    }

    @Test
    fun quickPayment() {
        val req = EBSRequest()

    }

    @Test
    fun generateOtpInsecure() {
        val tutiApiClient = TutiApiClient()
        var req: SignInRequest = SignInRequest()
        req.mobile = "sdsds"
        tutiApiClient.GenerateOtpInsecure(req, null!!, null!!)
    }

    @Test
    fun verifyOtp() {
        val tutiApiClient = TutiApiClient()
        var req = com.tuti.model.SignInRequest(mobile = "0912144343")
        tutiApiClient.VerifyOtp(req, null!!, null!!)
    }
}


