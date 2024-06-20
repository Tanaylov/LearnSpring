package HW8;

import HW8.test_class.exception_inspect.TestExceptionAnnotation;
import HW8.test_class.time_inspect.TimeInspectClass;
import HW8.test_class.time_inspect.TimeInspectMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AspectRunner {

    private final TimeInspectMethod timeInspectMethod;
    private final TimeInspectClass timeInspectClass;
    private final TestExceptionAnnotation testExceptionAnnotation;

    @EventListener(ApplicationReadyEvent.class)
    public void onAppReady() {
        System.out.println(timeInspectMethod.voidTimeInspectInstance(5));
        System.out.println(timeInspectMethod.emptyMethod());
        System.out.println(timeInspectClass.fibonacciCycle(7));
        System.out.println(timeInspectClass.fibonacciStream(7));
        System.out.println("-".repeat(100));
        System.out.println(testExceptionAnnotation.returnParameter("param"));
//        System.out.println(testExceptionAnnotation.returnParameter(""));
        System.out.println("-".repeat(100));
        System.out.println(testExceptionAnnotation.getParameterNumber(5));
        System.out.println(testExceptionAnnotation.getParameterNumber(0));


    }
}
