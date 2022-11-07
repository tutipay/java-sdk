package com.tuti.api.ebs

import java.io.Serializable

@kotlinx.serialization.Serializable
class LastTransactions {
    // Setter Methods
    // Getter Methods
    var merchantAccountType: String? = null
    var responseCode = 0f
    var transactionId: String? = null
    var merchantID: String? = null
    var issuerTranFee: String? = null
    var applicationId: String? = null
    var tranType: String? = null
    var authenticationType: String? = null
    var merchantAccountExpDate: String? = null
    var pan: String? = null
    var tranDateTime: String? = null
    var responseMessage: String? = null
    var merchantAccountReference: String? = null
    var tranAmount = 0f
    var responseStatus: String? = null
    var acqTranFee: String? = null
    var merchantName: String? = null
    var uuid: String? = null
    var merchantCity: String? = null
    var merchantMobileNo: String? = null
}