package com.fudn.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Khởi chạy ngữ cảnh Spring để có thể @Autowired Service
@Transactional   // Tự động Rollback dữ liệu sau mỗi test case để DB luôn sạch
public class studentTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private EntityManager em; // Dùng để kiểm tra dữ liệu trực tiếp trong DB

    @Test
    void testUpdateStudent() {
        // 1. Chuẩn bị: Tạo một sinh viên mẫu
        Student s = new Student("Cũ", "old@fpt.edu.vn", 20);
        em.persist(s);
        em.flush(); // Đẩy dữ liệu xuống DB để có ID
        Long id = s.getId();

        // 2. Thực hiện: Gọi phương thức update từ Service
        studentService.updateStudent(id, "Mới", "new@fpt.edu.vn", 22);
        em.flush(); // Đồng bộ thay đổi
        em.clear(); // Xóa cache của EntityManager để đảm bảo lấy dữ liệu mới nhất từ DB

        // 3. Kiểm chứng: Lấy lại sinh viên đó và so sánh
        Student updated = em.find(Student.class, id);
        assertNotNull(updated);
        assertEquals("Mới", updated.getFullName());
        assertEquals("new@fpt.edu.vn", updated.getEmail());
        assertEquals(22, updated.getAge());
    }

    @Test
    void testDeleteStudent() {
        // 1. Chuẩn bị
        Student s = new Student("Sắp bị xóa", "delete@fpt.edu.vn", 20);
        em.persist(s);
        em.flush();
        Long id = s.getId();

        // 2. Thực hiện
        studentService.deleteStudent(id);
        em.flush();
        em.clear();

        // 3. Kiểm chứng: Tìm lại phải ra null
        Student deleted = em.find(Student.class, id);
        assertNull(deleted, "Sinh viên lẽ ra phải bị xóa khỏi Database");
    }
}