package com.cybersoft.uniclub06.request;

//import lombok.Data; // extension for auto-create getter-setter

//@Data // enable lombok extension
//public class AuthenRequest {
//    private String email;
//    private String password;
//
//}


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenRequest(
        //Regex - tạo rule cho validation
        @NotNull(message = "Email không được phép null")
        @NotEmpty(message = "Email không được phép rỗng")
        @Email(message = "Không phải định dạng email")
        String email,
        @NotNull(message = "password không được phép null")
        @NotEmpty(message = "password không được phép rỗng")
        String password
) {
}
