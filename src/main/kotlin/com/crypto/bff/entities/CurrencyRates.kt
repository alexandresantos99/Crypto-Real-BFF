package com.crypto.bff.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyRates(
    @JsonProperty("rates")
    val rates: Map<String, Currency>
)

data class Currency(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("unit")
    val unit: String,
    @JsonProperty("value")
    val value: Double,
    @JsonProperty("type")
    val type: String
)