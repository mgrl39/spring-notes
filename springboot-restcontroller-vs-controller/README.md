# Spring Boot Web Content - Sessió 2: Controller vs RestController

Aquest projecte aprofundeix en la construcció d’una aplicació web utilitzant **Spring Boot amb Spring MVC**, fent una comparativa pràctica entre `@RestController` i `@Controller`. A diferència de la primera sessió (on s'utilitzava `@RestController` per retornar JSON), ara implementem un **servidor web real** amb retorn de vistes HTML mitjançant **Thymeleaf**.

---

## 🎯 Objectiu del projecte

Desenvolupar una aplicació que:

* Tingui una pàgina inicial (`index.html`) amb un formulari.
* Permeti enviar dades a través d’un formulari cap a un endpoint `/greeting`.
* Mostri totes les paraules rebudes en una taula HTML.
* Separi responsabilitats en capes (`Controller`, `Service`), utilitzant bones pràctiques.

> Seguint el tutorial oficial de Spring: [Serving Web Content](https://spring.io/guides/gs/serving-web-content), però ampliant-lo amb lògica pròpia i components típics d’una aplicació real.

---

## 🧩 Estructura i dependències

Aquest projecte s’ha generat amb [start.spring.io](https://start.spring.io) amb les dependències següents:

* **Spring Web**: per poder crear controladors HTTP.
* **Thymeleaf**: per generar vistes HTML des del servidor.
* **Spring Boot DevTools**: per permetre el *live reload* automàtic quan es fan canvis.

Spring Boot DevTools permet:

* Hot reloading (reinici automàtic de l’app quan hi ha canvis).
* Desactivació de la memòria cau de plantilles (important per veure els canvis de Thymeleaf a temps real).

---

## 📌 Implementació pas a pas

### ✅ 1. Crear el controlador amb `@Controller`

El controlador gestiona les peticions entrants. En aquest cas, el `GreetingController` és el punt d’entrada per a `/greeting`.

```java
@Controller
@RequestMapping(GreetingController.GREETING_CONTROLLER)
public class GreetingController {
    public static final String GREETING_CONTROLLER = "/greeting";

    @Value("${welcome-text}")
    String wel;

    @Autowired
    GreetingService service;

    @GetMapping
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        System.out.println(wel); // Mostra el missatge configurat via @Value
        service.addWord(name); // Afegeix el nom a la llista
        model.addAttribute("msgs", service.getAllMessages()); // Afegeix la llista al model per ser usada a la vista
        return "greeting"; // Retorna la vista greeting.html
    }
}
```

<details>
<summary>ℹ️ Explicació detallada de conceptes clau</summary>

* `@Controller`: Anotació que marca una classe com a controlador que retorna vistes HTML.
* `@RequestMapping(...)`: Defineix la ruta base per tots els mètodes del controlador.
* `@GetMapping(...)`: Indica que aquest mètode respon a peticions GET.
* `@RequestParam(...)`: Captura paràmetres de la URL (ex: `/greeting?name=Joan`).
* `@Value(...)`: Injecta valors definits al fitxer `application.properties`. Exemple: `@Value("${welcome-text}")` injecta "Benvingut!".
* `@Autowired`: Fa que Spring injecti automàticament una instància del `GreetingService` (no cal fer `new`).
* `Model`: Permet passar dades des del backend cap a la vista. `addAttribute("msgs", ...)` afegeix l'atribut `msgs` perquè sigui accessible des de Thymeleaf.

</details>

---

### ✅ 2. Crear el servei amb `@Service`

La lògica d’emmagatzemar les paraules s’abstrau dins d’un servei.

```java
@Service
public class GreetingService {
    private List<String> messages = new ArrayList<>();

    public void addWord(String w) {
        messages.add(w);
    }

    public List<String> getAllMessages() {
        return messages;
    }
}
```

<details>
<summary>📘 Què és un `@Service` i un Singleton?</summary>

* `@Service` indica que aquesta classe forma part de la lògica del negoci (business logic).
* Spring crea una **única instància** d’aquesta classe (patró *Singleton*) i la reutilitza allà on es necessiti.
* Això permet que la llista `messages` es mantingui durant tota l’execució del servidor.

</details>

---

### ✅ 3. Crear vistes HTML amb Thymeleaf

#### greeting.html

```html
<form method="get" action="/greeting">
    <input type="text" name="name" placeholder="name" />
    <input type="submit">
</form>

<p th:text="|Hello, ${msgs}!|" />

<table border="2">
    <tr th:each="el: ${msgs}">
        <td th:text="${el}"></td>
    </tr>
</table>
```

<details>
<summary>📘 Explicació de Thymeleaf</summary>

* `th:each="el: ${msgs}"`: fa un bucle per cada element de la llista `msgs`.
* `th:text="${el}"`: mostra el contingut de cada paraula en una cel·la HTML.
* Permet integrar dades del backend directament en la vista.

</details>

#### index.html

Una pàgina HTML bàsica a `resources/static/index.html` que actua com a entrada de l’aplicació:

```html
<form method="get" action="/greeting">
    <input type="text" name="name" placeholder="name" />
    <input type="submit">
</form>
```

Quan es fa submit, redirigeix a `/greeting?name=...`, afegint el nom a la llista.

---

### ✅ 4. Injectar valors de configuració amb `@Value`

A `application.properties`:

```properties
spring.application.name=springboot-restcontroller-vs-controller
welcome-text=Benvingut!
```

Aquest valor es pot mostrar o utilitzar dins del controlador:

```java
@Value("${welcome-text}")
String wel;
```

Això permet fer **configuració externa** sense modificar el codi font.

---

### ✅ 5. @Bean vs @Service

Inicialment es podia crear una instància compartida de la llista amb un `@Bean`:

```java
@Bean
public List<String> getMessages() {
    return new ArrayList<>();
}
```

Però la pràctica recomana usar `@Service`, ja que encapsula millor la lògica i segueix el patró d’arquitectura típic MVC (Model - View - Controller).

---

## 📊 Comparativa Controller vs RestController

| Característica       | @Controller                  | @RestController            |
| -------------------- | ---------------------------- | -------------------------- |
| Tipus de retorn      | Vista (HTML)                 | JSON o altres dades        |
| Ús habitual          | Aplicacions web              | APIs REST                  |
| Necessita plantilla? | Sí (Thymeleaf, JSP, etc.)    | No                         |
| Exemple de retorn    | "greeting" (nom d'una vista) | new Hello("Món") (objecte) |

---

## 🔁 Flux complet de funcionament

1. L’usuari accedeix a `/` i introdueix un nom al formulari.
2. Spring envia una petició GET a `/greeting?name=...`.
3. El `GreetingController` rep el nom i el passa al `GreetingService`.
4. El servei afegeix el nom a una llista en memòria.
5. El controlador envia la llista al `Model`.
6. Thymeleaf renderitza la vista amb la llista de noms dins d’una taula.

---

## ✅ Conclusions

✔️ S’ha aplicat correctament l’estructura MVC típica de Spring Boot.
✔️ S’ha diferenciat clarament `@Controller` de `@RestController`.
✔️ S’han usat components essencials com `@Value`, `@Service`, `@Autowired`, `Model`, `@RequestParam`.
✔️ Es pot estendre aquest projecte fàcilment per afegir persistència (JPA, base de dades) o API externa.

> Aquesta pràctica mostra com construir una aplicació web Spring completa, amb vistes, formularis i estructura modular pensada per escalar. És la base per a projectes més avançats.
