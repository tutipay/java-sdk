package com.tuti.api.data

/** TelecomIDs enum with payeedID and label to be used for all telecom transactions
 */
enum class TelecomIDs(val payeeID: String, val label: String, val paymentInfo: String, val resultId: String) {
    ZAIN("0010010001", "Zain Top Up", "MPHONE=", "zainTopup"),
    MTN("0010010003", "MTN Top Up", "MPHONE=", "mtnTopup"),
    SUDANI("0010010005", "Sudani Top Up", "MPHONE=", "sudaniTopup"),
    ZAIN_BILL("0010010002", "Zain Bills", "MPHONE=", "zainInquiry"),
    MTN_BILL("0010010004", "MTN Bills", "MPHONE=", "mtnInquiry"),
    SUDANI_BILL("0010010006", "Sudani Bills", "MPHONE=", "sudaniInquiru"),
    NEC("0010020001", "Electricity Bills", "METER=", "electricity"),
    E15("0010050001", "Electricity Bills", "METER=", "electricity"),
    CUSTOMS("0010030003", "Electricity Bills", "METER=", "electricity"),
    Einvoice("0055555555", "Electricity Bills", "METER=", "electricity"),
    Bashair("0010060002", "Electricity Bills", "METER=", "electricity"),
}

enum class BashairTypes (val value: String) {
    Card("Bashair Card"),
    Account("Bashair Account"),
    Station("Bashair Station")

}

fun BashairTypes.bashairInfo(id: String): String {
    return "REFTYPE=${this.value}/REFVALUE=${id}"
}

fun E15(isPayment: Boolean, invoice: String, phone: String?=""): String {
    val p: String = if (phone.isNullOrEmpty()) {
        "0912222222"
    }else{
        phone
    }
    val id: String = if (isPayment) {"2"} else "6"
    return "SERVICEID=$id/INVOICENUMBER=$invoice/PHONENUMBER=$p"
}

fun Customs(bank: String, id: String): String {
    return "BANKCODE=$bank/DECLARANTCODE=$id"
}