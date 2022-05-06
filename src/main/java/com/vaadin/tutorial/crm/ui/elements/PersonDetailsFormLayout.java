package com.vaadin.tutorial.crm.ui.elements;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.tutorial.crm.backend.entity.Person;
import com.vaadin.tutorial.crm.ui.view.list.PersonInfo;

import java.util.stream.Stream;

public class PersonDetailsFormLayout extends FormLayout {
    //Attributes
    private final TextField collegeDegreeField = new TextField("Grado Universitario");
    private final TextField phoneField = new TextField("Numero de Telefono");
    private final TextField addressField = new TextField("Direccion");
    private final TextField birthField = new TextField("Fecha de Nacimiento");
    private final TextField sexField = new TextField("Sexo");
    private final TextField graduationYearField = new TextField("Ano de Graduacion");
    private final TextField yearsExperienceField = new TextField("Anos de Experiencia");
    private final TextField collegeGraduateField = new TextField("Universidad");
    private static Person stud;

    //Constructor
    public PersonDetailsFormLayout(){
        Stream.of(addressField, phoneField, birthField, sexField, collegeDegreeField, collegeGraduateField, graduationYearField).forEach(field -> {
            field.setReadOnly(true);
            add(field);
        });
        setResponsiveSteps(new ResponsiveStep("0", 2));
        setColspan(addressField, 2);
        Button more = new Button("Ver Mas...");
        more.setIcon(new Icon(VaadinIcon.EYE));
        HorizontalLayout layout = new HorizontalLayout(more);
        more.addClickListener(event -> editPerson());
        layout.setWidthFull();
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        layout.setMargin(true);
        add(layout);
        setColspan(layout, 2);
    }

    //Methods
    //set the Person and set all the fields with the Person
    public void setPerson(Person person){
        stud = person;
        collegeDegreeField.setValue(person.getCollege_degree() == null ? "" : person.getCollege_degree());
        phoneField.setValue(person.getPhone() == null ? "" : person.getPhone());
        sexField.setValue(person.getSex() == null ? "" : person.getSex());
        addressField.setValue(person.getAddress() == null ? "" : person.getAddress());
        birthField.setValue(person.getBirth_date() == null ? "" : person.getBirth_date().toString());
        graduationYearField.setValue(person.getGraduation_year() == null ? "" : person.getGraduation_year().toString());
        yearsExperienceField.setValue(person.getYears_experience() == null ? "" : person.getYears_experience().toString());
        collegeGraduateField.setValue(person.getCollegeGraduate() == null ? "" : person.getCollegeGraduate().getName());
    }

    //navigate to PersonInfo with this Person
    private void editPerson() {
        if (stud != null){
            PersonInfo.setPerson(stud);
            UI.getCurrent().navigate("information");
        }
    }

}
