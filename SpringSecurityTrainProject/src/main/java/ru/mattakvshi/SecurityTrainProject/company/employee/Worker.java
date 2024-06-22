package ru.mattakvshi.SecurityTrainProject.company.employee;

public interface Worker {
    default void work() {
        throw new RuntimeException("Not implemented");
    };
}


