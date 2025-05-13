# 🦾 Spring Boot Chuck Norris Quote Generator - Sessió 3

Aquest projecte mostra com **consumir dades d'una API REST externa** ([https://api.chucknorris.io](https://api.chucknorris.io)) i mostrar-les en una pàgina web feta amb **Spring Boot**, **Thymeleaf** i **Bootstrap**.

> L’objectiu és integrar les diferents capes d’una aplicació (Controller, Service, Model i Vista) per consumir i mostrar dades externes, de forma clara i amb estil professional.

---

## 🎯 Objectius del projecte

* Consumir una API REST externa i obtenir una frase aleatòria de Chuck Norris.
* Mostrar la frase en una pàgina HTML.
* Mostrar la icona de Chuck Norris i una **imatge aleatòria** extra (usant `https://picsum.photos/300/200`).
* Estilitzar la pàgina amb **Bootstrap** i un fitxer CSS personalitzat.
* Aprendre a estructurar una aplicació en capes (`@Controller`, `@Service`, `Model`, `Vista`) amb bones pràctiques.

---

## 🧱 Estructura del projecte

| Capa          | Què fa                                                         |
| ------------- | -------------------------------------------------------------- |
| `@Controller` | Gestiona les peticions del navegador i retorna una pàgina HTML |
| `@Service`    | Connecta amb la API de Chuck Norris i recupera les dades       |
| `Model`       | Representa la frase rebuda en format JSON                      |
| `HTML + CSS`  | Mostra la frase i la imatge en pantalla amb estil              |

---

## 🚀 Què fa exactament aquest projecte?

Quan entres a `http://localhost:8080/quote`:

1. El navegador fa una petició GET.
2. El controlador (`QuoteController`) rep la petició.
3. El controlador crida el servei (`QuoteService`) per demanar una frase aleatòria a `https://api.chucknorris.io`.
4. L'API retorna un JSON amb la frase.
5. El servei converteix el JSON a un objecte Java (`Quote`).
6. El controlador afegeix aquest objecte al `Model` i mostra la vista `chuck.html`.
7. `chuck.html` mostra la frase, l’ID, la URL, la imatge de Chuck Norris i una imatge aleatòria.
8. L’usuari pot fer clic a **"Nova Frase"** per refrescar tot.

---

## 🧠 Capes del projecte i funcionament detallat

### ✅ 1. `QuoteService.java` → la capa de servei

```java
@Service
public class QuoteService {
    RestTemplate restTemplate = new RestTemplate();

    public Quote getQuote() {
        return restTemplate.getForObject("https://api.chucknorris.io/jokes/random", Quote.class);
    }
}
```

#### Explicació:

* `@Service`: diu a Spring que aquesta classe fa feina de "servei". És una capa que s'encarrega de la **lògica del negoci** (en aquest cas, recuperar dades d'una API externa).
* `RestTemplate`: eina que ens permet fer peticions HTTP (GET, POST, etc.).
* `getForObject(...)`: fa una petició GET a la URL donada, i converteix automàticament la resposta JSON a un objecte `Quote`.

---

### ✅ 2. `Quote.java` → el model de dades

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private String url;
    private String value;
    private String id;
    private String icon_url;
    // Getters i setters
}
```

#### Explicació:

* Aquesta classe representa les dades que rebem del JSON de l’API de Chuck Norris.
* Cada camp (`id`, `url`, `value`, `icon_url`) coincideix amb els noms del JSON rebut.
* `@JsonIgnoreProperties(ignoreUnknown = true)`: fa que Spring ignori els camps que no hem declarat (com `created_at`, `categories`...).

---

### ✅ 3. `QuoteController.java` → el controlador

```java
@Controller
@RequestMapping("/quote")
public class QuoteController {

    @Autowired
    QuoteService quoteService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("quote", quoteService.getQuote());
        return "chuck";
    }
}
```

#### Explicació:

* `@Controller`: diu a Spring que aquesta classe **gestiona pàgines web** (no JSON).
* `@RequestMapping("/quote")`: totes les rutes d'aquesta classe començaran per `/quote`.
* `@GetMapping`: aquest mètode respon a peticions GET.
* `Model`: conté les dades que Thymeleaf utilitzarà per renderitzar la vista.
* `quoteService.getQuote()`: crida al servei per obtenir una frase aleatòria.
* `model.addAttribute("quote", ...)`: envia l’objecte `quote` al HTML.
* `return "chuck"`: mostra la vista `chuck.html`.

---

### ✅ 4. `chuck.html` → la vista HTML (amb Thymeleaf + Bootstrap)

```html
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Chuck Norris Quote</title>

    <!-- Bootstrap des de WebJars -->
    <link rel="stylesheet" th:href="@{/webjars/bootstrap/5.1.3/css/bootstrap.min.css}"/>

    <!-- Estil personalitzat -->
    <link rel="stylesheet" href="assets/styles.css"/>
</head>
<body class="bg-light text-dark">
<div class="container mt-5">
    <div class="card p-4 shadow-sm">

        <!-- Frase de Chuck Norris -->
        <p><strong>ID:</strong> <span th:text="${quote.id}"></span></p>
        <p><strong>Value:</strong> <span th:text="${quote.value}"></span></p>
        <p><strong>URL:</strong> <a th:href="${quote.url}" th:text="${quote.url}" target="_blank"></a></p>

        <!-- Imatge del Chuck -->
        <p><strong>Icon:</strong></p>
        <img th:src="${quote.icon_url}" alt="Chuck Norris" class="img-thumbnail">

        <hr>

        <!-- Imatge aleatòria -->
        <p><strong>Random Image:</strong></p>
        <img src="https://picsum.photos/300/200" alt="Random image" class="img-thumbnail">

        <!-- Botó per obtenir nova frase -->
        <div class="mt-4 text-center">
            <a href="/quote" class="btn btn-primary">Nova Frase</a>
        </div>
    </div>
</div>
</body>
</html>
```

#### Explicació d’etiquetes i funcionalitat:

* `th:text="${quote.value}"`: mostra el text de la frase.
* `th:href`, `th:src`: Thymeleaf injecta dinàmicament les dades del backend.
* `class="container mt-5"`: Bootstrap per espaiat i centrat.
* `card`, `shadow-sm`, `img-thumbnail`, `btn`, `bg-light`, `text-dark`: classes de Bootstrap per fer que tot quedi bonic automàticament.

---

## 🎨 Estils personalitzats

Fitxer: `src/main/resources/static/assets/styles.css`

```css
img {
    max-width: 100px;
    max-height: 100px;
}
```

#### Com funciona?

* Aquest fitxer CSS està dins la carpeta `static`, que és la ruta **automàtica** de recursos públics a Spring Boot.
* Es carrega amb la línia:

```html
<link rel="stylesheet" href="assets/styles.css"/>
```

* El navegador llegeix aquest CSS i l’aplica a totes les `<img>`.

---

## 🧩 Com es carrega Bootstrap?

### ✅ WebJars

Hem afegit Bootstrap al `pom.xml` com a WebJar:

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>bootstrap</artifactId>
    <version>5.1.3</version>
</dependency>
```

### ✅ Per què WebJars?

* Ens permet tenir Bootstrap **integrat al projecte Java**, sense dependències externes.
* El CSS s’accedeix amb:

```html
<link rel="stylesheet" th:href="@{/webjars/bootstrap/5.1.3/css/bootstrap.min.css}" />
```

* Spring serveix automàticament aquest arxiu quan accedeixes a `/webjars/...`.

---

## 🧪 Altres fitxers del projecte

### `index.html` (a `static/index.html`)

```html
<a href="/quote">Quote Button</a>
```

Una pàgina simple amb un enllaç cap a `/quote`.

---

### `error.html` (opcional)

```html
<h1>NOT FOUND</h1>
```

Pàgina d’error per quan vas a una ruta que no existeix.

---

## 🔁 Com funciona el botó "Nova Frase"?

Aquest botó és un enllaç:

```html
<a href="/quote" class="btn btn-primary">Nova Frase</a>
```

Cada vegada que el cliques:

* El navegador torna a fer una petició GET a `/quote`.
* El controlador demana una **nova frase aleatòria** a l’API.
* Es mostra una nova frase + nova imatge aleatòria (gràcies a `https://picsum.photos`).

---

## 🛠 Tecnologies utilitzades

* **Spring Boot** – Framework principal per crear l’aplicació.
* **Spring MVC** – Per treballar amb capes (`@Controller`, `@Service`...).
* **Thymeleaf** – Per generar HTML des del servidor.
* **Bootstrap (via WebJars)** – Per estilitzar la pàgina fàcilment.
* **API REST** – Chuck Norris API ([https://api.chucknorris.io](https://api.chucknorris.io)).
* **CSS personalitzat** – Fitxer dins `static/assets`.

---

## ✅ Conclusions

✔️ El projecte mostra clarament com consumir dades d’una API REST i mostrar-les en una pàgina web.

✔️ Està ben estructurat en capes (Controller, Service, Model, Vista).

✔️ S’utilitza Thymeleaf per injectar dades al HTML i Bootstrap per donar-li estil.

✔️ El fitxer `README.md` explica **TOT** amb detall perquè no sigui necessari mirar el codi font.

---

## 📎 Enllaços útils

* [API Chuck Norris](https://api.chucknorris.io)
* [Guia Spring Boot + Thymeleaf](https://spring.io/guides/gs/serving-web-content/)
* [WebJars oficial](https://www.webjars.org/)
* [Bootstrap 5 Docs](https://getbootstrap.com/)

---

## 🗂 Fitxers importants

| Fitxer                 | Què fa                                                   |
| ---------------------- | -------------------------------------------------------- |
| `QuoteService.java`    | Connecta amb l'API i retorna una frase                   |
| `Quote.java`           | Representa una frase com a objecte Java                  |
| `QuoteController.java` | Controlador que mostra la pàgina amb la frase            |
| `chuck.html`           | HTML que mostra la frase i la imatge                     |
| `styles.css`           | Estils personalitzats per la pàgina                      |
| `pom.xml`              | Defineix les dependències del projecte (Bootstrap, etc.) |
| `index.html`           | Pàgina de benvinguda                                     |
| `error.html`           | Pàgina d’error bàsica                                    |

## Altre projecte mes complert fora d'aquest grup de sessió
https://github.com/mgrl39/consum-randomuser-api
