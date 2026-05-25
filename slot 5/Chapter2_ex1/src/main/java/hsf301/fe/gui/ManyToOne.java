package hsf301.fe.gui;

import hsf301.fe.configs.AppConfig;
import hsf301.fe.pojos.Student;
import hsf301.fe.services.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ManyToOne {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService myService = context.getBean(StudentService.class);
        Student st = new Student("Lam", "Nguyen", 8);

        myService.save(st);
    }
}
