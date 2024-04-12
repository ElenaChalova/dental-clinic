package org.top.dentalclinic.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

// User - таблица формы обратной связи
@Entity
@Table(name="feedback_t")
public class FeedbackForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;    // ФИО пациента

    @Column(name="date_birth_f")
    private LocalDate dateBirth; // Дата рождения пациента

    @Column(name="phone_number_f", nullable = false)
    private String phoneNumber;    // Номер телефона пациента

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // конструкторы
    public FeedbackForm() {
        id = 0;
        name = "";
        dateBirth = null;
        phoneNumber = "";
    }

    // getters & setters


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
