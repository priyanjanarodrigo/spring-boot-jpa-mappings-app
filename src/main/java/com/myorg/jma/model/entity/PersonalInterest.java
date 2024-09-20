package com.myorg.jma.model.entity;

import static jakarta.persistence.CascadeType.ALL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PersonalInterest entity class contains the personal interest details of a student.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PersonalInterest")
@Table(name = "PERSONAL_INTEREST")
public class PersonalInterest implements Serializable {

  @Serial
  private static final long serialVersionUID = 6751224467718085252L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "ID")
  private UUID id;

  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  @ManyToOne(
      targetEntity = Student.class,
      cascade = ALL,
      fetch = FetchType.LAZY,
      optional = false
  )
  @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
  private Student student;
}
