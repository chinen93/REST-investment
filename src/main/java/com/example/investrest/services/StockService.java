/**
    https://spring.io/guides/gs/managing-transactions/
 */
package com.example.investrest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.cache.annotation.Cacheable;

import com.example.investrest.models.Stock;

@Service
public class StockService {

    private static final Logger log = LoggerFactory.getLogger(StockService.class);

    @Cacheable("stocks")
    public Stock[] getStocksREST(){
        /**
        Self-Invocation
        Only external method calls coming in through the proxy are intercepted. This means that self-invocation,
        in effect, a method within the target object calling another method of the target object, will not lead
        to an actual cache interception at runtime even if the invoked method is marked with @Cacheable.
         */
        // Simulate a slow request to see if the cache is working
        try {
            long time = 1500L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        // Get stocks from REST
        RestTemplate restTemplate = new RestTemplate();
        Stock[] stocks = restTemplate.getForObject("http://localhost:8080/stock", Stock[].class);

        log.info("Get stocks from stock-manager");

        return stocks;
    }
}
