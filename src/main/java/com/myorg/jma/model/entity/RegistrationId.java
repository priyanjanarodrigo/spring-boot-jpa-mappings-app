package com.myorg.jma.model.entity;

import static java.util.Objects.hash;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RegistrationId class represents the composite primary key for the Registration entity. It
 * consists of two UUID fields: studentId and courseId. This class is used to uniquely identify a
 * registration record in the database. The equals and hashCode methods are overridden to ensure
 * proper comparison and hashing of RegistrationId objects.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RegistrationId implements Serializable {

  @Serial
  private static final long serialVersionUID = 1307928820905346227L;

  @Column(name = "STUDENT_ID", nullable = false)
  private UUID studentId;

  @Column(name = "COURSE_ID", nullable = false)
  private UUID courseId;

  @Override
  public int hashCode() {
    return hash(studentId, courseId);
  }

  @Override
  public boolean equals(Object object) {
    return object instanceof RegistrationId registrationId
        && Objects.equals(studentId, registrationId.studentId)
        && Objects.equals(courseId, registrationId.courseId);
  }
}
