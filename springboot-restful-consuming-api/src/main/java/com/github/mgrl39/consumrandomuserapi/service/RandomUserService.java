package com.github.mgrl39.consumrandomuserapi.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.mgrl39.consumrandomuserapi.model.User;
import com.github.mgrl39.consumrandomuserapi.model.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Indica que aquesta classe és un "servei" de Spring, o sigui, s'encarrega de la lògica de negoci.
// Això permet que Spring pugui gestionar-la automàticament (per exemple, injectar-la al controlador).
@Service
public class RandomUserService {
    // RestTemplate és una eina de Spring que permet fer peticions HTTP de forma fàcil.
    // Aquí es crea una instància per utilitzar-la dins del servei.
    RestTemplate restTemplate = new RestTemplate();

    public User getRandomUser() {
        // URL de l'API que retorna un usuari aleatori en format JSON
        String url = "https://randomuser.me/api/";
        // Fem la crida GET i mapejem automàticament la resposta JSON a un objecte Java (UserResponse)
        UserResponse response = restTemplate.getForObject(url, UserResponse.class);
        // Comprovem que la resposta no sigui null i que la llista de resultats no estigui buida
        if (response != null && !response.getResults().isEmpty()) {
            // Retornem el primer usuari de la llista
            return response.getResults().getFirst();
        } else {
            // Si no hi ha resposta vàlida o la llista està buida, llencem una excepció.
            throw new RuntimeException("No s'ha pogut obtenir l'usuari des de l'API");
        }
    }
}
