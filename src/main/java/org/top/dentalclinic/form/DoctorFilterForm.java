package org.top.dentalclinic.form;

// DoctorFilterForm - форма для фильтрации врачей
public class DoctorFilterForm {
    private String doctor;

    public DoctorFilterForm() {
        doctor = "";
    }

    public boolean isFormDoctorEmpty() {
        return doctor.equals("");
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
