package com.cybersoft.uniclub06.controller;

import com.cybersoft.uniclub06.request.AuthenRequest;
import com.cybersoft.uniclub06.response.BaseResponse;
import com.cybersoft.uniclub06.service.AuthenService;
import com.cybersoft.uniclub06.utils.JwtHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.List;

@RestController
@RequestMapping("/authen")
public class AuthenController {

    @Autowired
    private AuthenService authenService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<?> authen(@RequestBody AuthenRequest authenRequest) throws JsonProcessingException {

        //Tạo key -> chỉ xài 1 lần nhớ comment lại sau khi generate xong
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
        //Biến key thành chuỗi để lưu trữ lại
//        String key = Encoders.BASE64.encode(secretKey.getEncoded());

//        boolean isSuccess = authenService.checkLogin(authenRequest);

//        System.out.println("Kiem tra: " + isSuccess);

        UsernamePasswordAuthenticationToken authenToken =
                new UsernamePasswordAuthenticationToken(authenRequest.email(), authenRequest.password());
        authenticationManager.authenticate(authenToken);

        Authentication authentication = authenticationManager.authenticate(authenToken);

        List<GrantedAuthority> listRole = (List<GrantedAuthority>) authentication.getAuthorities();

        String data = objectMapper.writeValueAsString(listRole);

//        System.out.println("Kiểm tra " + data);

        String token = jwtHelper.generateToken(data);

        BaseResponse response = new BaseResponse();
        response.setData(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
