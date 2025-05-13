# 👤 Consum RandomUser API

<p align="center">
  <img src="src/main/resources/static/img/logo.png" alt="Logo del projecte" width="150">
</p>

Aplicació Spring Boot que consumeix la [Random User API](https://randomuser.me) i mostra informació d’un usuari generat aleatòriament. Està desenvolupada amb **Thymeleaf** i estilitzada amb **Bootstrap 5**.

## 🚀 Característiques

- Obtenció d’un usuari aleatori a través d’una crida a una API externa
- Mostra dades com nom, email, ubicació, telèfon, foto, coordenades i zona horària
- Interfície clara i senzilla amb Bootstrap
- Pàgina d’error personalitzada
- Estructura reutilitzable amb fragments de Thymeleaf
- Favicon i logo personalitzats

## 📂 Estructura

```
src
├── main
│   ├── java/com/github/mgrl39/consumrandomuserapi
│   │   ├── controller/
│   │   ├── model/
│   │   ├── service/
│   │   └── ConsumRandomuserApiApplication.java
│   └── resources/
│       ├── static/img/           # Logo i favicon
│       ├── templates/
│       │   ├── fragments/        # navbar.html, footer.html, head.html
│       │   ├── user.html
│       │   ├── error.html
│       │   └── test.html
```

## 🔧 Execució

1. Clona el repositori:
   ```bash
   git clone https://github.com/mgrl39/consum-randomuser-api.git
   cd consum-randomuser-api
   ```

2. Compila i arrenca l’aplicació:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Accedeix a les següents rutes:
    - [`http://localhost:8080/user`](http://localhost:8080/user) → Mostrar un usuari aleatori
    - [`http://localhost:8080/test`](http://localhost:8080/test) → Test de funcionament

## ❤️ Agraïments

- API: [Random User Generator](https://randomuser.me)
- UI: [Bootstrap 5](https://getbootstrap.com/)

## 📄 Llicència

Aquest projecte està sota llicència **MIT**. Consulta el fitxer `LICENSE` per a més informació.
