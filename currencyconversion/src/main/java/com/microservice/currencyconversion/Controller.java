package com.microservice.currencyconversion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class Controller {
    private final CurrencyExchangeProxy proxy;

    public Controller(CurrencyExchangeProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion  calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity){

        CurrencyConversion cu = proxy.retrieveExchangeValue(from,to);
        return new CurrencyConversion(cu.getId(),
                cu.getFrom(),
                cu.getTo(),
                cu.getConversionMultiple(),
                quantity,
                quantity.multiply(cu.getConversionMultiple()),
                cu.getEnvironment());
    }
}
