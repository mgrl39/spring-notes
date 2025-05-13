# spring-notes
## SESSIÓ 1. Introducció

1. Què és Spring i què podem fer?
- **Spring** és un framework lleuger de codi obert creat per Rob Jhonson per facilitar el desenvolupament d'aplicacions modernes (Microserveis, Programació reactiva, Cloud, Web Apps etc.) sobre Java o Kotlin.
- **Organitzat** en mòduls independents (Spring boot, Spring framework, etc.)
- **Alternatives**: Java EE --> ara Jakarta EE, Quarkus, Micronaut, Vert.x, Ktor (Kotlin)

2. Exemples d'API REST (Explorar els exemples)
- https://api.github.com
- [Dades obertes DIBA](https://dadesobertes.diba.cat/datasets)
- [Dades obertes Gencat](https://administraciodigital.gencat.cat/ca/dades/dades-obertes/inici/)
- [Chuck Norris API](https://api.chucknorris.io/)
- [RapidAPI](https://rapidapi.com/), plataforma concentradora d’apis

| **Create** `POST`          | **Read** `GET`                    | **Update** `PUT`                | **Update** `PATCH`                   | **Delete** `DELETE`                        |
|---------------------------|----------------------------------|--------------------------------|-------------------------------------|--------------------------------------------|
| Crear un nou recurs       | Obtenir un recurs o un conjunt   | Actualitzar un recurs          | Actualitzar part o diversos recursos | Eliminar un recurs                         |
| Operació no segura i no idempotent | Operació segura i idempotent    | Operació no segura i idempotent | Operació no segura i idempotent      | Operació no segura i idempotent            |
| Resposta: 201 (OK) o el tipus d’error | Resposta: 200 (OK) o el tipus d’error | Resposta: 200 (OK) o el tipus d’error | Resposta: 200 (OK) o el tipus d’error  | Resposta: 204 (OK) sense contingut o el tipus d’error |
| Es podria retornar una referència o recurs |                                  | Es podria retornar el recurs    | Es podria retornar el recurs         |                                            |

```java
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
```
