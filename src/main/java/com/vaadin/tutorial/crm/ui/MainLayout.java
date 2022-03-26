package com.vaadin.tutorial.crm.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.tutorial.crm.ui.elements.Footer;
import com.vaadin.tutorial.crm.ui.view.list.ListView;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Postgraduate UCLV");
        logo.addClassName("logo");
        Anchor a = new Anchor("http://localhost:8080/", "Log out");
        a.getStyle().set("padding", "15px");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, a);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(
               FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink listLink = new RouterLink("List", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink Link = new RouterLink("Link", ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink profileLink = new RouterLink("profile", ListView.class);
        profileLink.setHighlightCondition(HighlightConditions.sameLocation());
        VerticalLayout layout = new VerticalLayout(listLink, Link, profileLink);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        addToDrawer(layout);
    }

}
