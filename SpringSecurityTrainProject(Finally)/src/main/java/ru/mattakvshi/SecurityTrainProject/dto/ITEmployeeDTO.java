package ru.mattakvshi.SecurityTrainProject.dto;

import lombok.Data;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Employee;
import ru.mattakvshi.SecurityTrainProject.entity.employee.ITRole;



@Data
public class ITEmployeeDTO {
    private Long id;
    private String name;
    private int age;
    private ITRole role;

    public static ITEmployeeDTO from(Employee<ITRole> employee) {
        ITEmployeeDTO dto = new ITEmployeeDTO();
        dto.setAge(employee.getAge());
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setRole(employee.getRole());
        return dto;
    }

    public Employee<ITRole> toEmployee(){
        Employee<ITRole> employee = new Employee<>();
        employee.setId(this.id);
        employee.setAge(this.age);
        employee.setName(this.name);
        employee.setRole(this.role);
        return employee;
    }


}
