package com.example.demo.controllers

import com.example.demo.entities.Currency
import com.example.demo.entities.CurrencyRates
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
        return response?.rates?.filterKeys {
            it == "usd" || it == "btc" || it == "aud" || it == "eth"
        }?.toList()
    }
}