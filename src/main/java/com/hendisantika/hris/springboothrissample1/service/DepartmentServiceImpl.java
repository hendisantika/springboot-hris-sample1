package com.hendisantika.hris.springboothrissample1.service;

import com.hendisantika.hris.springboothrissample1.dto.DepartmentDTO;
import com.hendisantika.hris.springboothrissample1.model.Department;
import com.hendisantika.hris.springboothrissample1.repository.DepartmentRepository;
import com.hendisantika.hris.springboothrissample1.repository.MiscDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-hris-sample1
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-04-22
 * Time: 04:35
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private MiscDao m;

    @Autowired
    private DepartmentRepository d;

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentDTO> getDepartments() {
        return m.getAllDepartments();
    }

    @Override
    @Transactional(readOnly = true)
    public Department getOne(Long ID) {
        return d.findById(ID).get();
    }

}