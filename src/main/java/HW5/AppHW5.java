package HW5;

import HW5.model.Book;
import HW5.model.Reader;
import HW5.repository.ReaderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppHW5 {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AppHW5.class, args);

//        ReaderRepository readerRepository = context.getBean(ReaderRepository.class);
//
//        Reader reader = new Reader();
//        reader.setName("Ivan");
        Book b1 = new Book();
//        readerRepository.save(reader);
    }
}
