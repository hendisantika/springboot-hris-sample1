package com.hendisantika.hris.springboothrissample1.service;

import com.hendisantika.hris.springboothrissample1.dto.UserDTO;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:38
 */
public interface UserService {
    UserDTO getUser(String username, String Password);
}
