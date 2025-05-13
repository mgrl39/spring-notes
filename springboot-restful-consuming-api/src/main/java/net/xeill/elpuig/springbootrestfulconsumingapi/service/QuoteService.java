package net.xeill.elpuig.springbootrestfulconsumingapi.service;

import net.xeill.elpuig.springbootrestfulconsumingapi.model.Quote;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    RestTemplate restTemplate = new RestTemplate();

    public Quote getQuote() {
        return restTemplate.getForObject("https://api.chucknorris.io/jokes/random", Quote.class);
    }
}
