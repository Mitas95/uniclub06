package com.cybersoft.uniclub06.controller;


import com.cybersoft.uniclub06.request.AddProductRequest;
import com.cybersoft.uniclub06.response.BaseResponse;
import com.cybersoft.uniclub06.service.FileService;
import com.cybersoft.uniclub06.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping
    public ResponseEntity<?> addProduct(AddProductRequest request) {

        productService.addProduct(request);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Thành công !");

        return new ResponseEntity<>("Success add Product", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getProduct() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("Thành công !!");
        baseResponse.setData(productService.getProduct());

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
