package com.example.investrest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.investrest.services.StockService;


@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /quote (after Application path)
public class StockController {

    private static final Logger log = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @DeleteMapping(path="/stockcache") // Map ONLY DELETE Requests
    public ResponseEntity<String> clearStockCache () {
        log.info("endpoint: '/stockcache'");

        stockService.evictAllCacheValues();
        return new ResponseEntity<>("Cache Cleared", HttpStatus.OK);
    }
}
