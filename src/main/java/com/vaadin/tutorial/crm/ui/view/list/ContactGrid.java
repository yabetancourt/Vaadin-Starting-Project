package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.tutorial.crm.backend.Person;
import java.util.ArrayList;
import java.util.List;

public class ContactGrid{
    //Attributes
    Grid<Person> grid = new Grid<>(Person.class, false);
    public List<Person> people = new ArrayList<>();
    public Grid<Person> getGrid() {
        return grid;
    }

    public ContactGrid(){
        grid.addClassName("contact-grid");
        grid.setMinHeight("80%");
        for (int i = 0; i < 100; i ++)
            people.add(new Person());
        grid.setItems(people);
        grid.addColumn(Person::getName).setHeader("Student");
        grid.addColumn(Person::getGrade).setHeader("Grade");
        grid.addColumn(Person::getEmail).setHeader("Email");
        grid.addColumn(Person::getId).setHeader("ID");
        grid.getColumns().forEach(col -> {col.setAutoWidth(true);});
        grid.asSingleSelect().addValueChangeListener(event -> {

        });
    }

}
