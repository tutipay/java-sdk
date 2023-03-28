/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.tuti

import com.tuti.api.TutiApiClient
import com.tuti.api.authentication.SignInRequest
import com.tuti.api.authentication.SignInResponse
import com.tuti.api.data.Card
import com.tuti.model.Notification
import com.tuti.model.NotificationFilters

object Library {
    var jwt: String? = null

    @JvmStatic
    fun main(args: Array<String>) {
        val client = TutiApiClient()
        val tuti_username = System.getenv("tuti_username")
        val tuti_password = System.getenv("tuti_password")
        val tuti_card_pan = System.getenv("tuti_card_pan")
        val tuti_card_exp_date = System.getenv("tuti_card_exp_date")
        val tuti_card_ipin = System.getenv("tuti_card_ipin")
        val uuid = "3c420386-5fd0-4b72-918a-5b87bba13f81"

        val card = Card(
            PAN = tuti_card_pan,
            expiryDate = tuti_card_exp_date
        )

        client.SignIn(SignInRequest(
            mobile = tuti_username,
            password = tuti_password
        ),
            onResponse = { signInResponse: SignInResponse ->
                val token = signInResponse.authorizationJWT
                client.authToken = token
                client.getNotifications(
                    NotificationFilters("", true),
                    onResponse = { notifications: List<Notification> ->
                        println(notifications)
                    },
                    onError = { tutiResponse, exception -> }
                )

            },
            onError = { tutiResponse, exception -> })
    }
}

