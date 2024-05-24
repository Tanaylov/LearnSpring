package HW1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		Student student = new Student("Sass", "Factory");
//		System.out.println(student);
//		System.out.println(new StudentDB().getStudentsByPartOfName("van"));
		SpringApplication.run(Application.class, args);
	}

}
