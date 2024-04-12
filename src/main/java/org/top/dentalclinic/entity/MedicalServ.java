package org.top.dentalclinic.entity;

import jakarta.persistence.*;

//MedicalService описывает сущность "Медицинская услуга"
@Entity
@Table(name = "medical_service_t")
public class MedicalServ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_f", nullable = false)
    private String name;    // Название медицинской услуги

    @Column(name = "price_f", nullable = false)
    private Double price; // Цена медицинской услуги

    // Связь: услуга ссылается на отделение, много услуг к одному отделению
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;


    // конструкторы
    public MedicalServ() {
        id = 0;
        name = "";
        price = 0.0;
    }

    // getters & setters


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
                ", цена=" + price;
    }
}


