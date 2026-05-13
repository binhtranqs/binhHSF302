package com.fudn.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentService service) {
        return args -> {
            // 1. Thêm mới sinh viên (Đã có trong image_56715d.jpg)
            System.out.println("--- Đang tạo sinh viên mới ---");
            service.createStudent("Nguyễn Văn A", "a@fpt.edu.vn", 20);
            service.createStudent("Trần Thị B", "b@fpt.edu.vn", 21);
            service.createStudent("Lê Văn C", "c@fpt.edu.vn", 22);

            // 2. Hiển thị danh sách ban đầu
            System.out.println("\n--- Danh sách sinh viên hiện tại ---");
            service.printAll();

            // 3. Cập nhật sinh viên có ID là 1 (Nguyễn Văn A)
            // Lưu ý: ID được tự động tăng (IDENTITY) như thiết lập trong image_5670e2.jpg
            System.out.println("\n--- Đang cập nhật sinh viên ID 1 ---");
            service.updateStudent(1L, "Nguyễn Văn A - Updated", "a_new@fpt.edu.vn", 25);

            // 4. Xóa sinh viên có ID là 2 (Trần Thị B)
            System.out.println("\n--- Đang xóa sinh viên ID 2 ---");
            service.deleteStudent(2L);

            // 5. Kiểm tra lại kết quả cuối cùng
            System.out.println("\n--- Danh sách sinh viên sau khi update và delete ---");
            service.printAll();
        };
    }
}