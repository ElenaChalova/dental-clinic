package org.top.dentalclinic.entity;

import jakarta.persistence.*;

import java.util.Set;

// Doctor описывает сущность "Врача" - запись таблицы специалистов
@Entity
@Table(name = "doctor_t")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name_f", nullable = false)
    private String name;    // ФИО врача

    @Column(name="specialization_f", nullable = false)
    private String specialization; // Специализация врача

    @Column(name="experience_f", nullable = false)
    private Integer experience;    // Стаж работы

    // Связь: много врачей в одном отделении
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "doctor")
    private Set<VisitTime> visitTimeSet;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<FeedbackForm> feedbackFormSet;


    // конструкторы
    public Doctor() {
        id = 0;
        name = "";
        specialization = "";
        experience = 0;
    }

    // getters & setters


    public Set<FeedbackForm> getFeedbackFormSet() {
        return feedbackFormSet;
    }

    public void setFeedbackFormSet(Set<FeedbackForm> feedbackFormSet) {
        this.feedbackFormSet = feedbackFormSet;
    }

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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return id + " " + name +
                ", специализация - " + specialization + ", стаж работы " + experience;
    }
}
