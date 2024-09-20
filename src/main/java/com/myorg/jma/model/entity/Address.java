package com.myorg.jma.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
 * Address entity class contains the address details.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Address")
@Table(name = "ADDRESS")
public class Address implements Serializable {

  @Serial
  private static final long serialVersionUID = 1307928820905346227L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "ID")
  private UUID id;

  @Column(name = "RESIDENCE_NO", nullable = false)
  private String residenceNo;

  @Column(name = "ADDRESS_LINE_1", nullable = false)
  private String addressLine1;

  @Column(name = "ADDRESS_LINE_2", nullable = false)
  private String addressLine2;

  @Column(name = "CITY", nullable = false)
  private String city;

  @JsonIgnore
  @OneToOne(targetEntity = Student.class, mappedBy = "address", optional = false)
  private Student student;
}
