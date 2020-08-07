package com.labdaschool.javapiggybank.repo;


import com.labdaschool.javapiggybank.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepro extends CrudRepository<Currency, Long>
{

}

