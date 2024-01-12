package com.microserviceCourse.currencyconversionservice;

import com.microserviceCourse.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyConversionRepository extends CrudRepository<CurrencyConversion, Long> {

}
