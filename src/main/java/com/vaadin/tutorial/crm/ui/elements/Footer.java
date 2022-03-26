package com.vaadin.tutorial.crm.ui.elements;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Footer extends VerticalLayout{

    public Footer(){
        setClassName("footer");
        setWidthFull();
        getStyle().set("background-color", "rgba(252, 245, 255, 0.05)");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        HorizontalLayout mainLayout = new HorizontalLayout();
        for (int j = 0; j < 3; j++) {
            VerticalLayout firstC = new VerticalLayout();
            firstC.add(new H4("Ask"));
            for (int i = 0; i < 5; i++) {
                Anchor a = new Anchor("", "Terms of Service");
                firstC.add(a);
            }
            mainLayout.add(firstC);
        }

        Span span = new Span("2022 UCLV");
        span.getStyle().set("font-size", "0.7em");
        mainLayout.setWidth("100%");
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        mainLayout.setAlignItems(Alignment.CENTER);
        add(mainLayout);
        add(span);
    }
}
