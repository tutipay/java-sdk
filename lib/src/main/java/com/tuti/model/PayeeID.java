package com.tuti.model;

public enum PayeeID {
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
    Zain("0010010002"),
    MTN("0010010004"),
    Sudani("0010010006"),
    E15("0010050001"),
    Invoice("0055555555"),
    Mohe("0010030002"),
    MoheArab("0010030004"),
    Customs("0010030003");

    PayeeID(String s) {
    }
}
