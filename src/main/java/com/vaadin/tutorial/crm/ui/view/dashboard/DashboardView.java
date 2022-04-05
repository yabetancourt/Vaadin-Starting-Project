package com.vaadin.tutorial.crm.ui.view.dashboard;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
//import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.backend.service.StudentService;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.elements.Footer;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | UCLV")
public class DashboardView extends VerticalLayout {

    private StudentService studentService;
    public DashboardView(StudentService studentService){
        this.studentService = studentService;
        Chart c = new Chart(ChartType.BAR);
        c.drawChart();
        add(c);
        add(new Footer());
    }

    private Component getContactStats(){
        Span stats = new Span(studentService.count() + "students");
        stats.setClassName("contacts-stats");
        return stats;
    }
}
