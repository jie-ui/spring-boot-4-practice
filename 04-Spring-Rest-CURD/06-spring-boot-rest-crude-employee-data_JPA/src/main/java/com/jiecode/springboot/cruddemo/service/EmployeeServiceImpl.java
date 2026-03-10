package com.jiecode.springboot.cruddemo.service;

import com.jiecode.springboot.cruddemo.dao.EmployeeRepository;
import com.jiecode.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //inject Employee DAO
    private EmployeeRepository employeeRepository;
    @Autowired

    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }



    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theid) {

        //Java Optional
        /*
        Optional 用来表示一个值可能是存在的，也可能是缺失的。当方法的返回值可能为空时，
        可以使用 Optional 包装这个值，而不是返回 null。
        这提醒调用者需要显式地处理这种空值的情况，而不是默默地忽视它
         */

        Optional<Employee> result = employeeRepository.findById(theid);
        Employee theEmployee = null;
        if(result.isPresent()){
            theEmployee = result.get();
        }
        else {
            //we dind not find the employee
            throw new RuntimeException("did not find employee with id " + theid);
        }

        return theEmployee;
    }


    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override

    public Employee deleteById(int theid) {
     employeeRepository.deleteById(theid);
        return null;
    }
}
