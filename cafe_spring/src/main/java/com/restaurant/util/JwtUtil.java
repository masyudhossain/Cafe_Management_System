package com.restaurant.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode("413F4428472848625065536856605970337336763979244226452948404D6351"));

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // 24 hours
        long expirationTimeMillis = 1000 * 60 * 60 * 24;
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMillis))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUserName(String token) throws JwtException {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) throws JwtException {
        final String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws JwtException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) throws JwtException {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) throws JwtException {
        Date expirationDate = extractExpiration(token);
        return expirationDate.before(new Date());
    }

    private Date extractExpiration(String token) throws JwtException {
        return extractClaim(token, Claims::getExpiration);
    }
}

//package com.restaurant.util;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import jakarta.websocket.Decoder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Component
//public class JwtUtil {
//    public String generateToken (UserDetails userDetails) {
//        return generateToken (new HashMap<>(), userDetails);
//    }
//    private String generateToken (Map<String, Object> extraClaims, UserDetails userDetails) { return Jwts.builder().setClaims (extraClaims).setSubject(userDetails.getUsername()) .setIssuedAt(new Date(System.currentTimeMillis())) .setExpiration (new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
//    }
//    private Key getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode("413F4428472848625065536856605970337336763979244226452948404D6351"); return Keys.hmacShaKeyFor(keyBytes);
//    }
//    public String extractUserName (String token) {
//        return extractClaim (token, Claims::getSubject);
//    }
//    public boolean isTokenValid (String token, UserDetails userDetails) {
//        final String userName = extractUserName (token);
//        return (userName.equals(userDetails.getUsername())) && !isTokenExpired (token);
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//
//    private <T> T extractClaim (String token, Function<Claims, T> claimsResolvers) {
//        final Claims claims = extractAllClaims (token);
//        return claimsResolvers.apply(claims);
//    }
//    private Date extractExpiration (String token) {
//        return extractClaim (token, Claims::getExpiration);
//    }
//    private Claims extractAllClaims (String token) {
//        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
//    }
//
//
//
//
//    public static final String SECRET="413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
//
//    public String generateToken(String email){
//        Map<String,Object> claims= new HashMap<>();
//        return createToken(claims,email);
//    }
//
//    private String createToken(Map<String,Object> claims, String email){
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(email)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    private Key getSignKey(){
//        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//}
