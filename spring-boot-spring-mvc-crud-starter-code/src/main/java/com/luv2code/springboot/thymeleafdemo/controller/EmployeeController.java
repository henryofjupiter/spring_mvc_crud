package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

        return "employees/list-employees"; // name of the view page we return the list to
    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        // create model attribute to the bind form data
        Employee theEmployee = new Employee();

        // our thymeleaf template will access this data for binding form data
        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    // add mapping for saving an employee
    @PostMapping("/save")
    // form data being passed in from the html data binding th:object="${employee}"
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

        // save the employee
        employeeService.save(theEmployee);

        // user a redirect to prevent duplicate submissions
        return  "redirect:/employees/list";

    }
}
