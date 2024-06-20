package HW5_6_7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Security;
import HW5_6_7.model.Reader;

@SpringBootApplication
public class AppHW5_6_7 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AppHW5_6_7.class, args);

//        ReaderRepository readerRepository = context.getBean(ReaderRepository.class);
//
//        Reader reader = new Reader();
//        reader.setName("Ivan");
//        Book b1 = new Book();
//        readerRepository.save(reader);
    }
}
