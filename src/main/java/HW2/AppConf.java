package HW2;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConf {
    @Bean
    @Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)
    @Primary
    StudentDB studentDB() {return new StudentDB();}

    StudentController studentController(StudentDB studentDB) {return new StudentController(studentDB);}
}
