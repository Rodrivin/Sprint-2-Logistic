package com.example.logisticapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Gera uma chave segura automaticamente
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Tempo de expiração do token (1 hora)
    private final long EXPIRATION_MS = 1000 * 60 * 60;

    // Gerar token a partir do username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key) // já usa a chave segura
                .compact();
    }

    // Extrair username de dentro do token
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validar se o token ainda é válido
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (username.equals(extractedUsername) && !isTokenExpired(token));
    }

    // Verifica se o token já expirou
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Pegar todos os "claims" (dados do token)
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // usa a mesma chave da assinatura
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
