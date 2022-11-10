package com.tuti.model

enum class PayeeID( val payeeId: String) {
    /*
    * case "0010010002": // zain
                return billInfo.get("totalAmount").toString(); // FIXME(adonese): Zain also has an `unbilledAmount` field like mtn but we are using totalAmount here just for testing
            case "0010010004": // mtn
                return billInfo.get("unbilledAmount").toString(); // FIXME(adonese): This doesn't seem to be correct..
            case "0010010006": //sudani
                return billInfo.get("billAmount").toString();
            case "0055555555": // e-invoice
                return billInfo.get("amount_due").toString();
            case "0010030002": // mohe
            case "0010030004": // mohe-arab
                return billInfo.get("dueAmount").toString();
            case "0010030003": // Customs
                return billInfo.get("AmountToBePaid").toString();
            case "0010050001": // e-15
                return billInfo.get("TotalAmount").toString();
            default:
                return "";*/

    ZainPrepaid("0010010001"),
    MTNPrepaid("0010010003"),
    SudaniPrepaid("0010010005"),
    ZainPostpaid("0010010002"),
    MTNPostpaid("0010010004"),
    SudaniPostpaid("0010010006"),
    NEC("0010020001"),
    P2P("p2p"),
    Bashair("bashair"),
    E15("0010050001"),
    Invoice("0055555555"),
    Mohe("0010030002"),
    MoheArab("0010030004"),
    Customs("0010030003");
}