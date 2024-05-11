package com.example.sklep2xd.ssecurity;

import io.jsonwebtoken.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

public class JwtUtil {
    private static final String SECRET_KEY = "secret";

    public static String generateToken(String username, String role, int id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        if ("ROLE_KLIENT".equals(role)) {
            claims.put("klientId", id);
        } else if ("ROLE_PRACOWNIK".equals(role)) {
            claims.put("pracownikId", id);
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static List<GrantedAuthority> extractAuthorities(String token) {
        Claims claims = extractClaims(token);
        return Collections.singletonList(new SimpleGrantedAuthority((String) claims.get("role")));
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
        if (authentication != null && authentication.getDetails() instanceof Map) {
            Map details = (Map) authentication.getDetails();
            if (details.containsKey("klientId")) {
                return (Integer) details.get("klientId");
            }
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
