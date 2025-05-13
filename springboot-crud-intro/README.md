# Spring Boot CRUD Intro - Sessió 1

Aquest projecte és una introducció a Spring Boot, seguint el Quickstart oficial i completant una sèrie de tasques extres demanades pel professorat.

## 🧩 Estructura del projecte
Aquest projecte ha estat creat a partir de start.spring.io, afegint la dependència "Spring Web". 
El projecte genera automàticament una aplicació bàsica de Spring Boot amb una classe principal (main) i un exemple de controladora REST.
---

### 📌 Requisits de la pràctica i com els he completat
#### ✔️ Seguir el Quickstart, executar i entendre l’exemple
1. S’ha creat el projecte des de start.spring.io amb la dependència web.
2. S’ha descarregat i obert amb un IDE.
3. A la classe principal (`SpringbootCrudIntroApplication.java`) s’ha afegit l’endpoint `/hello-antic` com indica el tutorial original.
4. L'aplicació ha estat executada correctament accedint a `http://localhost:8080/hello-antic`.

#### ✔️ Tasques extres
- ✅ Treure l'endpoint de la classe main
En comptes de deixar els endpoints dins la classe principal (`SpringbootCrudIntroApplication`), s’ha creat una nova classe separada anomenada `HelloResource.java`, amb l’anotació `@RestController`. Això ajuda a seguir la bona pràctica de **separació de responsabilitats**.

- ✅ Crear una classe RestController que faci el mateix
  La classe HelloResource conté ara:
    - Un endpoint principal /hello, usant una constant HELLO_RESOURCE per definir la ruta.
    - Un mètode que retorna un objecte Hello en format JSON.
    - Altres mètodes com /hello/bye i /hello/test per ampliar la funcionalitat.

```java
@RequestMapping(HelloResource.HELLO_RESOURCE)
public class HelloResource {
    public static final String HELLO_RESOURCE = "/hello";

    @GetMapping()
    public Hello hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Hello(name);
    }

    @GetMapping("bye")
    public String bye(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Adeu %s!", name);
    }

    @GetMapping("/test")
    public String test(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello Test!!";
    }
}
```
- ✅ Usar una constant per definir el endpoint principal
Com es veu a dalt, la constant `HELLO_RESOURCE = "/hello"` defineix la ruta base del RestController.

- ✅ Afegir un index.html
S’ha afegit un fitxer index.html dins del directori `src/main/resources/static`, i s’ha carregat automàticament a l’accedir a http://localhost:8080/.
Conté:
  - Un missatge de benvinguda.
  - Un formulari per enviar un nom al endpoint /hello.
```html
<form method="get" action="/hello">
    <input type="text" name="name" placeholder="name" />
    <input type="submit">
</form>
```
- ✅ Crear un formulari HTML
El formulari envia una petició GET al servidor, passant el nom com a paràmetre. Això crida el mètode del HelloResource i retorna un JSON amb el missatge.
---
### Fitxer Hello.java
És una classe POJO (Plain Old Java Object) que representa la resposta amb un missatge i un identificador:
```java
public class Hello {
    int id;
    String message;

    public Hello(String message) {
        this.message = message;
        id = 1;
    }
}
```
Quan es fa una petició a /hello?name=Joan, retorna:
```json
{
  "id": 1,
  "message": "Joan"
}
```