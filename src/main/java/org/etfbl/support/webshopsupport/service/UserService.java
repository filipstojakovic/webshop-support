package org.etfbl.support.webshopsupport.service;

import org.etfbl.support.webshopsupport.dao.UserSupportDao;
import org.etfbl.support.webshopsupport.dto.UserSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserSupport loginUser(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        UserSupport user = UserSupportDao.selectByUsername(username);
        if (user == null) return null;

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

}
