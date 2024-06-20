package HW8.aspect;

import HW8.annotation.RecoverException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
public class ExceptionAspect {

    @Pointcut(value = "@annotation(HW8.annotation.RecoverException)")
    public void annotatedMethod() {}

    @Around(value = "annotatedMethod()")
    public Object exceptionRecovery(ProceedingJoinPoint pjp) {
        RecoverException annotation = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(RecoverException.class);
        List<Class<? extends RuntimeException>> exceptions = List.of(annotation.noRecoverFor());
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            if (exceptions.contains(e.getClass()))
                throw new RuntimeException("Exception is in annotation list");
            String returnTypeName = ((MethodSignature) pjp.getSignature()).getReturnType().getName();
            if (returnTypeName.contains("."))
                return null;
            return switch (returnTypeName) {
                case "char" -> ' ';
                case "boolean" -> false;
                default -> 0;
            };
        }
    }


}
