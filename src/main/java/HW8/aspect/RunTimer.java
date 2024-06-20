package HW8.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RunTimer {

    @Pointcut(value = "@annotation(HW8.annotation.Timer)")
    public void annotatedMethod() {}
    @Pointcut(value = "within(@HW8.annotation.Timer *)")
    public void annotatedClass() {}

    @Around(value = "annotatedMethod() || annotatedClass()")
    public Object timeInspection(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            log.info("Runtime method({} in Class: {} ) speed: {}",pjp.getSignature(),
                    pjp.getTarget().getClass(),
                    System.currentTimeMillis() - start);
        }
    }


}
