package com.myorg.jma.repository;

import com.myorg.jma.model.entity.Course;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CourseRepository interface extends JpaRepository to provide CRUD operations for the Course
 * entity.
 */
@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, UUID> {

}
