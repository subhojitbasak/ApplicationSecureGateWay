package com.form.login.demo.Controller;

import com.form.login.demo.Entiry.UserInfo;
import com.form.login.demo.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SecuredController {
    @Autowired
    private UserService service;
    @Autowired
    private RestTemplate restTemplate;

    //No authentication for below endpoint
//    @GetMapping("/get/welcome")
//    public String welcomeGet(){
//
//        return "Hello world GET!!";
//    }


    @PostMapping("/get/welcome")
    public String sendToEds(@RequestBody UserInfo userInfo){
        String url = "http://localhost:8081/sendfromgateway";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<String> list = new ArrayList<>();
        list.add(userInfo.getEmail());
        list.add(userInfo.getUsername());
        list.add(userInfo.getRoles());
        Map<String, String> map = new HashMap<>();
        map.put("username",userInfo.getUsername());
        map.put("email",userInfo.getEmail());
        map.put("roles",userInfo.getRoles());

//        HttpEntity<HashMap> requestEntity = new HttpEntity<HashMap>(map, headers);
        restTemplate.postForObject(url, map, HashMap.class);
        return "Send to EDS";

    }


    //No authentication for below endpoint , use this to register the user
    @PostMapping("/login/newUser")
    public ResponseEntity<String> newUserSignUp(@RequestBody @Valid UserInfo userInfo){

        return service.newUserSignUp(userInfo);
    }

    //Only Admin can access this endpoint
    @GetMapping("/get/secured/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String securedAdmin(){
        return "Hello ADMIN!!";
    }
    //Only User roles can access this endpoint
    @GetMapping("/get/secured/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String securedUser(){
        return "Hello User!!";
    }


}
