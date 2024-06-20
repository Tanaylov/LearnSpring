package HW8.test_class.time_inspect;

import HW8.annotation.Timer;
import org.springframework.stereotype.Component;

@Component
@Timer
public class TimeInspectClass {

    public long fibonacci(int sequenceNumber) {
        long previous = 0;
        long current = 1;
        long result = 0;
        /*  i = 0: res = 1; pre = 1; cur = 1
            i = 1: res = 2; pre = 1; cur = 2
            i = 2: res = 3; pre

         */
        for (int i = 2; i < sequenceNumber; i++) {
            result = current + previous;
            previous = current;
            current = result;
        }
        return result;
    }


}
