package miu.edu.myfinalproject.repository;

import miu.edu.myfinalproject.entity.Employee;
import miu.edu.myfinalproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT t FROM Task t JOIN t.employee e WHERE e.id = ?1")
    List<Task> getTaskByEmpId(int emp_Id);

    public List<Employee> findEmployeeByAgeGreaterThanEqual(int age);

    public Employee getEmployeeByEmail(String email);

    List<Employee> findByName(String name);


//    @Query("SELECT et.task FROM employee_tasks et WHERE et.employee.id = :employeeId")
//    List<Task> findTasksByEmployeeId(@Param("employeeId") int employeeId);

//    Employee byAgeAndTask(int age, String description){
//
//    };


//        @Query(value = "select e.* FROM employee e JOIN employee_tasks et ON e.id = et.tasks_id JOIN task t ON t.id = e.id WHERE e.age =?1 ", nativeQuery = true)
//    public List<Employee> byAgeAndTask(int age);
}
