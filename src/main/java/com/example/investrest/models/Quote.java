/**
  https://spring.io/guides/gs/accessing-data-mysql/
 */
package com.example.investrest.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;

@Entity // This tells Hibernate to make a table out of this class
@NoArgsConstructor // JPA Only
@Getter
public class Quote {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String stockId;

    private LocalDate date;

    private Double price;

    public Quote(String stockId, LocalDate date, Double price) {
      this.stockId = stockId;
      this.date = date;
      this.price = price;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public void setStockId(String stockId) {
      this.stockId = stockId;
    }

    public void setDate(LocalDate date) {
      this.date = date;
    }

    public void setPrice(Double price) {
      this.price = price;
    }
}