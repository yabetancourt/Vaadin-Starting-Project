package com.vaadin.tutorial.crm.ui.view.dashboard;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.tutorial.crm.ui.MainLayout;
import com.vaadin.tutorial.crm.ui.elements.Footer;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Dashboard | UCLV")
public class DashboardView extends VerticalLayout {

    public DashboardView(){
        Chart c = new Chart(ChartType.BAR);
        c.drawChart();
        add(c);
        add(new Footer());
    }

}
