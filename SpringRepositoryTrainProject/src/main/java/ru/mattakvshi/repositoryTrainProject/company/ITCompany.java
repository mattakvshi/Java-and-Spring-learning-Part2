package ru.mattakvshi.repositoryTrainProject.company;



import ru.mattakvshi.repositoryTrainProject.company.employee.Employee;
import ru.mattakvshi.repositoryTrainProject.company.employee.ITRole;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "companies")
public class ITCompany extends EmployeeManager<Employee<ITRole>> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "director_id")
    private Employee<ITRole> director;

    public ITCompany(){}

    public ITCompany(String companyName){
        super();
        this.name = companyName;
    }

    public void startWork(){
        getEmployees().forEach(worker -> {
            worker.work();
            System.out.println(worker.getName() + "is" + worker.getRole());
        });
    }

    public String getName(){
        return name;
    }

    public Long getId() {
        return id;
    }

    public Employee<ITRole> getDirector() {
        return director;
    }

    public void setDirector(Employee<ITRole> director) {
        this.director = director;
    }

    @Override
    public String toString(){
        return "ITCompany{" +
                "name='" + name + "'" +
                "}";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ITCompany itCompany = (ITCompany) o;
        return Objects.equals(name, itCompany.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
