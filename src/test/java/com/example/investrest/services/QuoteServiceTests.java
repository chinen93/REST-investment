package com.example.investrest;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.investrest.repositories.QuoteRepository;
import com.example.investrest.services.QuoteService;

import com.example.investrest.models.Stock;
import com.example.investrest.models.Quote;
import com.example.investrest.dto.QuoteDTO;

import java.time.LocalDate;



@ExtendWith(MockitoExtension.class)
class QuoteServiceTests {

    @Mock
    private QuoteRepository quoteRepository;

    private QuoteService quoteService;
    private Stock[] stocks = new Stock[2];

    @BeforeEach
    void initUseCase() {
        quoteService = new QuoteService(quoteRepository);

        stocks[0] = new Stock("PETR4", "desc");
        stocks[1] = new Stock("VALE5", "desc");
    }

    @Test
    void testInStock(){
        assertTrue(quoteService.inStock("PETR4", stocks));
        assertFalse(quoteService.inStock("AUX00", stocks));
    }

    @Test
    void testFindAllQuotes(){
        quoteService.findAllQuotes();
        verify(quoteRepository, times(1)).findAll();
    }

    @Test
    void testFindOneQuote(){
        String stockId = "PETR4";
        quoteService.findOneQuote(stockId);
        verify(quoteRepository, times(1)).findByStockId(stockId);
    }

    @Test
    void testSaveQuoteNewQuote(){
        String stockId = "PETR4";
        LocalDate date = LocalDate.now();
        Double price = new Double(35);

        when(quoteRepository.findByStockId(stockId)).thenReturn(null);
        QuoteDTO quoteDTO = new QuoteDTO(stockId, date, price);
        Quote result = quoteService.saveQuote(quoteDTO, stocks);
        verify(quoteRepository, times(1)).save(result);          
    }
}
