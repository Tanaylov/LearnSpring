package HW5_6.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties
public class BookProperties {

    private final int copyQuantity = 1;

}
