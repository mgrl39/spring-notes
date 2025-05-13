# Spring Boot CRUD Intro - Sessió 1

Aquest projecte és una introducció a Spring Boot, seguint el [Quickstart oficial de Spring](https://spring.io/quickstart) i completant una sèrie de tasques extres demanades pel professorat.

## 🧩 Estructura del projecte

Aquest projecte ha estat creat a partir de [start.spring.io](https://start.spring.io), afegint la dependència `Spring Web`.

Això ens genera una estructura bàsica per poder crear aplicacions web amb Spring Boot de forma ràpida.

---

## 📌 Requisits de la pràctica i com els he completat

### ✔️ Seguir el Quickstart, executar i entendre l’exemple

S'ha seguit el tutorial oficial i s'ha afegit un primer endpoint dins de la classe principal.

<details>
<summary>🔍 Exemple de codi original amb explicació pas a pas</summary>

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

        String comentari = """
        El mètode hello() que hem afegit està dissenyat per rebre un paràmetre de tipus String anomenat name,<br>
        i després combinar aquest paràmetre amb la paraula "Hola" dins del codi.<br><br>
        L’anotació @RestController indica a Spring que aquest codi descriu un endpoint web.<br><br>
        L’anotació @GetMapping("/hello-antic") vincula el mètode a l'adreça http://localhost:8080/hello-antic<br><br>
        Finalment, @RequestParam recull el valor "name" i usa "Món" si no es passa cap valor.
        """;

        return "<p>" + missatge + "</p><p>" + comentari + "</p>";
    }
}
```

### Explicació detallada d’anotacions i elements:

* `@SpringBootApplication`: configura automàticament Spring Boot, escaneja components i arrenca l’aplicació.
* `@RestController`: indica que la classe conté mètodes que respondran a peticions HTTP. A diferència d’un `@Controller`, no retorna una pàgina HTML, sinó dades (en aquest cas, text o JSON).
* `@GetMapping("/hello-antic")`: associa aquest mètode amb l’URL `/hello-antic`.
* `@RequestParam`: recull els paràmetres que venen per la URL. Per exemple: `?name=Joan`. Si no arriba cap valor, agafa el valor per defecte `"Món"`.

</details>

---

## ✔️ Tasques extres

### ✅ Treure l'endpoint de la classe principal i crear una classe `RestController` separada

Aquesta és una bona pràctica: separar la configuració (`main`) de la lògica dels endpoints web.

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

### Explicació detallada:

<details>
<summary>ℹ️ Explicació d'anotacions</summary>

* `@RestController`: igual que abans, indica que aquesta classe gestionarà peticions HTTP i respondrà amb dades.
* `@RequestMapping(HELLO_RESOURCE)`: defineix una ruta base per a tots els mètodes. En aquest cas, tots començaran amb `/hello`.
* `@GetMapping()`: com que no té cap valor, aquest mètode respon a `/hello`.
* `@GetMapping("bye")`: respondrà a `/hello/bye`.
* `@RequestParam(...)`: com abans, recull el valor de la URL. Exemple: `/hello/bye?name=Maria` -> `"Adeu Maria!"`

</details>

---

### ✅ Usar una constant per definir el path principal

Definim `HELLO_RESOURCE` com a constant per reutilitzar-la i evitar errors per escriure la ruta manualment diverses vegades.

---

### ✅ Afegir un `index.html`

Quan el servidor s’aixeca, Spring Boot detecta automàticament el fitxer `index.html` a `src/main/resources/static`.

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

Quan s’omple el formulari i es prem "Enviar", el navegador fa una petició GET a `/hello?name=ElNom`, i es rep una resposta en format JSON.

---

### ✅ Formulari HTML que crida l’endpoint `/hello`

```json
{
  "id": 1,
  "message": "Joan"
}
```

El navegador mostra la resposta crua, però si s’utilitza una eina com Postman o el navegador amb una extensió JSON, es veu formatat.

---

## 🧱 Classe `Hello.java`

És una classe senzilla amb dos camps (`id` i `message`) i mètodes `get` i `set`.

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

Quan es retorna aquest objecte des d’un endpoint, Spring Boot converteix-lo automàticament a **JSON**.

---

## ⚙️ Fitxer `application.properties`

Aquest fitxer serveix per configurar l’aplicació de forma senzilla. Per exemple:

<details>
<summary>📄 application.properties</summary>

```properties
spring.application.name=springboot-crud-intro
welcome-value=Benvingut
# server.port=8082 (es pot descomentar per canviar el port)
```

També es pot fer servir el fitxer `application.yml` en format YAML.

</details>

---

## 📌 Resum de Conceptes Clau

<details>
<summary>🧠 @RestController (explicació ampliada)</summary>

`@RestController` és una combinació de dues anotacions:

* `@Controller`: indica que és una classe que pot gestionar peticions HTTP.
* `@ResponseBody`: fa que el resultat del mètode es retorni directament com a resposta HTTP (no com una vista HTML).

Per tant, serveix per **fer APIs REST** que treballen amb dades (JSON, XML...).

</details>

<details>
<summary>🧠 @GetMapping (explicació ampliada)</summary>

És una forma ràpida d’especificar que un mètode respon a **peticions GET**. GET és el tipus de petició que fa el navegador normalment quan accedim a una URL.

```java
@GetMapping("/hola")
public String hola() {
    return "Hola món!";
}
```

Això respondrà quan entrem a `http://localhost:8080/hola`.

</details>

<details>
<summary>🧠 @RequestParam (explicació ampliada)</summary>

`@RequestParam` s’utilitza per accedir als paràmetres passats per la URL:

```java
@GetMapping("/hello")
public String hello(@RequestParam String name) {
    return "Hola " + name;
}
```

Si accedim a `/hello?name=Joan`, es mostrarà `"Hola Joan"`.

També es pot afegir un valor per defecte:

```java
@RequestParam(value = "name", defaultValue = "Món")
```

</details>

<details>
<summary>🧠 POJO (Plain Old Java Object)</summary>

Un POJO és una classe senzilla de Java amb atributs i mètodes getters/setters. En Spring, sovint s’utilitza per representar objectes que es retornen en format JSON.

</details>

---

## ✅ Conclusió

* ✔️ S’ha completat tota la pràctica, incloent les tasques extres.
* ✔️ S’ha seguit una estructura clara i modular.
* ✔️ Tots els conceptes s’han explicat detalladament per a que qualsevol persona, encara que no conegui Spring Boot, pugui entendre què fa cada part del projecte.