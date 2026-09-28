package com.key_stone.Security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final String SECRET_KEY = "KEYSTONE_SECRET_KEY_2026_MYSQL_JAVA17_SECURITY";

	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

	private Key getSigningKey() {

		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(String email, String role) {

		return Jwts.builder().subject(email).claim("role", role).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(getSigningKey()).compact();
	}

	public String extractEmail(String token) {

		return Jwts.parser().verifyWith((javax.crypto.SecretKey) getSigningKey()).build().parseSignedClaims(token)
				.getPayload().getSubject();
	}

	public boolean isTokenValid(String token) {

		try {

			Jwts.parser().verifyWith((javax.crypto.SecretKey) getSigningKey()).build().parseSignedClaims(token);

			return true;

		} catch (Exception e) {

			return false;
		}
	}
}