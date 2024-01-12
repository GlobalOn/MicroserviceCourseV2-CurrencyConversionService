package com.microserviceCourse.currencyconversionservice.controller;

import com.microserviceCourse.currencyconversionservice.CurrencyConversionRepository;
import com.microserviceCourse.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    private Environment environment;
    private CurrencyConversionRepository repository;

    @Autowired
    public CurrencyConversionController(Environment environment, CurrencyConversionRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-conversion/{from}/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) throws SQLException {

        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/{from}/{to}",
                CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();
        if (currencyConversion != null) {
            currencyConversion.setQuantity(quantity);
            BigDecimal total = new BigDecimal(quantity.intValue() * currencyConversion.getConversionMultiple().intValue());
            currencyConversion.setTotalCalculatedAmount(total);
            currencyConversion.setEnvironment(environment.getProperty("local.server.port"));

            return currencyConversion;
        } else
            throw new RuntimeException("Unable to find currency rate for " + from + " to " + to);
    }
}