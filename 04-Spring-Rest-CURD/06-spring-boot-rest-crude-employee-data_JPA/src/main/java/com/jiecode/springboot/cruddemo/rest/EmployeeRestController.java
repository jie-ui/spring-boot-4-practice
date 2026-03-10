package com.jiecode.springboot.cruddemo.rest;

import com.jiecode.springboot.cruddemo.entity.Employee;
import com.jiecode.springboot.cruddemo.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping
public class EmployeeRestController {
    private EmployeeService employeeService;

    private JsonMapper jsonMapper;
    //quick and ritry: inject employee dao(use constuctor injection)

    public EmployeeRestController(EmployeeService theemployeeService, JsonMapper thejsonMapper) {
      employeeService = theemployeeService;
      jsonMapper = thejsonMapper;
    }

    //expose /Employess and return a list of employee
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    //add mapping for GET /employee/{employee/{emloyeeId}
    @GetMapping("/employees/{employeeId}")
            public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);
        if(employee == null){
            throw new RuntimeException("employee not found"+employeeId);


        }
        return employee;
    }

    //Add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //also just in case they pass an id  in josn set id to 0
        //this is to force a save for new item insteas of unpdate

        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    //update exostong employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //Add Mapping for PATCH
    @PatchMapping("/employee/{employeeId})")
    public Employee updateEmployee(@RequestBody Map<String, Object> patchPayload, @PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        //throw exception if null
        if(theEmployee == null){
            throw new RuntimeException("employee not found"+employeeId);
        }
        //throw exception if request body contains id
        if(patchPayload.containsKey("id")){
                throw new RuntimeException("employee id not request body");
        }
        Employee PatchEmployee = (Employee) jsonMapper.updateValue(patchPayload, theEmployee);
        Employee dbEmployee = employeeService.save(PatchEmployee);
        return dbEmployee;
    }




    //delte
    // 删除操作
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if(employee == null){
            throw new RuntimeException("employee not found"+employeeId);

        }
        employeeService.deleteById(employeeId);
        return "success";
    }

}
