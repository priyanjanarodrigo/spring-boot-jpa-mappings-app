package com.myorg.jma.model.entity;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Club")
@Table(name = "CLUB")
public class Club implements Serializable {

  @Serial
  private static final long serialVersionUID = 5787159696145325341L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "ID")
  private UUID id;

  @Column(name = "NAME", nullable = false)
  private String name;

  /**
   * This implementation is based on a many-to-many mapping between the Club and Student entities.
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
      targetEntity = Student.class,
      mappedBy = "clubs",
      cascade = {MERGE, PERSIST},
      fetch = FetchType.LAZY)
  private List<Student> students;
}
