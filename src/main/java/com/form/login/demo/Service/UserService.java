package com.form.login.demo.Service;

import com.form.login.demo.Entiry.UserInfo;
import com.form.login.demo.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    private UserInfoRepository repository;

    public ResponseEntity<String> newUserSignUp(UserInfo userInfo) {
        UserInfo byEmail = repository.findByEmail(userInfo.getEmail());

        if(repository.findByEmail(userInfo.getEmail())==null) {
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            repository.save(userInfo);
            return ResponseEntity.ok().body("User sucessfully signed up!!");
        }
        else {
//            System.out.println(byEmail.getUsername());
            return ResponseEntity.badRequest().body("User is already registered. Chose another email address!!");
        }
    }


}
