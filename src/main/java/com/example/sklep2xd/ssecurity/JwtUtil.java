package com.example.sklep2xd.ssecurity;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY = "secret";

    public static String generateToken(String username, int klientId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("klientId", klientId);
        return Jwts.builder()
                .setClaims(claims) // Set the custom claims
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    public static int extractKlientId(String token) {
        Claims claims = extractClaims(token);
        return Integer.parseInt(claims.get("klientId").toString());
    }
    public static Integer getCurrentAuthenticatedKlientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            String token = (String) authentication.getPrincipal();
            return extractKlientId(token); // Assuming the token itself is set as the principal
        }
        return null;
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.err.println("Invalid JWT signature - " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.err.println("Invalid JWT token - " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.err.println("Expired JWT token - " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.err.println("Unsupported JWT token - " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println("JWT claims string is empty - " + ex.getMessage());
        }
        return false;
    }
}
