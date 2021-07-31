/**
  https://marioalvial.medium.com/blindando-sua-api-spring-boot-com-o-padr%C3%A3o-dto-44f97020d1a0
 */
package com.example.investrest.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.example.investrest.models.Quote;

// DTO (Data Transfer Object)
@Getter
@AllArgsConstructor
@NoArgsConstructor // JPA Only
public class QuoteDTO {
    private String stockId;
    private LocalDate date;
    private Double price;

    public Quote createQuote() {
        return new Quote(stockId, date, price);
    }
}