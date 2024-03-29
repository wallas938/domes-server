package fr.greta.domes_server.services;

import fr.greta.domes_server.entities.DomesUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        try {
            return extractClaim(token, Claims::getSubject);

        } catch (Exception e) {
            return null;
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        } catch (Exception e) {
            return null;
        }
    }

    public String generateToken(DomesUser domesUser, String issuer) {
        return generateToken(new HashMap<>(), domesUser, issuer);
    }

    public String generateRefreshToken(DomesUser domesUser, String issuer) {
        return generateRefreshToken(new HashMap<>(), domesUser, issuer);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            DomesUser domesUser,
            String issuer
    ) {
        extraClaims.put("authorities", domesUser.getAuthorities());
        return "Bearer " + Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(domesUser.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) // 1 jour
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .setIssuer(issuer)
                .compact();
    }

    public String generateRefreshToken(
            Map<String, Object> extraClaims,
            DomesUser domesUser,
            String issuer
    ) {
        extraClaims.put("authorities", domesUser.getAuthorities());
        return "Bearer " + Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(domesUser.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7))) // one week
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .setIssuer(issuer)
                .compact();
    }

    public boolean isTokenValid(String token, DomesUser domesUser) {
        final String username = extractUsername(token);
        return (username.equals(domesUser.getEmail())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
