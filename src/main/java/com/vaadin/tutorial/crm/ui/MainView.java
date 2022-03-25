package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.Person;
import java.util.ArrayList;
import java.util.List;

@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    ContactGrid grid = new ContactGrid();
    TextField filter = new TextField();
    Button addStudent = new Button("Add Student");
    HorizontalLayout horizontalLayout;
    Div content;
    ContactForm contactForm;
    public MainView() {
        addClassName("list-view");
        setSizeFull();

        configureFilter();
        configureAddStudent();
        horizontalLayout = new HorizontalLayout(filter, addStudent);
        horizontalLayout.setWidth("50%");
        content = new Div(grid.getGrid());
        content.addClassName("content");
        content.setSizeFull();
        contactForm = new ContactForm(content, horizontalLayout);
        contactForm.getStyle().set("display", "none");
        add(horizontalLayout, content);
        add(contactForm);

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
