package com.github.mgrl39.consumrandomuserapi.controller;

import com.github.mgrl39.consumrandomuserapi.model.User;
import com.github.mgrl39.consumrandomuserapi.service.RandomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired // Si dona error Could not autowire. No beans of 'RandomUserService' type found. es que no hem
    // escrit el @Service a la clase RandomUserService
    private RandomUserService randomUserService;

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }

    @GetMapping("/user")
    public String showUser(Model model) {
        try {
        User user = randomUserService.getRandomUser(); // Cridem al servei
        model.addAttribute("user", user); // Afegim l'usuari al model
        return "user"; // retornem la plantilla user.html
    } catch (Exception e) {
        return "error"; // Si falla la crida a l’API, et porta directament a error.html.
        }
    }


}
