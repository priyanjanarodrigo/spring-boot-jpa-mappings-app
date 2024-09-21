package com.myorg.jma.model.entity;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
 * Student entity class contains the student details.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
@Table(name = "STUDENT")
public class Student implements Serializable {

  @Serial
  private static final long serialVersionUID = 1317998567913088476L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "ID")
  private UUID id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "AGE", nullable = false)
  private Integer age;

  /**
   * Whoever owns the foreign key column gets the @JoinColumn annotation. In this particular case,
   * the STUDENT table will contain a foreign key as ADDRESS_ID which points to the ADDRESS table's
   * primary key (ID).
   */
  @OneToOne(
      targetEntity = Address.class,
      cascade = ALL,
      fetch = FetchType.EAGER,
      optional = false,
      orphanRemoval = true
  )
  @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
  private Address address;

  /**
   * One student can have many personal interests. Therefore, we have a one-to-many relationship
   * between Student and PersonalInterest entities.
   */
  @OneToMany(
      targetEntity = PersonalInterest.class,
      cascade = ALL,
      mappedBy = "student",
      fetch = FetchType.LAZY,
      orphanRemoval = true
  )
  private List<PersonalInterest> personalInterests;

  /**
   * This implementation is based on a many-to-many mapping between the Course and Student entities.
   * What we have used here is a bidirectional association between the Course and Student entities
   * by placing the associative entity Registration in the middle.Registration is the associative
   * entity that holds the foreign keys to the Course and Student entities as an embedded primary
   * key identifier called RegistrationId.
   * <p>
   * The association between the Student and Course entities is one-to-many. And with the
   * associative entity Registration, it is a one-to-many relationship between the Student and
   * Registration entities.
   */
  @OneToMany(
      targetEntity = Registration.class,
      cascade = {MERGE, PERSIST},
      mappedBy = "student",
      fetch = FetchType.LAZY,
      orphanRemoval = true
  )
  private List<Registration> courses;

  /**
   * This implementation is based on a many-to-many mapping between the Student and Club entities.
   * <p>
   * When deciding how to apply Cascade options in a ManyToMany relationship, it’s important to
   * consider the behavior you want when dealing with related entities. In this case, since Student
   * and Club are separate entities and probably have their own lifecycle management, it might not
   * always make sense to cascade all operations.
   * <p>
   * CascadeType.MERGE: Applied to ensure that when you update the Student, the associated Club
   * relationships are properly merged (but not newly persisted).
   * <p>
   * CascadeType.REFRESH: Allows refreshing the Student or Club from the database if needed, along
   * with its associated entities.
   * <p>
   * Do not cascade REMOVE: Deleting a Student should not delete the associated Club.
   * <p>
   * Be cautious with PERSIST: This should only be used if both entities are being created together,
   * which is typically rare in ManyToMany relationships where the associated entities can already
   * exist independently.
   */
  @ManyToMany(
      targetEntity = Club.class,
      cascade = {MERGE, PERSIST},
      fetch = FetchType.LAZY
  )
  @JoinTable(
      name = "STUDENT_CLUB",
      joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "CLUB_ID", referencedColumnName = "ID")
  )
  private List<Club> clubs;
}
