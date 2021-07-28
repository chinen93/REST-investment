/**
    https://spring.io/guides/gs/accessing-data-mysql/
 */
package com.example.investrest.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.investrest.models.Quote;

// This will be AUTO IMPLEMENTED by Spring into a Bean called QuoteRepository
// CRUD refers Create, Read, Update, Delete
public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    Quote findByStockId(String stockId);
}