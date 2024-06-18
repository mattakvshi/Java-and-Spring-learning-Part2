package ru.mattakvshi.repositoryTrainProject.company.employee;

public interface Worker {
    default void work() {
        throw new RuntimeException("Not implemented");
    };
}


