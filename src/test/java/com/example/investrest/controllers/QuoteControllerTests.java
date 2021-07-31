package com.example.investrest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.investrest.controllers.QuoteController;
import com.example.investrest.services.QuoteService;
import com.example.investrest.services.StockService;
import com.example.investrest.models.Quote;
import com.example.investrest.models.Stock;
import com.example.investrest.dto.QuoteDTO;

import java.util.ArrayList;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class QuoteControllerTests {

	@Autowired
	private MockMvc mockMvc;

    @Mock
    private QuoteService quoteService;

    @Mock
    private StockService stockService;

    @InjectMocks
    private QuoteController QuoteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(QuoteController).build();
    }

    @Test
    public void testGetAllQuotes() throws Exception {
        // Mock variables
        ArrayList<Quote> quotes = new ArrayList<Quote>();
        quotes.add(new Quote("PETR4"));
        quotes.add(new Quote("VALE5"));

        // Mock service function
        verify(quoteService, times(0)).findAllQuotes();
        when(quoteService.findAllQuotes()).thenReturn(quotes);

        // Hit the endpoint and assert test
        this.mockMvc.perform(get("/quote/all"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                        "[{'id':null,'stockId':'PETR4','quotes':{}},{'id':null,'stockId':'VALE5','quotes':{}}]"
                    ));
        verify(quoteService, times(1)).findAllQuotes();
    }

    @Test
    public void testGetOneQuoteValid() throws Exception {
        // Mock variables
        String stockId = "PETR4";
        Quote quote = new Quote(stockId);

        // Mock service function
        verify(quoteService, times(0)).findOneQuote(stockId);
        when(quoteService.findOneQuote(stockId)).thenReturn(quote);

        // Hit the endpoint and assert test
        this.mockMvc.perform(get("/quote/{stockId}", stockId))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                        "{'id':null,'stockId':'PETR4','quotes':{}}"
                    ));
        verify(quoteService, times(1)).findOneQuote(stockId);
    }
    
    @Test
    public void testGetOneQuoteInvalid() throws Exception {
        // Mock variables
        String stockId = "PETR4";

        // Mock service function
        verify(quoteService, times(0)).findOneQuote(stockId);
        when(quoteService.findOneQuote(stockId)).thenReturn(null);

        // Hit the endpoint and assert test
        this.mockMvc.perform(get("/quote/{stockId}", stockId))
                    .andExpect(status().is(404));
        verify(quoteService, times(1)).findOneQuote(stockId);
    }

    /*
    @Test
    public void testAddUpdateQuote() throws Exception {
        // Mock variables
        String stockId = "PETR4";
        Stock[] stocks = new Stock[1];
        stocks[0] = new Stock("PETR4", "desc");
        
        LocalDate date = LocalDate.now();
        Double price = 30.0;
        QuoteDTO quoteDTO = new QuoteDTO(stockId, date, price);
        Quote quote = quoteDTO.createQuote();

        // Mock service function
        
        // Strict stubbing argument mismatch. Please check:
        // - this invocation of 'saveQuote' method:
        //    quoteService.saveQuote(
        //    com.example.investrest.dto.QuoteDTO@5d05ef57,
        //    [com.example.investrest.models.Stock@585c13de]
        //); -> at com.example.investrest.controllers.QuoteController.addNewQuote(QuoteController.java:47)
        //- has following stubbing(s) with different arguments:
        //    1. quoteService.saveQuote(
        //    com.example.investrest.dto.QuoteDTO@58065f0c,
        //    [com.example.investrest.models.Stock@585c13de]
        //); -> at com.example.investrest.QuoteControllerTests.testAddUpdateQuote(QuoteControllerTests.java:138)
        when(stockService.getStocksREST()).thenReturn(stocks);
        when(quoteService.saveQuote(quoteDTO, stocks)).thenReturn(quote);
        
        JSONObject content = new JSONObject();
        content.put("stockId", stockId);
        content.put("date", date);
        content.put("price", price);

        // Hit the endpoint and assert test
        this.mockMvc.perform(  
                        post("/quote/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString())
                    )
                    .andDo(print())
                    .andExpect(status().is(201));
    }
    */
}
