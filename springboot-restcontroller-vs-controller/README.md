# Spring Boot Web Content - Sessio 2: Controller vs RestController

Aquest projecte desenvolupa una aplicació web amb Spring Boot centrada en l’ús del patró Model-View-Controller (“MVC”). A diferència de la sessió anterior on usàvem `@RestController` per retornar JSON, en aquesta sessió hem treballat amb `@Controller` per retornar vistes HTML mitjançant el motor de plantilles Thymeleaf.

---

## 🧩 Objectiu del projecte

Aquest projecte té com a objectiu entendre i aplicar la diferència entre `@RestController` i `@Controller`, aprenent a:

* Implementar un servidor web amb `@Controller`
* Mostrar vistes HTML dinàmiques amb **Thymeleaf**
* Passar dades des del backend al frontend via `Model`
* Injectar dades amb `@Autowired`, `@Value` i separar lògica amb `@Service`

A més, segueix i amplia el tutorial oficial de Spring: [Serving Web Content](https://spring.io/guides/gs/serving-web-content).

---

## 🪡 Estructura del projecte

El projecte s’ha generat amb [Spring Initializr](https://start.spring.io) amb les següents dependències:

* `Spring Web`
* `Thymeleaf`
* `Spring Boot DevTools`

Aquestes eines ens han permès crear una aplicació amb suport per vistes HTML, auto recàrrega i estructura modular.

---

## 📉 Requisits i tasques completades

### ✅ Seguir el guia oficial i adaptar-lo

S’ha seguit el tutorial oficial `Serving Web Content`, però s’ha adaptat i millorat amb funcionalitats extra, com:

* Afegir un servei per guardar paraules entrades
* Mostrar totes les paraules a la mateixa pàgina
* Formularis en diverses vistes que apunten al mateix endpoint

<details>
<summary>🔍 Codi base adaptat amb explicació</summary>

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
        service.addWord(name);
        model.addAttribute("msgs", service.getAllMessages());
        return "greeting";
    }
}
```

</details>

---

### ✅ Canviar l'ús de `@RestController` per `@Controller`

A diferència de la sessió 1, aquesta vegada la resposta és una **vista HTML**. L’objectiu era construir una web real, no una API JSON.

<details>
<summary>ℹ️ Explicació detallada de les anotacions</summary>

* `@Controller`: Permet retornar el nom d’una plantilla HTML (no JSON)
* `@RequestMapping(...)`: Defineix el path base per als mètodes d’aquesta classe
* `@Value(...)`: Permet accedir a valors definits a `application.properties`
* `@Autowired`: Injecció automàtica d’una dependència
* `Model`: Objecte que permet passar dades del controlador a la vista

</details>

---

### ✅ Crear un servei amb `@Service` per gestionar l’estat

Anteriorment es proposava fer un `@Bean` per retornar una llista. En aquest exercici es millora aquesta pràctica creant una classe `GreetingService`, marcada com a singleton amb `@Service`.

<details>
<summary>📄 GreetingService.java</summary>

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

</details>

Aquesta classe s’encarrega d’emmagatzemar les paraules rebudes. Al ser singleton, sempre treballa amb la mateixa instància.

---

### ✅ Crear vistes HTML amb Thymeleaf

S’han creat dues vistes: `index.html` (pàgina inicial) i `greeting.html` (pàgina amb taula dinàmica).

<details>
<summary>📄 greeting.html</summary>

```html
<form method="get" action="/greeting">
    <input type="text" name="name" placeholder="name" />
    <input type="submit">
</form>

<table border="2">
    <tr th:each="el: ${msgs}">
        <td th:text="${el}"></td>
    </tr>
</table>
```

</details>

Utilitzem:

* `th:each` per iterar sobre la llista de missatges
* `th:text` per mostrar cada valor en una cel·la

Aquesta pàgina es mostra cada vegada que afegim un nom nou a la URL o al formulari.

---

### ✅ Formulari també a `index.html`

Spring Boot detecta automàticament `static/index.html` com a pàgina inicial. Aquest fitxer conté un formulari que envia dades a `/greeting`.

---

### ✅ Injectar paràmetres de `application.properties`

<details>
<summary>📄 application.properties</summary>

```properties
spring.application.name=springboot-restcontroller-vs-controller
welcome-text=Benvingut!
```

</details>

Amb `@Value("${welcome-text}")` es pot accedir a aquest text des del controlador.

---

## 📊 Comparativa Controller vs RestController

| Característica | @Controller                | @RestController                  |
| -------------- | -------------------------- | -------------------------------- |
| Retorna        | Nom de vista (HTML)        | Objecte Java (convertit a JSON)  |
| Frontend       | Amb Thymeleaf              | Sense vista, es mostra com a API |
| Adequat per... | Web tradicional amb vistes | APIs REST per serveis externs    |

---

## 🔄 Flux del funcionament

1. L’usuari accedeix a `/` i omple el formulari amb un nom
2. El navegador envia la petició GET a `/greeting?name=ElNom`
3. El controlador afegeix el nom a la llista (via `GreetingService`)
4. Passa la llista al `Model`
5. Thymeleaf genera una taula HTML amb tots els noms

---

## 📊 Conclusions

* ✔️ S’ha aplicat el patró MVC amb separació clara de responsabilitats
* ✔️ S’han aplicat anotacions clau: `@Controller`, `@Service`, `@Value`, `@Autowired`, `@RequestParam`
* ✔️ S’ha demostrat l’ús de formularis i la renderització de dades amb Thymeleaf
* ✔️ Es pot reutilitzar aquest patró per aplicacions web amb lògica més complexa

> Aquesta pràctica demostra l’ús complet d’un servidor Spring Boot amb suport per vistes, formularis i dades dinàmiques.
