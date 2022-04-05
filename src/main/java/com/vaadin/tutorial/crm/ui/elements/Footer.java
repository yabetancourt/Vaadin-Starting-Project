package com.vaadin.tutorial.crm.ui.elements;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode.CENTER;

public class Footer extends VerticalLayout{

    public Footer(){
        setClassName("footer");
        setWidthFull();
        getStyle().set("background-color", "rgba(252, 245, 255, 0.05)");
        setJustifyContentMode(CENTER);
        setAlignItems(Alignment.CENTER);
        setAlignSelf(Alignment.AUTO);
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.getStyle().set("display", "flex");

        //First column
        VerticalLayout studiesLayout = new VerticalLayout();

        H5 studies = new H5("Studies");
        Anchor undergraduate = new Anchor("", "Undergraduate");
        Anchor research = new Anchor("", "Research Centers");
        Anchor graduate = new Anchor("", "Graduate");
        Anchor projects = new Anchor("", "Projects");
        Anchor project = new Anchor("", "Projects");
        H5 follow = new H5("Follow us");
        Icon face = new Icon(VaadinIcon.FACEBOOK);
        face.setColor("#3b5998");
        face.addClickListener(click -> {

        });
        Icon google = new Icon(VaadinIcon.GOOGLE_PLUS);
        google.setColor("#ea4335");
        Icon twitter = new Icon(VaadinIcon.TWITTER);
        twitter.setColor("#55acee");
        Icon telegram = new Icon(VaadinIcon.PAPERPLANE);
        telegram.setColor("#0088cc");
        HorizontalLayout net = new HorizontalLayout(face, google, twitter, telegram);
        studiesLayout.add(studies, undergraduate, research, graduate, projects, project, follow, net);
        mainLayout.add(studiesLayout);

        //Second Column
        VerticalLayout links = new VerticalLayout();
        H5 linksInter = new H5("Links of Interest");
        Anchor ministry = new Anchor("", "Ministry of Higher Education");
//        Anchor ecu = new Anchor("", "Ecured");
        Anchor institute = new Anchor("", "Institute");
        Anchor portal = new Anchor("", "Portal");
        Anchor red = new Anchor("", "Science net");
        H5 email = new H5("Email");
        Anchor contactEmail = new Anchor("","contacto@uclv.edu.cu");
        links.add(linksInter, ministry, institute, portal, red, email, contactEmail);
        mainLayout.add(links);

        //Third Column
        VerticalLayout contactLayout = new VerticalLayout();
        H5 contact = new H5("Contact");
        Anchor address = new Anchor("", "Address");
        Span textAddress = new Span("Highway to Camajuani km. 5 and 1/2. Santa Clara. Villa Clara. Cuba");
        contactLayout.add(contact, address, textAddress);

        H5 phones = new H5("Phones");
        Span rect = new Span("Rectorship");
        Anchor phone = new Anchor("", "(53)42281178");
        HorizontalLayout temp = new HorizontalLayout(phone, rect);
        contactLayout.add(phones, temp);

        rect = new Span("Rectorship");
        phone = new Anchor("", "(53)42281178");
        temp = new HorizontalLayout(phone, rect);
        contactLayout.add(temp);

        rect = new Span("Rectorship");
        phone = new Anchor("", "(53)42281178");
        temp = new HorizontalLayout(phone, rect);
        contactLayout.add(temp);


        // contactLayout.add(email, contactEmail);
        mainLayout.add(contactLayout);

        Span span = new Span("Copyright 2022 UCLV. All rights reserved");
        span.getStyle().set("font-size", "0.7em");
        mainLayout.setWidth("100%");
        mainLayout.setJustifyContentMode(JustifyContentMode.START);
        mainLayout.setAlignItems(Alignment.START);
        add(mainLayout);
        add(span);
    }
}
