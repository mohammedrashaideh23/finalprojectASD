package miu.edu.myfinalproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String description;
    private int priority;
    @ManyToOne
    @JsonIgnore
    private Employee employee;

    public Task(int id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }
}
