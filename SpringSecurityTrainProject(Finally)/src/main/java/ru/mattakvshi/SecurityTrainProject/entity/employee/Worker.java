package ru.mattakvshi.SecurityTrainProject.entity.employee;

public interface Worker {
    default void work() {
        throw new RuntimeException("Not implemented");
    };
}


