package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the employees from db
        List<Employee> employees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", employees);

        return "employee-list";
    }

    @GetMapping("/addEmp")
    public String addEmployees(Model theModel) {

        // get the employees from db
        Employee emp = new Employee();

        // add to the spring model
        theModel.addAttribute("employee", emp);
        theModel.addAttribute("title", "Add Employee");

        return "edit";
    }

    @GetMapping("/editEmp")
    public String editEmployees(@RequestParam("empId") int empId, Model theModel) {

        // get the employees from db
        Employee emp = employeeService.findById(empId);

        // add to the spring model
        theModel.addAttribute("employee", emp);
        theModel.addAttribute("title", "Edit Employee");

        return "edit";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee emp){
        employeeService.save(emp);
        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("empId") int empId, Model theModel){
        employeeService.delete(empId);
        return "redirect:/employees/list";
    }
}
