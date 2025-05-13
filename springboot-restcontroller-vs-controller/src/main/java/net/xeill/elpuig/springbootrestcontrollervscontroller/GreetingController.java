package net.xeill.elpuig.springbootrestcontrollervscontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

// Implementem un servei web fent servir l'annotation @Controller
@Controller
@RequestMapping(GreetingController.GREETING_CONTROLLER)
public class GreetingController {
    // Usar una constant per definir el endpoint principal de la classe “/greeting”
    public static final String GREETING_CONTROLLER = "/greeting";

    @Value("${welcome-text}")
    String wel;

    // List<String> msgs = new ArrayList<>();
    @Autowired
    GreetingService service;

    // http://localhost:8080/greeting?name=asdad&name=xd&name=pepe&name=test
    //@GetMapping("/greeting")
    // Canviar la String name per un List<String> i adaptar l’html amb el Thymeleaf per
    // poder veure a la mateixa plana tots els texts entrats i desats a l’array
    @GetMapping
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")String name, Model model) {
        //model.addAttribute("name", name);
        //model.addAttribute("wel", wel);
        System.out.println(wel);
        service.addWord(name);
        model.addAttribute("msgs", service.getAllMessages());
        return "greeting";
    }
}
