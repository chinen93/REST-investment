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
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "quotes")
@NoArgsConstructor // JPA Only
@Getter
public class Quote {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String stockId;

  @ElementCollection
  @CollectionTable(name = "quotes_prices_mapping", 
    joinColumns = {@JoinColumn(name = "quotes_id", referencedColumnName = "id")})
  @MapKeyColumn(name = "date")
  @Column(name = "price")
  private Map<LocalDate, Double> datePriceMap;

  public Quote(String stockId, LocalDate date, Double price) {
    this.stockId = stockId;
    this.datePriceMap = new HashMap<LocalDate, Double>();
    this.datePriceMap.put(date, price);
  }
}