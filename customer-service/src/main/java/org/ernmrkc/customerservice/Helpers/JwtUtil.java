package org.ernmrkc.customerservice.Helpers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final long EXP_TIME_10_DAYS = 864_000_000;
    private static final SecretKey key = KeyGenUtil.getKey();

    public static String generateToken(String username) {
        return Jwts.builder()
                .claim("sub", username)
                .claim("exp", new Date(System.currentTimeMillis() + EXP_TIME_10_DAYS))
                .signWith(key)
                .compact();
    }

    // TODO: Customize exception handling and return value
    public static String extractUsername(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return "";
    }

    // TODO: Customize exception handling
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parse(token);
            return true;
        }catch (ExpiredJwtException e){
            System.out.println("Expired Token Exception");
        }catch (Exception e){
            System.out.println("Exception");
        }
        return false;
    }

    // TODO: Customize exception handling
    public static boolean validateTokenWithUsername(String token, String username){
        try{
            Jwts.parser()
                    .requireSubject(username)
                    .verifyWith(key)
                    .build()
                    .parse(token);
            return true;
        }catch (Exception e){
            System.out.println("Exception");
        }
        return false;
    }
}
