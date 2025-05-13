// Aquest és el paquet on es troba la classe - s'ubica dins del paquet "model"
// que normalment conté les classes que representen les dades de l'aplicació
package net.elpuig.springbootrestfulwebservicebasics.model;

// Definició d'un "record" de Java - una característica introduïda en Java 14
// Els records són una forma concisa de definir classes immutables per emmagatzemar dades
// Un record genera automàticament:
//   - Constructor
//   - Mètodes getters (id() i content() en aquest cas)
//   - Mètodes equals(), hashCode() i toString()
//   - Accessors finals per als camps

// El primer camp del record, un identificador únic per a cada salutació
// Tipus long per permetre un gran nombre de salutacions sense desbordament

// El segon camp del record, conté el missatge de salutació personalitzat
// Exemple: "Hello, World!" o "Hello, Marc!"
public record Greeting(long id, String content) {
    // El cos del record està buit perquè no necessitem afegir comportament addicional
    // Tota la funcionalitat bàsica (constructor, getters, equals, hashCode, toString)
    // és generada automàticament pel compilador de Java
}
