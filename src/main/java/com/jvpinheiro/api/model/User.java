package com.jvpinheiro.api.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "users")
public class User { 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
    @Column(name = "password", nullable = false)
    private String password;

    @Positive
    @Column(name = "weight")
    private Double weight;

    @Positive
    @Column(name = "height")
    private Double height;

    @Positive
    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;
    
    @Column(name = "registration_date")
    private Date registrationDate;



}
