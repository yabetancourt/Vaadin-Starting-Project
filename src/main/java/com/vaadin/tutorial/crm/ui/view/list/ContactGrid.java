package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.tutorial.crm.backend.Person;

import java.util.ArrayList;
import java.util.List;
import com.vaadin.tutorial.crm.backend.Person;
public class ContactGrid{
    Grid<Person> grid = new Grid<>(Person.class, false);

    public Grid<Person> getGrid() {
        return grid;
    }

    public ContactGrid(){
        grid.addClassName("contact-grid");
        grid.setMinHeight("70%");
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 100; i ++)
            people.add(new Person());
        grid.setItems(people);
        grid.addColumn(Person::getName).setHeader("Student");
        //grid.addColumn(Person::getLastName).setHeader("Last").setFrozen(true);
        grid.addColumn(Person::getGrade).setHeader("Grade");
        grid.addColumn(Person::getEmail).setHeader("Email");
        grid.addColumn(Person::getId).setHeader("ID");
        // grid.setColumnReorderingAllowed(true);
        grid.addColumn(
                new NativeButtonRenderer<Person>("Remove", clickedItem -> {
                    people.remove(4);
                    grid.setItems(people);
                })
        ).setHeader("Action");
        grid.getColumns().forEach(col -> {col.setAutoWidth(true);});
        grid.asSingleSelect().addValueChangeListener(event -> {

        });
    }

}
