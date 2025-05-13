# Spring Notes Repository

Benvinguts al repositori **Spring Notes**. Aquest repositori està dissenyat com una guia pràctica per aprendre i aprofundir en el framework **Spring**, incloent-hi exemples clars, tasques pràctiques i conceptes clau per desenvolupar aplicacions modernes.

## Descripció

Aquest projecte ofereix una introducció pas a pas a **Spring Boot** i altres mòduls de Spring Framework, amb un enfocament en:

- Desenvolupament d'aplicacions modernes (Microserveis, Programació reactiva, Cloud, Web Apps).
- Implementació d'APIs RESTful i serveis web.
- Persistència de dades amb JPA i bases de dades relacionals o NoSQL.
- Integració d'eines com **Thymeleaf** i **Bootstrap** per millorar el desenvolupament web.

Aquest repositori està pensat per a estudiants, desenvolupadors i qualsevol persona interessada a aprendre Spring de manera estructurada i pràctica.

---

## Índex de continguts

### **Spring Boot 2025**

#### **[SESSIÓ 1. Introducció](/introduccio)**

- Què és Spring i què podem fer?
    - Introducció al framework Spring
    - Alternatives al Spring
    - Exemples d'APIs REST i operacions CRUD
- Tasques extres
    - Crear un endpoint principal
    - Afegir un `index.html` per a un missatge de benvinguda
    - Crear un formulari per enviar dades a un endpoint

#### **[SESSIÓ 2. RestController Vs Controller](/springboot-restcontroller-vs-controller)**

- Comparació entre `@RestController` i `@Controller`
- Exemples amb Thymeleaf i manipulació de dades
- Introducció a l'ús de `@Value`, `@Service`, i constants
- Tasques extres amb formularis i llistes dinàmiques

#### **SESSIÓ 3. Consuming RestFul + Serving Web Content + Bootstrap**

- Consumir dades d'una API REST (exemple amb Chuck Norris API)
- Estructura de capes: `RestController`, `Controller`, `Service`, `Repository`
- Tasques extres amb `bootstrap` i personalització de vistes

#### **SESSIÓ 4. RESTFul Web Service**

- Creació d'un servei RESTFul:
    - Resposta amb JSON
    - Exemples amb endpoints GET, POST, DELETE
    - Ús de `@PostMapping` i `@DeleteMapping`
- Introducció a eines com POSTMAN

#### **SESSIÓ 5. RESTFul Web Service amb persistència de dades (JPA + postgresql, mongodb o mysql)**

- Preparació de la base de dades
- Creació de classes `@Entity` i DTO
- Implementació de les següents operacions:
    - GET, POST, DELETE, PUT, PATCH
    - Exemples amb `JpaRepository`
- Configuració del projecte per accedir a bases de dades
- Explicació del PATCH amb Spring

---

## Llicència

Aquest projecte està sota la llicència MIT. Consulta el fitxer [LICENSE](LICENSE) per més informació.