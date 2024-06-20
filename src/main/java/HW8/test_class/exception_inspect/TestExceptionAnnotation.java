package HW8.test_class.exception_inspect;

import HW8.annotation.RecoverException;
import org.springframework.stereotype.Component;

@Component
public class TestExceptionAnnotation {

    @RecoverException(noRecoverFor = IllegalArgumentException.class)
    public String returnParameter(String parameter) {
        if (parameter.equals(""))
            throw new IllegalArgumentException();
        return parameter;
    }

    @RecoverException
    public long getParameterNumber(int number) {
        if (number == 0)
            throw new IllegalArgumentException();
        return number;
    }

}
