package com.example.logisticapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration indica que esta classe contém definições de beans.
// Implementa WebMvcConfigurer para personalizar a configuração do Spring MVC.
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite CORS para todos os endpoints da sua API.
        registry.addMapping("/**") // Aplica a todos os caminhos
                .allowedOrigins(
                        "http://localhost:8081",
                        "http://localhost:19000",
                        "http://localhost:19001",
                        "exp://localhost:19000",
                        "exp://192.168.15.46:19000", // SEU IP para Expo Go
                        "http://192.168.15.46:8081", // SEU IP para web no emulador/físico
                        "http://10.0.2.2:8081",
                        "http://10.0.2.2:8080", // Para emulador Android
                        "http://192.168.15.46:8080" // SEU IP para o backend acessado diretamente
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true) // Permite credenciais (cookies, headers de autorização)
                .maxAge(3600); // Tempo máximo em segundos para cache da resposta de pré-verificação
    }
}
