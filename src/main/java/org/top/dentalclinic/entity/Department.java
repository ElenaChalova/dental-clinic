package org.top.dentalclinic.entity;

import jakarta.persistence.*;

import java.util.Set;

// Department описывает сущность "Отделение" - запись таблицы отделений
@Entity
@Table(name = "department_t")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;    // Название отделения

    @Column(name="address_f", nullable = false)
    private String address; // Адрес филиала, где находится отделение

    @Column(name="phone_number_f", nullable = false)
    private String phoneNumber;    // Номер телефона отделения

    // Связи
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<MedicalServ> medicalServSet;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Doctor> doctorSet;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<FeedbackForm> feedbackFormSet;

    // конструкторы
    public Department() {
        id = 0;
        name = "";
        address = "";
        phoneNumber = "";
    }

    // getters & setters


    public Set<FeedbackForm> getFeedbackFormSet() {
        return feedbackFormSet;
    }

    public void setFeedbackFormSet(Set<FeedbackForm> feedbackFormSet) {
        this.feedbackFormSet = feedbackFormSet;
    }

    public Set<Doctor> getDoctorSet() {
        return doctorSet;
    }

    public void setDoctorSet(Set<Doctor> doctorSet) {
        this.doctorSet = doctorSet;
    }

    public Set<MedicalServ> getMedicalServSet() {
        return medicalServSet;
    }

    public void setMedicalServSet(Set<MedicalServ> medicalServSet) {
        this.medicalServSet = medicalServSet;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ToString

    @Override
    public String toString() {
        return id + " - " + name +
                "; адрес филиала: " + address +
                "; контактный номер телефона: " + phoneNumber;
    }
}
