# Spring Boot CRUD Intro - Sessió 1

Aquest projecte és una introducció a Spring Boot, seguint el [Quickstart oficial de Spring](https://spring.io/quickstart) i completant una sèrie de tasques extres demanades pel professorat.

## 🧩 Estructura del projecte

Aquest projecte ha estat creat a partir de **start.spring.io**, afegint la dependència `Spring Web`.
El projecte genera automàticament una aplicació bàsica amb una classe principal (`main`) i un exemple de controladora REST.

---

## 📌 Requisits de la pràctica i com els he completat

### ✔️ Seguir el Quickstart, executar i entendre l’exemple

1. S’ha creat el projecte des de start.spring.io amb la dependència web.
2. S’ha obert amb un IDE i modificat el fitxer `SpringbootCrudIntroApplication.java`.
3. S’ha afegit un endpoint `/hello-antic`.
4. S’ha executat correctament accedint a: [http://localhost:8080/hello-antic](http://localhost:8080/hello-antic)

<details>
<summary>🔍 Codi amb explicació detallada</summary>

```java
@SpringBootApplication
@RestController
public class SpringbootCrudIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCrudIntroApplication.class, args);
    }

    @GetMapping("/hello-antic")
    public String hello(@RequestParam(value = "name", defaultValue = "Món") String name) {
        String missatge = String.format("Hola %s!", name);
        return "<p>" + missatge + "</p>";
    }
}
```

### Explicacions:

* `@SpringBootApplication`: Anotació principal que configura tota l’aplicació.
* `@RestController`: Indica que aquesta classe és un controlador REST. Tots els mètodes amb `@GetMapping` retornaran dades per HTTP.
* `@GetMapping("/hello-antic")`: Associa el mètode amb el path `/hello-antic`.
* `@RequestParam(...)`: Permet rebre paràmetres de la URL, per exemple: `/hello-antic?name=Joan`.

</details>

---

## ✔️ Tasques extres

### ✅ Treure l'endpoint de la classe principal (`main`) i crear una classe `RestController` separada

S’ha creat una nova classe `HelloResource.java` per contenir els endpoints.

<details>
<summary>📄 HelloResource.java</summary>

```java
@RestController
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

</details>

### ✅ Usar una constant per definir l'endpoint principal

A la línia `public static final String HELLO_RESOURCE = "/hello"`, es defineix una constant reutilitzable per mantenir el codi net i evitable errors.

---

### ✅ Afegir un `index.html`

Aquest fitxer es troba dins de `src/main/resources/static`. Quan es visita `http://localhost:8080/`, es mostra automàticament.

<details>
<summary>📄 index.html</summary>

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring 2025</title>
</head>
<body>
    <h1>Welcome Spring Daw</h1>
    <p>No m'ha fet falta configurar res per aconseguir un missatge de benvinguda. Només cal crear index.html a static.</p>

    <form method="get" action="/hello">
        <input type="text" name="name" placeholder="name" />
        <input type="submit">
    </form>
</body>
</html>
```

</details>

El formulari fa una petició `GET` al servidor amb el nom escrit, que és recollit pel paràmetre `name`.

---

### ✅ Crear un formulari HTML que interactua amb `/hello`

Quan l’usuari introdueix un nom i envia el formulari, el backend respon amb un JSON com aquest:

```json
{
  "id": 1,
  "message": "Joan"
}
```

---

## 🧱 Classe `Hello.java`

Aquesta classe és un **POJO** (Plain Old Java Object), que serveix com a model per a les respostes JSON del servidor.

<details>
<summary>📄 Hello.java</summary>

```java
public class Hello {
    int id;
    String message;

    public Hello(String message) {
        this.message = message;
        id = 1;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
```

</details>

---

## ⚙️ Fitxer `application.properties`

Aquest fitxer permet configurar variables per a l’aplicació, com el nom o el port.

<details>
<summary>📄 application.properties</summary>

```properties
spring.application.name=springboot-crud-intro
welcome-value=Benvingut
# server.port=8082 (si vols canviar el port)
```

També es pot fer servir un fitxer `application.yml` per a configuracions més complexes.

</details>

---

## 📌 Conceptes explicats

<details>
<summary>ℹ️ @RestController</summary>
Indica que la classe conté mètodes que gestionen peticions HTTP i les respostes van directament al navegador o aplicació client (no una vista HTML). Retorna dades (com JSON), no pàgines.

</details>

<details>
<summary>ℹ️ @RequestParam</summary>
Permet obtenir valors des de la URL. Per exemple:  
`/hello?name=Joan`  
Això fa que `name = "Joan"` dins del mètode.

També es pot afegir `defaultValue`, per si no es passa cap paràmetre.

</details>

<details>
<summary>ℹ️ @GetMapping</summary>
Defineix un mètode que respon a peticions HTTP de tipus GET (com quan accedim a una URL al navegador).  
Per exemple:  
`@GetMapping("/hello")` fa que el mètode respongui a `/hello`.

</details>

<details>
<summary>ℹ️ Spring Boot i start.spring.io</summary>
Spring Boot és un framework per crear aplicacions Java molt ràpidament, amb configuració mínima.

[start.spring.io](https://start.spring.io/) és una eina web que permet generar plantilles de projectes de forma ràpida.

</details>