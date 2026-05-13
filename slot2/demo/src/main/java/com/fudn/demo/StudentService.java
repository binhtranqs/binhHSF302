package com.fudn.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createStudent(String name, String email, int age) {
        Student s = new Student(name, email, age);
        em.persist(s);      // INSERT
        System.out.println("Saved with ID = " + s.getId());
    }

    @Transactional(readOnly = true)
    public void printAll() {
        em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultList()
                .forEach(System.out::println);
    }
    @Transactional
    public void updateStudent(Long id, String newName, String newEmail, int newAge) {
        // 1. Tìm sinh viên theo ID
        Student student = em.find(Student.class, id);

        if (student != null) {
            // 2. Cập nhật thông tin qua setter (Dựa trên image_5670e2.jpg)
            student.setFullName(newName);
            student.setEmail(newEmail);
            student.setAge(newAge);

            // Do trong @Transactional, JPA sẽ tự động nhận diện thay đổi
            // và thực hiện lệnh UPDATE khi kết thúc phương thức (Dirty Checking).
            System.out.println("Updated student with ID = " + id);
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }

    @Transactional
    public void deleteStudent(Long id) {
        // 1. Tìm sinh viên trước khi xóa
        Student student = em.find(Student.class, id);

        if (student != null) {
            // 2. Thực hiện xóa
            em.remove(student);
            System.out.println("Deleted student with ID = " + id);
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }
}