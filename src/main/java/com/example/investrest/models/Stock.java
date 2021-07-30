package com.example.investrest.models;

import lombok.Getter;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "stocks")
@Getter
@AllArgsConstructor
public class Stock {

    @Id
    private String id;
    private String description;
}
