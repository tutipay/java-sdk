package com.tuti.api


import com.tuti.api.authentication.SignInRequest
import com.tuti.api.data.TutiResponse
import com.tuti.api.ebs.EBSRequest
import com.tuti.model.NotificationFilters
import com.tuti.model.Notifications
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

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
        var req: SignInRequest = SignInRequest(mobile = "sdsds")
        tutiApiClient.GenerateOtpInsecure(req, {}, { resp, t -> Unit })
    }

    @Test
    fun verifyOtp() {
        val tutiApiClient = TutiApiClient()
        var req = com.tuti.model.SignInRequest(mobile = "0912144343")
        tutiApiClient.VerifyOtp(req, null!!, null!!)
    }

    @Test
    fun addBeneficiary() {
//        val tutiApiClient = TutiApiClient()
//        tutiApiClient.authToken =
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIwMTExNDkzODg1IiwiZXhwIjoxNjY3NDMxNTY5LCJpc3MiOiJub2VicyJ9.DUyUJDTPO68b9f4Jl5dCnt-yIQOGfA94l2C-t7D88JY"
//        val beneficiary = Beneficiary(number = "0912141679", billType = 0, operator = 1)
//        tutiApiClient.addBeneficiary(beneficiary, { signInResponse: TutiResponse? ->
//            run { println(signInResponse) }
//        }, { objectReceived: TutiResponse?, exception: Exception? ->
//            run {
//                println(objectReceived.toString())
//            }
//        })
    }

    @Test
    fun upsertFirebase() {
        val tutiApiClient = TutiApiClient()
        tutiApiClient.authToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIwMTExNDkzODg1IiwiZXhwIjoxNjY3NDMxNTY5LCJpc3MiOiJub2VicyJ9.DUyUJDTPO68b9f4Jl5dCnt-yIQOGfA94l2C-t7D88JY"

        tutiApiClient.UpsertFirebase("this is my firebase token", { signInResponse: TutiResponse? ->
            run { println(signInResponse) }
        }, { objectReceived: TutiResponse?, _: Exception? ->
            run {
                println(objectReceived.toString())
            }
        })
    }

    @Test
    fun getNotifications() {
        val tutiApiClient = TutiApiClient()
        tutiApiClient.authToken =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIwMTExNDkzODg1IiwiZXhwIjoxNjY3NDMxNTY5LCJpc3MiOiJub2VicyJ9.DUyUJDTPO68b9f4Jl5dCnt-yIQOGfA94l2C-t7D88JY"
        tutiApiClient.getNotifications(
            NotificationFilters(getAll = true, mobile = "0111493885"),
            onResponse = { res: Notifications? ->
                run {
                    assertEquals(res!!.notifications[0].phone, "0129751986")
                    assertEquals(res.notifications[0].body, "fuff")
                }
            },
            onError = { objectReceived: TutiResponse?, _: Exception? ->
                run {

                }
            })
    }

    @Test
    fun getUserCard() {
//        val tutiApiClient = TutiApiClient()
//        tutiApiClient.authToken =
//            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIwMTExNDkzODg1IiwiZXhwIjoxNjY3NDMxNTY5LCJpc3MiOiJub2VicyJ9.DUyUJDTPO68b9f4Jl5dCnt-yIQOGfA94l2C-t7D88JY"
//        tutiApiClient.getUserCard(mobile="0111493885", onResponse = { res: User? ->
//            run {
//                assertEquals(res!!.cards[0].PAN, "0129751986")
//            }}, onError = { objectReceived: TutiResponse?, _: Exception? ->
//            run {
//
//            }
//        })
    }
}


