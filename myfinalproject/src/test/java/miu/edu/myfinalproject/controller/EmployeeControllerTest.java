package miu.edu.myfinalproject.controller;

import miu.edu.myfinalproject.controller.EmployeeController;
import miu.edu.myfinalproject.entity.Employee;
import miu.edu.myfinalproject.entity.Task;
import miu.edu.myfinalproject.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testGetAllEmployee() throws Exception {
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setName("John");
        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Jane");
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeService.findAll()).thenReturn(employees);

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"));

        verify(employeeService, times(1)).findAll();
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        verify(employeeService, times(1)).getEmployeeById(1);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("John");

        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"));

        verify(employeeService, times(1)).createEmployee(any(Employee.class));
        verifyNoMoreInteractions(employeeService);
    }
}
