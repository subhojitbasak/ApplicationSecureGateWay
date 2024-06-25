package com.form.login.demo.Controller;

import com.form.login.demo.Entiry.UserInfo;
import com.form.login.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {
    @Autowired
    private UserService service;

    //No authentication for below endpoint
    @GetMapping("/get/welcome")
    public String welcomeGet(){
        return "Hello world GET!!";
    }
    //No authentication for below endpoint , use this to register the user
    @PostMapping("/login/newUser")
    public String newUserSignUp(@RequestBody UserInfo userInfo){
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

    //logout feature


}
