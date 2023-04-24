package com.ravilearns.currencyexchangeservice;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    private final CurrencyExchangeRepo repo;

    private final Environment environment;

    public Controller(CurrencyExchangeRepo repo, Environment environment) {
        this.repo = repo;
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable("from") String from,
            @PathVariable("to") String to){

        CurrencyExchange currencyExchange = repo.findByFromAndTo(from,to);
        if(currencyExchange==null)
            throw new RuntimeException();
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
        return currencyExchange;
    }

    @PostMapping("/addInfo")
    public CurrencyExchange addInfo(@RequestBody CurrencyExchange currencyExchange){
        return repo.save(currencyExchange);
    }
}
