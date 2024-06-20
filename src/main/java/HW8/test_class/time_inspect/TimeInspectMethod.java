package HW8.test_class.time_inspect;

import HW8.annotation.Timer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TimeInspectMethod {

    @Timer
    public List<TimeInspectMethod> voidTimeInspectInstance(int count) {
        List<TimeInspectMethod> timeInspects = new ArrayList<>(count + 10);
        for (int i = 0; i++ < count; timeInspects.add(new TimeInspectMethod()));
        return timeInspects;
    }

    public String emptyMethod() {
        return "do nothing";
    }

}
