package com.example.TestSpringJPA;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repository;

    // Injecting StudentRepository instead of UserRepository
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // Returns a list of all Student objects
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Handles both Insert and Update for Students
    public void saveStudent(Student student) {
        repository.save(student);
    }

    // Deletes a student using their Long ID
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    // Fetches a single student by ID for the edit form
    public Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
    }
}