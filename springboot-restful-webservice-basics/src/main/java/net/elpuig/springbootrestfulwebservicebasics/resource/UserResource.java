package net.elpuig.springbootrestfulwebservicebasics.resource;

import net.elpuig.springbootrestfulwebservicebasics.controller.UserController;
import net.elpuig.springbootrestfulwebservicebasics.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.embedded.TomcatVirtualThreadsWebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/users")
public class UserResource {
    private final UserController userController;

    @Autowired
    public UserResource(UserController userController) {
        this.userController = userController;
    }

    // GET /api/v0/users
    @GetMapping
    public List<User> getAllUsers() {
        return userController.getAllUsers();
    }

    // GET /api/v0/users/{id} - Retorna un usuari per ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userController.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST /api/v0/users - Afegir un nou usuari
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userController.saveUSer(user);
    }

    // DELETE /api/v0/users/{id} - Esborrar un usuari per ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userController.deleteUserById(id);

        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not found
        }
    }
}
