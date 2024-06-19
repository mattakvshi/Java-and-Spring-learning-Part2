package ru.mattakvshi.AOPTrainProject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mattakvshi.AOPTrainProject.company.employee.Developer;
import ru.mattakvshi.AOPTrainProject.company.employee.Employee;
import ru.mattakvshi.AOPTrainProject.company.employee.ITRole;
import ru.mattakvshi.AOPTrainProject.company.employee.PM;
import ru.mattakvshi.AOPTrainProject.dto.CompanyDTO;
import ru.mattakvshi.AOPTrainProject.dto.ITEmployeeDTO;
import ru.mattakvshi.AOPTrainProject.service.CompanyService;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public Long createCompany(@RequestBody CompanyDTO companyDTO) {
        return companyService.createCompany(companyDTO.toCompany());
    }

    @GetMapping("/{id}")
    public CompanyDTO company(@PathVariable long id){
        log.info("get company info");
        return CompanyDTO.from(companyService.getCompany(id));
    }

    @PostMapping("/{id}/employers/developers")
    public ResponseEntity addEmployer(@RequestBody Developer developer, @PathVariable(name = "id") long company_id) {
        log.info("add developer");
        companyService.addDeveloper(developer, company_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/employers/PMs")
    public ResponseEntity addEmployer(@RequestBody PM pm, @PathVariable(name = "id") long company_id) {
        log.info("add PM");
        companyService.addPM(pm, company_id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employers/{index}")
    public ResponseEntity<ITEmployeeDTO> getEmployerByIndex(@PathVariable long index) {
        log.info("get employer by index = " + index);
        try {
            Employee<ITRole> employee = companyService.getEmployerByIndex(index);
            if (employee != null) {
                ITEmployeeDTO employeeDTO = ITEmployeeDTO.from(employee);
                return ResponseEntity.ok(employeeDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/employers/role/find")
    public ResponseEntity<List<ITEmployeeDTO>> getEmployerByRole(
            @RequestParam ( name = "role") ITRole role,
            @PathVariable(name = "id") int company_id){
        log.info("get employer by role = " + role);

        List<ITEmployeeDTO> result = companyService.getEmployerByRole(role, company_id)
                .stream()
                .map(ITEmployeeDTO::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/employers/age/find")
    public ResponseEntity<List<ITEmployeeDTO>> getEmployerByAge(
            @RequestParam ( name = "age") int age,
            @PathVariable(name = "id") long company_id){
        log.info("get employer by age = " + age);

        List<ITEmployeeDTO> result = companyService.getEmployerByAge(age, company_id)
                .stream()
                .map(ITEmployeeDTO::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

}
