package ru.mattakvshi.AOPTrainProject.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //Указали в качестве цели методы, то есть аннотироватся нашей кастомной аннотацией будут методы
@Retention(RetentionPolicy.RUNTIME) //И указали, что аннотация будет действовать или будет запущена в рантайме
public @interface LogExecutionTime {
}
