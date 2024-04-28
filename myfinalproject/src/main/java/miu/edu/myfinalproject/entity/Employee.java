package miu.edu.myfinalproject.entity;

import jakarta.persistence.*;
        import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password;

    private String name ;
    private int age;
    private String Salary;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Task> tasks;

    public Employee(int id, String email, String password, String name, int age, String salary, List<Task> tasks) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        Salary = salary;
        this.tasks = tasks;
    }

    public void addTask(Task task){
        tasks.add(task);
    }
}
