package com.microserviceCourse.currencyconversionservice.repository;

import com.microserviceCourse.currencyconversionservice.entity.CurrencyConversion;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyConversionRepository extends CrudRepository<CurrencyConversion, Long> {

}
