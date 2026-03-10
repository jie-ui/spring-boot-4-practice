package com.jiecode.springboot.cruddemo.service;

import com.jiecode.springboot.cruddemo.dao.EmployeeDAO;
import com.jiecode.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //inject Employee DAO
    private EmployeeDAO employeeDAO;
    @Autowired

    public EmployeeServiceImpl(EmployeeDAO theemployeeDAO) {
        employeeDAO = theemployeeDAO;
    }



    @Override
    public List<Employee> findAll(){
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int theid) {
        return employeeDAO.findById(theid);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override

    public Employee deleteById(int theid) {
     employeeDAO.deleteById(theid);
        return null;
    }
}
