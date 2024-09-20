package com.myorg.jma.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Registration entity class contains the registration details of a student for a course.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Registration")
@Table(name = "REGISTRATION")
public class Registration implements Serializable {

  @Serial
  private static final long serialVersionUID = 735459600230504947L;

  @EmbeddedId
  private RegistrationId id;

  @ManyToOne
  @MapsId("studentId")
  @JoinColumn(name = "STUDENT_ID", nullable = false)
  private Student student;

  @ManyToOne
  @MapsId("courseId")
  @JoinColumn(name = "COURSE_ID", nullable = false)
  private Course course;

  @Column(name = "REGISTRATION_DATE", nullable = false)
  private LocalDateTime registrationDate;
}
