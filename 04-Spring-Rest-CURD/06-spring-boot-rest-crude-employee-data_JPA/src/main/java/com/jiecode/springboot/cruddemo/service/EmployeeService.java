package com.jiecode.springboot.cruddemo.service;

import com.jiecode.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();//Get
    Employee findById(int theid);//get single one by id

    Employee save(Employee employee); //post update
    Employee deleteById(int theid); //delete by id
    //void delete(Employee employee); //delete all

}
