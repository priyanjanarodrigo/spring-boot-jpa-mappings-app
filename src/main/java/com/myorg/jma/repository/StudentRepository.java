package com.myorg.jma.repository;

import com.myorg.jma.model.entity.Student;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * StudentRepository interface extends JpaRepository to provide CRUD operations for Student entity.
 */
@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, UUID> {

}
