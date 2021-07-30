/**
    https://spring.io/guides/gs/managing-transactions/
 */
package com.example.investrest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import org.json.JSONObject;


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

    @CacheEvict(value="stocks", allEntries=true)
    public void evictAllCacheValues() {
        log.info("Clear stocks cache");
    }

    public void registerClearStocksCache(){
        log.info("Registering to clear stock cache");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject notification = new JSONObject();
        notification.put("host", "localhost");
        notification.put("port", 8081);

        HttpEntity<String> request = new HttpEntity<String>(notification.toString(), headers);
        String notificationResult = restTemplate.postForObject("http://localhost:8080/notification", request, String.class);

        log.info(notificationResult);
    }
}