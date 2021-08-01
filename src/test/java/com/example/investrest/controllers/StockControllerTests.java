package com.example.investrest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.json.JSONObject;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.investrest.controllers.StockController;
import com.example.investrest.services.StockService;

@ExtendWith(MockitoExtension.class)
public class StockControllerTests {

	@Autowired
	private MockMvc mockMvc;

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController StockController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(StockController).build();
    }

    @Test
    public void testClearStockCache() throws Exception {
        // Mock service function
        verify(stockService, times(0)).evictAllCacheValues();
        doNothing().when(stockService).evictAllCacheValues();

        // Hit the endpoint and assert test
        this.mockMvc.perform(delete("/stockcache"))
                    .andExpect(status().isOk());
        verify(stockService, times(1)).evictAllCacheValues();
    }
}
