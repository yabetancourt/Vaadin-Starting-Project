package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.tutorial.crm.backend.Person;

import java.util.List;

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
    //Binder<Person> binder = new Binder<>(Person.class);

    public ContactForm(Div div, HorizontalLayout layout){
        //binder.bindInstanceFields(this);
        addClassName("contact-form");
        configureCancel(div, layout);
        configureDelete();
        add(fNameTextField);
        add(lNameTextField);
        add(emailField);
        add(comboBox);
        add(IdTextField);
        add(createButtonsLayout());
    }

    private void configureSave(){

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
        return layout;
    }
}
