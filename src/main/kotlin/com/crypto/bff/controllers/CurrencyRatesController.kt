package com.crypto.bff.controllers

import com.crypto.bff.entities.Currency
import com.crypto.bff.entities.CurrencyRates
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/cryptos")
class CurrencyRatesController {

    @GetMapping
    fun index(): List<Pair<String, Currency>>? {
        val url = "https://api.coingecko.com/api/v3/exchange_rates"
        val rest = RestTemplate()
        val response = rest.getForObject(url, CurrencyRates::class.java)

        val filterList = response?.rates?.filterKeys {
            it == "usd" || it == "btc" || it == "aud" || it == "eth"
        }?.toList()

        val urlMap = mapOf(
            "usd" to "https://uploaddeimagens.com.br/images/004/382/822/full/dollar.png?1678335374",
            "btc" to "https://uploaddeimagens.com.br/images/004/382/825/thumb/bitcoin.png?1678335438",
            "eth" to "https://uploaddeimagens.com.br/images/004/382/824/thumb/etherium.png?1678335424",
            "aud" to "https://uploaddeimagens.com.br/images/004/382/823/full/australia.png?1678335397"
        )

        filterList?.forEach { pair ->
            pair.second.urlImage = urlMap[pair.first]!!
        }
        return filterList
    }
}