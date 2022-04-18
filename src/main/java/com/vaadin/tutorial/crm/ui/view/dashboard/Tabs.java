package com.vaadin.tutorial.crm.ui.view.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.backend.entity.Student;
import com.vaadin.tutorial.crm.backend.service.StudentService;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.elements.Footer;
import com.vaadin.tutorial.crm.ui.elements.ToggleButton;
import com.vaadin.tutorial.crm.ui.view.list.ContactForm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@PreserveOnRefresh
@Route(value = "tabs", layout = MainLayout.class)
@PageTitle("Tabs | UCLV")
public class Tabs extends VerticalLayout {

    private final StudentService studentService;
    private final Grid<Student> grid1 = new Grid<>(Student.class, false);
    private final Grid<Student> grid2 = new Grid<>(Student.class);
    private final Grid<Student> grid3 = new Grid<>(Student.class);
    private static String className = "algo";

    public Tabs(StudentService studentService){
        this.studentService = studentService;
        config();
        setClassName(className);
        Tab tab1 = new Tab("UCLV");
        Div page1 = new Div(grid1);
        Tab tab2 = new Tab("UH");
        Div page2 = new Div(grid2);
        page2.setVisible(false);
        Tab tab3 = new Tab("UO");
        Div page3 = new Div(grid3);
        page3.setVisible(false);
        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        tabsToPages.put(tab3, page3);
        Div pages = new Div(page1, page2, page3);
        Set<Component> pagesShown = Stream.of(page1).collect(Collectors.toSet());
        com.vaadin.flow.component.tabs.Tabs tabs = new com.vaadin.flow.component.tabs.Tabs(tab1, tab2, tab3);
        tabs.addSelectedChangeListener(click -> {
            pagesShown.forEach(page -> page.setVisible(false));
            pagesShown.clear();
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
            pagesShown.add(selectedPage);
        });

        add(tabs);

        pages.setMinWidth("100%");
        add(pages);
        add(new Footer());
        add(new ToggleButton());
    }

    private void config(){
        configUclv();
        configUh();
        configUo();
    }

    public static void setClassNam(String name){
        className = name;
    }
    public static String getClassNam(){
        return className;
    }


    private void configUclv(){
        grid1.addClassName("contact-grid");
        grid1.addColumn(new ComponentRenderer<>(
                student -> new Avatar(student.getFirstName() + " " + student.getLastName())))
                .setHeader("");
        grid1.addColumn(student -> student.getFirstName()).setHeader("First Name").setSortable(true);
        grid1.addColumn(student -> student.getLastName()).setHeader("Last Name").setSortable(true);
        grid1.addColumn(student -> student.getEmail()).setHeader("Email").setSortable(true);
        grid1.addColumn(student -> student.getUniversity().getName()).setHeader("University").setSortable(true);
        grid1.getColumns().forEach(col -> col.setAutoWidth(true));
        grid1.getColumns().get(0).setAutoWidth(false).setWidth("6px");
        grid1.setItems(studentService.findByUni("UCLV"));
        grid1.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid1.asSingleSelect().addValueChangeListener(e -> editStudent(e.getValue()));
    }
    private void editStudent(Student value) {
        if (value != null){
            UI.getCurrent().navigate("form");
            ContactForm.setStudent(value);
        }else ContactForm.setStudent(new Student());
    }

    private void configUh(){
        grid2.addClassName("contact-grid");
        grid2.setColumns("firstName", "lastName", "email");
        grid2.addColumn(student -> student.getUniversity().getName()).setHeader("University").setSortable(true);
        grid2.getColumns().forEach(col -> col.setAutoWidth(true));
        grid2.setItems(studentService.findByUni("UH"));
        grid2.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid2.addThemeVariants(GridVariant.LUMO_NO_BORDER);
    }

    private void configUo(){
        grid3.addClassName("contact-grid");
        grid3.setColumns("firstName", "lastName", "email");
        grid3.addColumn(student -> student.getUniversity().getName()).setHeader("University").setSortable(true);
        grid3.getColumns().forEach(col -> col.setAutoWidth(true));
        grid3.setItems(studentService.findByUni("UO"));
    }
}
