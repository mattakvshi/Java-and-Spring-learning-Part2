package ru.mattakvshi.SecurityTrainProject.company.employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "pm")
public class PM extends Employee<ITRole> {

    @Column(name = "number_of_projects")
    private int numberOfProjects;

    @Column(name = "methodology")
    private String methodology;


    public PM(){

    }

    public PM(String name, int age){
        super(name, age, ITRole.PM);
        this.numberOfProjects = numberOfProjects;
        this.methodology = methodology;
    }

    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    public void setNumberOfProjects(int numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    @Override
    public void work(){
        System.out.println(this.getName() + "is managing project");
    }

    @Override
    public String toString() {
        return "\nDeveloper{" +
                "name='" + getName() + '\'' +
                "age='" + getAge() + '\'' +
                "language='" + numberOfProjects + '\'' +
                ", grade='" + methodology + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PM pm = (PM) o;
        return numberOfProjects == pm.numberOfProjects && Objects.equals(methodology, pm.methodology);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfProjects, methodology);
    }
}

