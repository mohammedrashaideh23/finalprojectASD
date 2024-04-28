package miu.edu.myfinalproject.repository;

import miu.edu.myfinalproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
}
