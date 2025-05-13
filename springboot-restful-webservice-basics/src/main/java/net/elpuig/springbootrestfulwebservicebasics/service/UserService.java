package net.elpuig.springbootrestfulwebservicebasics.service;

import net.elpuig.springbootrestfulwebservicebasics.model.User;
import net.elpuig.springbootrestfulwebservicebasics.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtenir tots els usuaris
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtenir un usuari per ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public boolean deleteUser(long id) {
        return userRepository.deleteById(id);
    }
}