package com.tuti.model

enum class PayeeID( val payeeId: String) {
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