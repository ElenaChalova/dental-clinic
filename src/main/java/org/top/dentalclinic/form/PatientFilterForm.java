package org.top.dentalclinic.form;

// PatientFilterForm - форма для фильтрации пациентов
public class PatientFilterForm {
    private String name;

    public PatientFilterForm() {
        name = "";
    }

    public boolean isFormEmpty() {
        return name.equals("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
