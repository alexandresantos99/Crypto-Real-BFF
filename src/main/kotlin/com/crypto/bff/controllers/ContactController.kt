package com.crypto.bff.controllers

import com.crypto.bff.entities.Contact
import com.crypto.bff.entities.CurrencyRates
import com.crypto.bff.repositories.ContactRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/contacts")
class ContactController {
    @Autowired
    lateinit var repository: ContactRepository

    @GetMapping
    fun index(): CurrencyRates? {
        val url = "https://api.coingecko.com/api/v3/exchange_rates"
        val rest = RestTemplate()
        return rest.getForObject(url, CurrencyRates::class.java)
    }

    @PostMapping
    fun create(@RequestBody contact: Contact): Contact {
        return repository.save(contact)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody newContact: Contact): Contact {
        val contact = repository.findById(id).orElseThrow { EntityNotFoundException() }
        contact.apply {
            this.name = newContact.name
            this.email = newContact.email
        }
        return repository.save(contact)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        val contact = repository.findById(id).orElseThrow { EntityNotFoundException() }
        return repository.delete(contact)
    }
}