package ru.mattakvshi.AOPTrainProject.aspect;

//Наш компонент Аспект, который реализован как Java класс, и содержи те самые advice (советы)

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // Это аспект, то есть содержит правила о том когда должны выполнятся советы
@Component // Ну и как мы видим это компонент, то есть бин нашего спрингового контекста
@Slf4j // логирование
public class AspectComponent {
    @Around("@annotation(LogExecutionTime)") //Обернули целевойц метод в совет, нашей точкой соединения будет наша кастомная анноация @LogExecutionTime
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis(); // Замеряем время перед запуском целевого метода

        Object result = joinPoint.proceed(); // Обозначаем место процесса работы целевого метода, joinPoint экзепляр класса ProceedingJoinPoint, который содержит всю информацию о целевом методе
        // метод proceed() этого класса говорит о том что нужно запустить работу целевого метода, к которому мы привязали данный функционал

        long executeTime = System.currentTimeMillis() - startTime; // Замеряем время после завершения работы метода, и вычисляем разницу

        log.info(joinPoint.getSignature() + " executed in " + executeTime + "ms"); // Выводим информацию в логи
        return result;
    }

    //Добавим ещё один совет. По успешному выполнению метода, (Срез по) с любым модификатором доступа и возвращемым значением, из класса CompanyServiceImpl,
    // для любого метода этого класса, с любыми аогументами.
    @AfterReturning(pointcut = "execution(* ru.mattakvshi.AOPTrainProject.service.CompanyServiceImpl.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info(joinPoint.getSignature().getName() + " result = " + result.toString());
    }

}
