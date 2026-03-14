package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return"employees/list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        //create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int thId, Model theModel) {
        //get the employee form the service
        Employee theEmployee = employeeService.findById(thId);
        //set employee in the model to prepopulate the form
         theModel.addAttribute("employee", theEmployee);

        //send over to  form


        return "employees/employee-form";

    }
    @PostMapping("/save")
    public String save(@ModelAttribute("employees") Employee theEmployee) {
        //save the employee
        employeeService.save(theEmployee);
        //use a redireect to rpevent dubplicate submissions
        return "redirect:/employees/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int thId) {
        //delete the employee
        employeeService.deleteById(thId);
        //redirect to the /emplyees/list
        return "redirect:/employees/list";
    }

}
