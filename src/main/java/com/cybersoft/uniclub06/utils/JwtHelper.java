package com.cybersoft.uniclub06.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${jwts.key}")
    private String strKey;
    private int expiredTime = 8 * 60 * 60 * 1000;
    //LocalDateTime => hỏi GPT
    public String generateToken(String data) {
        // Biến key kiểu string đã lưu trữ trước đó thành SecretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));

        // Time expirating calculation and function
        Date currentDate = new Date();
        long miliSecondFuture = currentDate.getTime() + expiredTime;
        Date dateFuture = new Date(miliSecondFuture);

        String  token = Jwts.builder().signWith(secretKey).subject(data).compact();
        return token;
    }

    public String decodeToken (String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(strKey));
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
