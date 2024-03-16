package com.ewhathon.notegather.domain.repository;

import com.ewhathon.notegather.domain.entity.Lecture;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findLectureByNameAndProfessor(String name, String professor);
}
