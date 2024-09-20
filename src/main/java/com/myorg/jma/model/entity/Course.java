package com.myorg.jma.model.entity;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Course entity class contains the course details.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Course")
@Table(name = "COURSE")
public class Course implements Serializable {

  @Serial
  private static final long serialVersionUID = -1936492135974047119L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "ID")
  private UUID id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "FEE", nullable = false)
  private double fee;

  /**
   * This implementation is based on a many-to-many mapping between the Course and Student entities.
   * What we have used here is a bidirectional association between the Course and Student entities
   * by placing the associative entity Registration in the middle.Registration is the associative
   * entity that holds the foreign keys to the Course and Student entities as an embedded primary
   * key identifier called RegistrationId.
   * <p>
   * The association between the Course and Student entities is one-to-many. And with the
   * associative entity Registration, it is a one-to-many relationship between the Course and
   * Registration entities.
   */
  @OneToMany(
      targetEntity = Registration.class,
      cascade = {MERGE, PERSIST},
      mappedBy = "course",
      fetch = LAZY,
      orphanRemoval = true)
  private List<Registration> registrations;
}
