package com.ravilearns.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Integer> {
    CurrencyExchange findByFromAndTo(String from, String to);
}

