package com.form.login.demo.Repository;

import com.form.login.demo.Entiry.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByUsername(String username);
    Optional<UserInfo> findByEmail(String emailId);
}
