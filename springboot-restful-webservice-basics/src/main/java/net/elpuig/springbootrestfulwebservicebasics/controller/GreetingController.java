// Aquest és el paquet on es troba la classe - els paquets són com carpetes que organitzen les classes
package net.elpuig.springbootrestfulwebservicebasics.controller;

// Importacions necessàries per al funcionament del codi
// Importa la classe model Greeting que defineix l'estructura de dades que retornarà l'API
import net.elpuig.springbootrestfulwebservicebasics.model.Greeting;
// Anotació per gestionar peticions HTTP GET
import org.springframework.web.bind.annotation.GetMapping;
// Anotació per mapejar URLs a aquesta classe controladora
import org.springframework.web.bind.annotation.RequestMapping;
// Anotació per capturar paràmetres de consulta de la URL
import org.springframework.web.bind.annotation.RequestParam;
// Anotació que identifica aquesta classe com a controlador REST
import org.springframework.web.bind.annotation.RestController;

// Classe per crear comptadors segurs en entorns multifil (thread-safe)
import java.util.concurrent.atomic.AtomicLong;

// Marca aquesta classe com un controlador REST, que combina @Controller i @ResponseBody
// Això significa que els mètodes retornaran dades (no vistes) i seran convertides automàticament a JSON
@RestController

// Defineix la ruta base per a tots els endpoints d'aquest controlador
// Utilitza una constant per a millor mantenibilitat i reutilització
@RequestMapping(GreetingController.GREETING_CONTROLLER)
public class GreetingController {

    // Defineix una constant amb la ruta base "/greeting"
    // Utilitzar constants en lloc de cadenes literals millora la mantenibilitat del codi
    public static final String GREETING_CONTROLLER = "/greeting";

    // Plantilla pel missatge de salutació
    // %s és un marcador de posició que serà substituït pel nom proporcionat
    private static final String template = "Hello, %s!";

    // Comptador que generarà un ID únic per a cada salutació
    // AtomicLong garanteix la integritat en entorns concurrents (multiusuari)
    private final AtomicLong counter = new AtomicLong();

    // @GetMapping sense paràmetres significa que aquest mètode respondrà a GET /greeting
    // Com que ja hem definit la ruta a nivell de classe amb @RequestMapping, no cal especificar-la aquí
    @GetMapping

    // Aquest mètode gestiona les peticions i retorna un objecte Greeting
    // @RequestParam busca un paràmetre anomenat "name" a la URL (exemple: /greeting?name=Pere)
    // defaultValue="World" significa que si no es proporciona el paràmetre, s'utilitzarà "World"
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        // Crea i retorna un nou objecte Greeting amb:
        // 1. Un ID únic generat incrementant el comptador (counter.incrementAndGet())
        // 2. Un missatge formatat substituint %s pel nom rebut (String.format(template, name))
        // Spring convertirà automàticament aquest objecte a JSON gràcies a Jackson
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    // http://localhost:8080/greeting?name=Mouad
}
