package com.form.login.demo.Service;

import com.form.login.demo.Entiry.UserInfo;
import com.form.login.demo.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder encoder;


    @Autowired
    private UserInfoRepository repository;

    public String newUserSignUp(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User sucessfully signed up!!";
    }
}
