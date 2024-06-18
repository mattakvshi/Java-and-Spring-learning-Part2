package ru.mattakvshi.hibernateTrainProject.company.employee;

public interface Worker {
    default void work() {
        throw new RuntimeException("Not implemented");
    };
}


