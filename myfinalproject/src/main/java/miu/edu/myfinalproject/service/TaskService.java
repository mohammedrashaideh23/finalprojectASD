package miu.edu.myfinalproject.service;

import miu.edu.myfinalproject.entity.Employee;
import miu.edu.myfinalproject.entity.Task;
import miu.edu.myfinalproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TaskService implements ApplicationRunner {

    EmployeeRepository employeeRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TaskService(EmployeeRepository employeeRepository, BCryptPasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void addDumyData(){
        employeeRepository.saveAll(
                Arrays.asList(
                        new Employee(1, "john@example.com", passwordEncoder.encode("password123"), "John Doe", 25, "50000", Arrays.asList(new Task(1, "Task 1", 1))),
                        new Employee(2,"jane@example.com" ,passwordEncoder.encode("password456") , "Jane Smith", 30, "60000", Arrays.asList(new Task(2, "Task 2", 2))),
                        new Employee(3,"bob@example.com" ,passwordEncoder.encode( "password789"), "Bob Johnson", 28, "45000", Arrays.asList(new Task(3, "Task 3", 3)))
                )
        );

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addDumyData();
    }
}
