package com.microserviceCourse.currencyconversionservice.proxyService;

import com.microserviceCourse.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000/") -> Doesn`t use SpringCloudLoadBalancer

//This declaration will use SpringCloudLoadBalancer that transitively will be added with Eureka dependency
@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/{from}/{to}")
    public CurrencyConversion retrieveCurrency(@PathVariable String from, @PathVariable String to) throws SQLException;
}
