package com.techlab.jwtsecurity.dto;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.techlab.jwtsecurity.exception.UserApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app-jwt-expiration-milliseconds}")
	private long jwtExpirationDate;
	
	
	public String generateToken(Authentication authentication) {
		
		String username=authentication.getName();
		
		Date curentDate = new Date();
		
		Date expirateionDate = new Date(jwtExpirationDate+curentDate.getTime());
		
		String token = Jwts.builder().claims().subject(username).issuedAt(new Date(System.currentTimeMillis()))
				       .expiration(expirateionDate).and().signWith(Key())
				       .claim("role", authentication.getAuthorities()).compact();
		
		return token;
	}
	
private SecretKey Key() {
	return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
}

public boolean validateToken(String token){
    try{
      Jwts.parser().verifyWith(Key()).build().parse(token);
        return true;
    } catch (MalformedJwtException ex) {
        throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
    } catch (ExpiredJwtException ex) {
        throw new UserApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
    } catch (UnsupportedJwtException ex) {
        throw new UserApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
        throw new UserApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
    }
    catch(Exception e)
    {
      throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid Credentials");
    }
}

public String getUsername(String token){
    Claims claims = Jwts.parser().verifyWith(Key()).build().parseSignedClaims(token).getPayload();     
  
    
    String username = claims.getSubject();
    
    
    
    return username;
}

}
