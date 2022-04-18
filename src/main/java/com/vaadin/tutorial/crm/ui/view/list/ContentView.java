package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Student;
import com.vaadin.tutorial.crm.backend.service.StudentService;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.elements.Footer;

@Route(value = "list", layout = MainLayout.class)
@PageTitle("Postgraduate Students | UCLV")
public class ContentView extends VerticalLayout {
    private final StudentService contactService;
    private final Grid<Student> grid = new Grid<>(Student.class);
    private final TextField filter = new TextField();

    public ContentView(StudentService contactService) {
        this.contactService = contactService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        Footer footer = new Footer();
        add(getToolbar(), grid, footer);
        updateList();
    }

    private Component getToolbar() {
        filter.setPlaceholder("Filter by name...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> updateList());
        Button add_student = new Button("Add Student");
        add_student.addClickListener(click -> {
            grid.asSingleSelect().clear();
            editStudent(new Student());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, add_student);
        toolbar.addClassName("toolbar");
        return toolbar;
    }


    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setColumns("firstName", "lastName", "email");
        grid.addColumn(student -> student.getUniversity().getName()).setHeader("University").setSortable(true);
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editStudent(e.getValue()));
    }

    private void editStudent(Student value) {
        if (value != null){
            UI.getCurrent().navigate("form");
            ContactForm.setStudent(value);
        }else ContactForm.setStudent(new Student());
    }

    private void updateList() {
        grid.setItems(contactService.findAllStudents(filter.getValue()));
    }

}
