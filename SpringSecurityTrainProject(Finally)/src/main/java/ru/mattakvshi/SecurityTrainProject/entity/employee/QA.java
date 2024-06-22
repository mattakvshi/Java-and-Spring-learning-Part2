package ru.mattakvshi.SecurityTrainProject.entity.employee;

public class QA extends Employee<ITRole> {

    public QA(String name, int age){
        super(name, age, ITRole.QA);
    }
    @Override
    public void work(){
        System.out.println(this.getName() + "is testing");
    }
}