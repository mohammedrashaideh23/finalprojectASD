package miu.edu.myfinalproject.controller;

import miu.edu.myfinalproject.entity.Employee;
import miu.edu.myfinalproject.entity.Task;
import miu.edu.myfinalproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployee(){
      return   employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id ){
        return employeeService.getEmployeeById(id);
    }
     @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return  employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id , @RequestBody Employee employee){
        return  employeeService.updateEmployee(id,employee);
    }
    @DeleteMapping("/{id}")
    public  void deleteEmployee(@PathVariable int id){
         employeeService.deleteEmployee(id);
    }

    @PostMapping("/addTask/{id}")
    public void addTaskToEmployeeByEmpId(@PathVariable int id, @RequestBody Task task){
        employeeService.addTaskToEmpByEmpId(id, task);
    }
//    @GetMapping("/byAgeAndTaskDescription/{age}")
//    public List<Employee> byAgeAndTask(@PathVariable int age){
//        return employeeService.byAgeAndTask(age);
//    }
//    @GetMapping("/getEmpTasks/{emp_id}")
//public List<Task> getEmployeeTasks(@PathVariable int emp_id) {
//    return employeeService.getEmployeeTasks(emp_id);
//}
    @GetMapping("/tasksByEmpId/{emp_id}")
    public List<Task> getTasksByEmpId(@PathVariable int emp_id){
        return employeeService.getTasksByEmpId(emp_id);
    }
    @GetMapping("/employeeGreater/{age}")
    public List<Employee> findEmployeeGreater(@PathVariable int age){
        return employeeService.findEmployeeGreater(age);
    }
    @GetMapping("/employeeByName/{name}")
    public List<Employee> findEmployeeByName(@PathVariable String name){
        return employeeService.findEmployeeByName(name);
    }
}
