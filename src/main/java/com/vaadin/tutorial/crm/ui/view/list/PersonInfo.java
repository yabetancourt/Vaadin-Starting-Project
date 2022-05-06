package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Person;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.elements.Footer;

import java.util.stream.Stream;
@PageTitle("Informacion | UCLV")
@Route(value = "information", layout = MainLayout.class)
public class PersonInfo extends VerticalLayout {

    //Attributes
    private final TextField nameField = new TextField("Nombre");
    private final TextField lastNameField = new TextField("Apellido");
    private final TextField idNumberField = new TextField("Carnet de Identidad");
    private final TextField emailField = new TextField("Correo Electronico");
    private final TextField collegeDegreeField = new TextField("Grado Universitario");
    private final TextField phoneField = new TextField("Numero Telefonico");
    private final TextField addressField = new TextField("Direccion");
    private final TextField birthField = new TextField("Fecha de nacimiento");
    private final TextField sexField = new TextField("Sexo");
    private final TextField graduationYearField = new TextField("Ano de Graduacion");
    private final TextField yearsExperienceField = new TextField("Anos de Experiencia");
    private final TextField collegeGraduateField = new TextField("Universidad");
    private final Button backButton = new Button("Atras");
    private final Button editButton = new Button("Editar");
    private static Person person;

    //Constructor
    public PersonInfo(){
        configure();
        add(new H4(  "Informacion de " + person.getName() + ":"));
        FormLayout layout = new FormLayout();
        Stream.of(nameField, lastNameField, idNumberField, emailField, phoneField, birthField, sexField, collegeDegreeField, collegeGraduateField, graduationYearField, yearsExperienceField, addressField ).forEach(field -> {
            field.setReadOnly(true);
            layout.add(field);
        });
        layout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
        layout.setColspan(addressField, 2);
        add(layout);
        add(configureButtons());
        add(new Footer());
    }

    //Methods

    public static void setPerson(Person value){
        person = value;
    }
    //Configuration back and edit buttons
    private HorizontalLayout configureButtons(){
        backButton.addClickListener(click -> UI.getCurrent().navigate("list"));
        backButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        editButton.addClickListener(click -> editPerson());
        HorizontalLayout ans = new HorizontalLayout(backButton, editButton);
        ans.setJustifyContentMode(JustifyContentMode.END);
        ans.setWidthFull();
        return ans;
    }

    //set the values to all the fields with the Person's attributes
    private void configure(){
        nameField.setValue(person.getName() == null ? "" : person.getName());
        lastNameField.setValue(person.getLastName() == null ? "" : person.getLastName());
        idNumberField.setValue(person.getIdNumber() == null ? "" : person.getIdNumber());
        emailField.setValue(person.getEmail() == null ? "" : person.getEmail());
        collegeDegreeField.setValue(person.getCollege_degree() == null ? "" : person.getCollege_degree());
        phoneField.setValue(person.getPhone() == null ? "" : person.getPhone());
        sexField.setValue(person.getSex() == null ? "" : person.getSex());
        addressField.setValue(person.getAddress() == null ? "" : person.getAddress());
        birthField.setValue(person.getBirth_date() == null ? "" : person.getBirth_date().toString());
        graduationYearField.setValue(person.getGraduation_year() == null ? "" : person.getGraduation_year().toString());
        yearsExperienceField.setValue(person.getYears_experience() == null ? "" : person.getYears_experience().toString());
        collegeGraduateField.setValue(person.getCollegeGraduate() == null ? "" : person.getCollegeGraduate().getName());
    }


    private void editPerson() {
        if (person != null){
            PersonForm.setPerson(person);
            UI.getCurrent().navigate("form");
        }
    }
}
