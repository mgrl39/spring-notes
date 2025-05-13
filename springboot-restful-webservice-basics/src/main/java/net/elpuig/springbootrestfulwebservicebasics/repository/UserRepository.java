package net.elpuig.springbootrestfulwebservicebasics.repository;

import net.elpuig.springbootrestfulwebservicebasics.model.User;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    // Llista en memòria per emmagatzemar usuaris
    private final List<User> users = new ArrayList<>();

    // Constructor per inicializar amb dades d'exemple
    public UserRepository() {
        // Afegim alguns usuaris d'exemple
        users.add(new User(1L, "user1@exemple.com", "User One", "password1"));
        users.add(new User(2L, "user2@exemple.com", "User Two", "password2"));
        users.add(new User(3L, "user3@exemple.com", "User Three", "password3"));
    }

    // Obtenir tots els usuaris
    public List<User> findAll() {
        return users;
    }

    // Obtenir un usuari per id
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.id() == id)
                .findFirst();
    }

    // Afegir un nou usuari;
    public User save(User user) {
        Optional<User> existingUser = findById(user.id());
        if (existingUser.isPresent()) {
            // Si existeix, l'eliminem per després afegir-ne la versió actualitzada
            users.removeIf(u -> u.equals(user));
        }
        users.add(user);
        return user;
    }

    public boolean deleteById(Long id) {
        int sizeBefore = users.size();
        users.removeIf(user -> user.id() == id);
        return sizeBefore == users.size();
    }

}
