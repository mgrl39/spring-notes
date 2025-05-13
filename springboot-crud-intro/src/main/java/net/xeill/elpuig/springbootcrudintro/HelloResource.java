package net.xeill.elpuig.springbootcrudintro;

import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
    Com a tasca extra de la sessió 1 es demana fer el següent:
    Treure l'endpoint de la classe on hi ha el main i crear una classe RestController que faci el mateix
 */

/**
 * La classe RestController l'hem definit un endpoint principal, i totes les opcions apunten al endpoint general
 * Per tant, tenim /hello, /helo/bye y /hello/test
 */
@RestController // Aquesta liniea l'hi diu a Spring que aixo es un endpoint, noms hi haura un en tot el document crec
@RequestMapping(HelloResource.HELLO_RESOURCE)
public class HelloResource {

    // Usar una constant per definir el endpoint principal de la classe
    public static final String HELLO_RESOURCE = "/hello";

    // Aqui tenim definiida el get sobre aquest metode de aqui
    @GetMapping()
    // El RequestParame s el nom de la propietat. Hi hagi un valor o no el string contindra un valor
    // que l'hi pasem en el request param. Que fa aixo? Fa un return d'un string


    public Hello hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Hello(name);
    }

    // http:localhost:8080/hello/bye?name?DAW
    @GetMapping("bye") // Crec que es el mateix posar /bye que bye al GetMapping
    public String bye(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Adeu %s!", name);
    }

    @GetMapping("/test")
    public String test(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello Test!!";
    }

    /*
     * Si anem a localhost:8080/ es error.
     * Si anem a localhost:8080/name es Hello World!
     * Si anem a localhost:8080/name?=test apareix Hello test!
     *
     * Si en comptes de fer un return de String faig un Return d'un objecte retorna JSON doncs
     */
}
