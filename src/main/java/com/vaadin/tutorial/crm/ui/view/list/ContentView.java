package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.elements.Footer;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Postgraduate Students | UCLV")
public class ContentView extends VerticalLayout {
    //Attributes
    ContactGrid grid = new ContactGrid();
    TextField filter = new TextField();
    Button addStudent = new Button("Add Student");
    HorizontalLayout horizontalLayout;
    Div content;
    ContactForm contactForm;

    public ContentView() {
        addClassName("list-view");
        setSizeFull();
        configureFilter();
        configureAddStudent();
        horizontalLayout = new HorizontalLayout(filter, addStudent);
        horizontalLayout.setWidth("50%");
        content = new Div(grid.getGrid());
        content.addClassName("content");
        content.setSizeFull();
        contactForm = new ContactForm(content, horizontalLayout, grid);
        contactForm.getStyle().set("display", "none");
        add(horizontalLayout, content);
        add(contactForm);
        Footer footer = new Footer();
        add(footer);
    }

    private void configureAddStudent(){
        addStudent.addClickListener(click -> {
            content.getStyle().set("display", "none");
            horizontalLayout.getStyle().set("display", "none");
            contactForm.getStyle().set("display", "flex");
        });
    }

    private void configureFilter(){
        filter.setPlaceholder("Filter by name...");
        filter.setWidth("50%");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.LAZY);
    }

}
