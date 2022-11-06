package com.tuti.api.data

@kotlinx.serialization.Serializable
data class Cards (
    val cards: List<Card> = emptyList()
)