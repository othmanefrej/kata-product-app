<!-- @format -->

### **1. Structure du Projet PRODUCT-APP_TEST**

Une structure typique pour un projet Spring Boot bien organisé est la suivante :

``` 
src/
├── main/
   ├── java/
   │   └── ma/kata/product_app
   │       ├── config/           # Configuration globale
   │       ├── controller/       # Gestion des requêtes HTTP (API REST)
   │       ├── service/          # Logique métier
   │       ├── repository/       # Couche d'accès aux données (DAO)
   │       ├── model/            # Entités et modèles de données
   │       ├── dto/              # Objets de transfert de données (DTO)
   │       ├── exception/        # Gestion des exceptions
   │       ├── utils/            # Classes utilitaires
   │       └── security/         # Gestion de la sécurité
   └── resources/
       ├── application.properties # Configuration principale
```

**Description des répertoires :**

- **config/** : Gestion des configurations spécifiques comme la sécurité ou le CORS.
- **controller/** : Classes REST contrôleurs qui gèrent les points d'entrée des requêtes HTTP.
- **service/** : Contient la logique métier.
- **repository/** : Interfaces pour interagir avec la base de données (ex. `JpaRepository`).
- **model/** : Représentation des données du domaine, mappées à la base de données (entités).
- **dto/** : Objets simplifiés pour échanger les données entre les couches.
- **exception/** : Gestion des exceptions personnalisées et centralisées.
- **security/** : Configuration de l'authentification et de l'autorisation (JWT, filtres, etc.).
- **utils/** : Classes utilitaires réutilisables.

---

### **2. Documentation API avec Swagger**

Le projet intègre **Swagger UI** pour faciliter la visualisation et le test des endpoints REST de l'application.

- Une fois l'application démarrée, accédez à la documentation interactive via l'URL suivante :

🔗 [http://localhost:8082/api/swagger-ui/index.html](http://localhost:8082/api/swagger-ui/index.html)

> Assurez-vous que le port `8082` est bien utilisé dans votre `application.properties`.

---