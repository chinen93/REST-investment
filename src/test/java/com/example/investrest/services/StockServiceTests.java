package com.example.investrest;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.json.JSONObject;
import org.json.JSONException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.investrest.models.Stock;

import com.example.investrest.services.StockService;

import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
class StockServiceTests {

    @Mock
    private RestTemplate restTemplate;

    private StockService stockService;

    @BeforeEach
    void initUseCase() {
        stockService = new StockService(restTemplate);
    }

    @Test
    void testGetStocksREST(){
        // Run function
        Stock[] stocks = stockService.getStocksREST();

        // Assert tests
        verify(restTemplate, times(1)).getForObject("http://172.17.0.3:8080/stock", Stock[].class);
    }

    @Test
    void testRegisterClearStocksCache() throws JSONException {
        // Mock variables
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject notification = new JSONObject();
        notification.put("host", "172.17.0.4");
        notification.put("port", 8081);

        HttpEntity<String> request = new HttpEntity<String>(notification.toString(), headers);

        // Run function
        stockService.registerClearStocksCache();

        // Assert tests
        verify(restTemplate, times(1)).postForObject("http://172.17.0.3:8080/notification", request, String.class);
    }
    
}
