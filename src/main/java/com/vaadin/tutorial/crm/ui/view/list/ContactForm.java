package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.tutorial.crm.backend.Person;

public class ContactForm extends FormLayout {
    //attributes
    TextField fNameTextField = new TextField("First Name");
    TextField lNameTextField = new TextField("Last Name");
    TextField IdTextField = new TextField("Identification Number");
    EmailField emailField = new EmailField("Email");
    ComboBox<String> comboBox = new ComboBox<>("Grade", new String[]{"Master", "Doctor"});
    Button buttonSave = new Button("Save");
    Button buttonCancel = new Button("Cancel");
    Button buttonDelete = new Button("Delete");
    Binder<Person> binder = new Binder<>(Person.class);
    AccordionPanel accordionPanel;

    public ContactForm(Div div, HorizontalLayout layout, ContactGrid grid){
        validate();
        add(new H4("Information:"));
        add(new H4(""));
        addClassName("contact-form");
        configureCancel(div, layout);
        configureDelete();
        configureSave(grid);
        configureAccordion();
        add(fNameTextField);
        add(lNameTextField);
        add(emailField);
        add(comboBox);
        add(IdTextField);
        add(new H4(""));
        add(accordionPanel);
        add(new H4(""));
        add(createButtonsLayout());

    }

    private void configureAccordion(){
        FormLayout detailsFormLayout = new FormLayout();
        TextField address = new TextField("Address");
        TextField phone = new TextField("Phone Number");
        DatePicker birth = new DatePicker("Birth");
        detailsFormLayout.add(address, phone, birth);
        accordionPanel = new AccordionPanel("More Details", detailsFormLayout);
        accordionPanel.addOpenedChangeListener(e -> {
        });
    }
    private void validate(){
        binder.forField(fNameTextField).asRequired().withValidator(name -> validateName(name), "Only Letters").bind(Person::getFirstName, Person::setFirstName);
        binder.forField(lNameTextField).asRequired().withValidator(name -> validateName(name), "Only Letters").bind(Person::getLastName, Person::setLastName);
        binder.forField(emailField).asRequired().bind(Person::getEmail, Person::setEmail);
        binder.forField(IdTextField).asRequired().withValidator(id -> id.length() == 11, "Incorrect Identification Number").bind(Person::getId, Person::setId);
        binder.forField(comboBox).asRequired().bind(Person::getGrade, Person::setGrade);
    }

    private boolean validateName(String s){
        for (int i = 0; i < s.length(); i++){
            if (!Character.isLetter(s.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private void configureSave(ContactGrid grid){
        buttonSave.addClickListener(buttonClickEvent -> {
            try{
                Person saved = new Person();
                binder.writeBean(saved);
                saved.setName(saved.getFirstName() + " " +saved.getLastName());
                grid.people.add(saved);
                grid.getGrid().setItems(grid.people);
                buttonDelete.click();
                buttonCancel.click();

            }catch (ValidationException e){
                System.out.println("error");
            }
        });
    }

    private void configureCancel(Div div, HorizontalLayout layout){
        buttonCancel.addClickListener(buttonClickEvent -> {
            getStyle().set("display", "none");
            div.getStyle().set("display", "flex");
            layout.getStyle().set("display", "flex");
        });
    }

    private void configureDelete(){
        buttonDelete.addClickListener(buttonClickEvent -> {
            fNameTextField.clear();
            lNameTextField.clear();
            emailField.clear();
            IdTextField.clear();
            comboBox.clear();
        });
    }

    private HorizontalLayout createButtonsLayout(){
        buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        HorizontalLayout layout = new HorizontalLayout(buttonSave, buttonDelete, buttonCancel);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setMargin(true);
        return layout;
    }


}
