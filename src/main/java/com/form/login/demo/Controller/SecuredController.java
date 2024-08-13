package com.form.login.demo.Controller;

import com.form.login.demo.Config.UserInfoUserDetailService;
import com.form.login.demo.Entiry.UserInfo;
import com.form.login.demo.Service.JwtService;
import com.form.login.demo.Service.UserService;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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


    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoUserDetailService userInfoUserDetailService;
    @PostMapping("/fromEDS")
    public ResponseEntity<String>  requestFromEds(@RequestBody String bearerToken){
        HttpHeaders header = new HttpHeaders();
        try {
            String username = jwtService.extractUsername(bearerToken);
            Date expiration = jwtService.extractExpiration(bearerToken);
            UserDetails userDetails = userInfoUserDetailService.loadUserByUsername(username);


            System.out.println("Bearer: " + bearerToken);
            String user = jwtService.extractUsername(bearerToken);
            System.out.println("User: " + user);

            header.set("username", username);
            Boolean b = jwtService.validateToken(bearerToken, userDetails);
            header.set("validateToken", String.valueOf(b));
            return new ResponseEntity<String>("Hello World", header, HttpStatus.ACCEPTED);
        }catch (JwtException ex){

            header.set("error", "JWT error: "+ex.getMessage());
            header.set("validateToken","false");
            return new ResponseEntity<>("JWT Error", header, HttpStatus.BAD_REQUEST);
        }catch(UsernameNotFoundException ex){
            header.set("error", "User not found: " +ex.getMessage());
            header.set("validateToken","false");
            return new ResponseEntity<>("User Not Found", header, HttpStatus.UNAUTHORIZED);
        }catch (Exception ex) {
            // General error handling
            header.set("error", "Unknown error: " + ex.getMessage());
            return new ResponseEntity<>("An error occurred", header, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
