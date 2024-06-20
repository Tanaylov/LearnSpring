package HW8.test_class.time_inspect;

import HW8.annotation.Timer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Component
@Timer
public class TimeInspectClass {

    public long fibonacciCycle(int sequenceNumber) {
        long previous = 0;
        long current = 1;
        long result = 0;

        for (int i = 2; i < sequenceNumber; i++) {
            result = current + previous;
            previous = current;
            current = result;
        }
        return result;
    }

    public long fibonacciStream(int sequenceNumber) {
        return Stream.iterate(new long[]{0, 1}, array -> new long[]{array[1], array[0] + array[1]})
                .skip(sequenceNumber - 1).limit(1)
                .map(y -> y[0])
                .findFirst().get();
    }


}
