package net.elpuig.springbootrestfulwebservicebasics.controller;

import net.elpuig.springbootrestfulwebservicebasics.model.User;
import net.elpuig.springbootrestfulwebservicebasics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Obtenir tots els usuaris
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Obtenir un usuari per un ID
    public Optional<User> getUserById(Long id) {
        return userService.getUserById(id);
    }

    // Afegir o actualitzar un usuari
    public User saveUSer(User user) {
        return userService.saveUser(user);
    }

    // Eliminar un usuari
    public boolean deleteUserById(Long id) {
        return userService.deleteUser(id);
    }
}
