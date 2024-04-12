package org.top.dentalclinic.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patient_t")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;    // ФИО пациента

    @Column(name="date_birth_f")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateBirth; // Дата рождения пациента

    @Column(name="phone_number_f", nullable = false)
    private String phoneNumber;    // Номер телефона пациента

    @OneToMany(mappedBy = "patient")
    private Set<VisitTime> visitTimeSet;


    // конструкторы
    public Patient() {
        id = 0;
        name = "";
        dateBirth = null;
        phoneNumber = "";
    }

    // getters & setters


    public Set<VisitTime> getVisitTimeSet() {
        return visitTimeSet;
    }

    public void setVisitTimeSet(Set<VisitTime> visitTimeSet) {
        this.visitTimeSet = visitTimeSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  name +
                " , дата рождения: " + dateBirth +
                " , контактный номер: " + phoneNumber;

    }
}
