package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        this.employeeService = theEmployeeService;
    }
    //add mapping for/list

    @GetMapping("/list")
    public String list(Model themodel) {


        // get the employee form db
        //add to the spring model
        List<Employee> theEmployees = employeeService.findAll();
        themodel.addAttribute("employees", theEmployees);

        return"list-employees";
    }
}
