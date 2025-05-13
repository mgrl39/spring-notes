package net.xeill.elpuig.springbootrestfulconsumingapi.controller;

import net.xeill.elpuig.springbootrestfulconsumingapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    Controlador web. Rep peticions i retorna pàgines HTML.
 */
@Controller
@RequestMapping(QuoteController.QUOTES_PATH)
public class QuoteController {
    public static final String QUOTES_PATH = "/quote";

    @Autowired
    QuoteService quoteService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("quote", quoteService.getQuote());
        return "chuck";
    }
}
