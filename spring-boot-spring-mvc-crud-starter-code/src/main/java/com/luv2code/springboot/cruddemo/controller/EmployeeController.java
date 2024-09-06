package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees") // "/employees is the base mapping url requests for items in this controller
public class EmployeeController {

    private final EmployeeService employeeService;

    // constructor for constructor injection, and inject the EmployeeService
    // since we only have one constructor, @Autowiring is optional
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;


    }
    // add mapping for listing employees
    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // get employees from the database
        List<Employee> employees = employeeService.findAll();

        // add data to the spring model
        theModel.addAttribute("employees", employees);

        return "list-employees"; // name of the view page we return the list to
    }
}
