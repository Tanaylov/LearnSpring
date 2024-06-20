package HW8;

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

    @EventListener(ApplicationReadyEvent.class)
    public void onAppReady() {
        System.out.println(timeInspectMethod.voidTimeInspectInstance(5));
        System.out.println(timeInspectClass.fibonacci(227));
    }
}
