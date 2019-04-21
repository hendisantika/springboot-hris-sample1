package com.hendisantika.hris.springboothrissample1.service;

import com.hendisantika.hris.springboothrissample1.dto.UserDTO;
import com.hendisantika.hris.springboothrissample1.repository.MiscDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MiscDao m;

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUser(String userName, String password) {
        UserDTO p = m.getUser(userName, password);
        return p;
    }

}