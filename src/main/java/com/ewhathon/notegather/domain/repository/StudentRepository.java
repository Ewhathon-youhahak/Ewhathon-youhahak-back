package com.ewhathon.notegather.domain.repository;

import com.ewhathon.notegather.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
