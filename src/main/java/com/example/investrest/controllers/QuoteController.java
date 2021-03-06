/**
    https://spring.io/guides/gs/accessing-data-mysql/
 */
package com.example.investrest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.investrest.models.Quote;
import com.example.investrest.models.Stock;
import com.example.investrest.dto.QuoteDTO;
import com.example.investrest.services.QuoteService;
import com.example.investrest.services.StockService;

import java.time.LocalDate;


@Controller // This means that this class is a Controller
@RequestMapping(path="/quote") // This means URL's start with /quote (after Application path)
public class QuoteController {

    private static final Logger log = LoggerFactory.getLogger(QuoteController.class);

	@Autowired
    private QuoteService quoteService;

    @Autowired
    private StockService stockService;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public ResponseEntity<Quote> addNewQuote (@RequestBody QuoteDTO quoteDTO) {

        log.info("endpoint: '/quote/add'");

        Stock[] stocks = stockService.getStocksREST();
        Quote quote = quoteService.saveQuote(quoteDTO, stocks);

        if(quote == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(quote, HttpStatus.CREATED);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Quote> getAllQuotes() {
        log.info("endpoint: '/quote/all'");

        // This returns a JSON or XML with the users
        return quoteService.findAllQuotes();
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<Quote> getOneQuote(@PathVariable String stockId) {
        log.info("endpoint: '/quote/" + stockId + "'");

        Quote quote = quoteService.findOneQuote(stockId);

        if(quote == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(quote, HttpStatus.OK);
        }
    }
}
