package com.cybersoft.uniclub06.request;

//import lombok.Data; // extension for auto-create getter-setter

//@Data // enable lombok extension
//public class AuthenRequest {
//    private String email;
//    private String password;
//
//}


public record AuthenRequest(String email, String password) {
}
