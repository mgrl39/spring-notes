package net.xeill.elpuig.springbootcrudintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootCrudIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCrudIntroApplication.class, args);
    }

// https://spring.io/quickstart
/* http://localhost:8080/hello?name=xd */
@GetMapping("/hello")
public String hello(@RequestParam(value = "name", defaultValue = "Món") String name) {
    String missatge = String.format("Hola %s!", name);

    String comentari = """
        El mètode hello() que hem afegit està dissenyat per rebre un paràmetre de tipus String anomenat name,<br>
        i després combinar aquest paràmetre amb la paraula "Hola" dins del codi.<br><br>
        L’anotació @RestController indica a Spring que aquest codi descriu un endpoint web.<br><br>
        L’anotació @GetMapping("/hello") vincula el mètode a l'adreça http://localhost:8080/hello<br><br>
        Finalment, @RequestParam recull el valor "name" i usa "Món" si no es passa cap valor.
        """;
    return "<p>" + missatge + "</p><p>" + comentari + "</p>";
}
}
