package com.example.TestSpringJPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository provides all CRUD methods (save, findAll, deleteById, etc.)
    // automatically for the Student entity using a Long ID.
}