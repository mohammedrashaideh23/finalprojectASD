package miu.edu.myfinalproject.service;

import miu.edu.myfinalproject.entity.Employee;
import miu.edu.myfinalproject.entity.Task;
import miu.edu.myfinalproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> findAll() {
      return   employeeRepository.findAll();

    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
         return employeeRepository.save(employee);
    }

    public Employee updateEmployee(int id, Employee employee) {
        Employee oldEmployee=employeeRepository.findById(id).orElse(null);
        oldEmployee.setAge(employee.getAge());
        oldEmployee.setName((employee.getName()));
        oldEmployee.setSalary(employee.getSalary());
        oldEmployee.setTasks(employee.getTasks());
        return oldEmployee;
    }

    public void deleteEmployee(int id) {
        employeeRepository.delete(getEmployeeById(id));
    }

    public void addTaskToEmpByEmpId(int id, Task task) {
        Employee existingEmp = getEmployeeById(id);
        existingEmp.addTask(task);
        employeeRepository.save(existingEmp);
    }

    public List<Task> getTasksByEmpId(int emp_Id) {
        return employeeRepository.getTaskByEmpId(emp_Id);
    }

    public List<Employee> findEmployeeGreater(int age) {
        return employeeRepository.findEmployeeByAgeGreaterThanEqual(age);
    }

    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }

//    public List<Task> getEmployeeTasks(int emp_Id) {
//        return employeeRepository.findEmployeeTasks(emp_Id);
//    }

//    public List<Employee> byAgeAndTask(int age) {
//        return employeeRepository.byAgeAndTask(age);
//    }
}
