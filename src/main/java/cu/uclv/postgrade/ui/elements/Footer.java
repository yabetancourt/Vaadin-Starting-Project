package cu.uclv.postgrade.ui.elements;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import static com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode.CENTER;

public class Footer extends VerticalLayout{

    //Constructor
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

        H5 studies = new H5("Estudios");
        Icon stud = new Icon(VaadinIcon.INSTITUTION);
        stud.setSize("1.0em");
        HorizontalLayout horizontalLayout = new HorizontalLayout(studies, stud);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        Anchor undergraduate = new Anchor("", "Pregrado");
        Anchor research = new Anchor("", "Centros de Investigacion");
        Anchor graduate = new Anchor("", "Postgrado");
        Anchor projects = new Anchor("", "Proyectos");
        Anchor project = new Anchor("", "Proyectos");
        H5 follow = new H5("Siguenos");
        Icon fol = new Icon(VaadinIcon.SHARE);
        fol.setSize("1.0em");
        HorizontalLayout layoutFollow = new HorizontalLayout(follow, fol);
        layoutFollow.setAlignItems(Alignment.BASELINE);
        Icon iconFace = new Icon(VaadinIcon.FACEBOOK);
        iconFace.setColor("#3b5998");
        Anchor face = new Anchor("https://www.facebook.com", iconFace);
        Icon iconGoogle = new Icon(VaadinIcon.GOOGLE_PLUS);
        iconGoogle.setColor("#ea4335");
        Anchor google = new Anchor("https://www.facebook.com", iconGoogle);
        Icon iconTwitter = new Icon(VaadinIcon.TWITTER);
        iconTwitter.setColor("#55acee");
        Anchor twitter = new Anchor("https://www.facebook.com", iconTwitter);
        Icon iconTelegram = new Icon(VaadinIcon.PAPERPLANE);
        iconTelegram.setColor("#0088cc");
        Anchor telegram = new Anchor("https://www.facebook.com", iconTelegram);
        HorizontalLayout net = new HorizontalLayout(face, google, twitter, telegram);
        net.setAlignItems(Alignment.BASELINE);
        studiesLayout.add(horizontalLayout, undergraduate, research, graduate, projects, project, layoutFollow, net);
        mainLayout.add(studiesLayout);

        //Second Column
        VerticalLayout links = new VerticalLayout();
        H5 linksInter = new H5("Enlaces de Interes");
        Icon linkIcon = new Icon(VaadinIcon.LINK);
        linkIcon.setSize("1.0em");
        horizontalLayout = new HorizontalLayout(linksInter, linkIcon);
        horizontalLayout.setAlignItems(Alignment.BASELINE);
        Anchor ministry = new Anchor("", "Ministerio de Educacion Superior");
        Anchor institute = new Anchor("", "Instituto");
        Anchor portal = new Anchor("", "Portal");
        Anchor red = new Anchor("", "Red de ciencia");
        H5 email = new H5("Correo Electronico");
        Icon em = new Icon(VaadinIcon.ENVELOPE_O);
        em.setSize("1.0em");
        HorizontalLayout layout = new HorizontalLayout(email, em);
        layout.setAlignItems(Alignment.BASELINE);
        Anchor contactEmail = new Anchor("","contacto@uclv.edu.cu");
        links.add(horizontalLayout, ministry, institute, portal, red, layout, contactEmail);
        mainLayout.add(links);

        //Third Column
        VerticalLayout contactLayout = new VerticalLayout();
        H5 contact = new H5("Contacto");
        Icon iconCont = new Icon(VaadinIcon.PHONE);
        iconCont.setSize("1.0em");
        HorizontalLayout lay = new HorizontalLayout(contact, iconCont);
        lay.setAlignItems(Alignment.BASELINE);
        Anchor address = new Anchor("", "Direccion");
        Span textAddress = new Span("Carretera a Camajuani km. 5 and 1/2. Santa Clara. Villa Clara. Cuba");
        contactLayout.add(lay, address, textAddress);

        H5 phones = new H5("Telefonos");
        Icon add = new Icon(VaadinIcon.PHONE_LANDLINE);
        add.setSize("1.0em");
        layout = new HorizontalLayout(phones, add);
        layout.setAlignItems(Alignment.BASELINE);
        Span rect = new Span("Rectorado");
        Anchor phone = new Anchor("", "(53)42281178");
        HorizontalLayout temp = new HorizontalLayout(phone, rect);
        contactLayout.add(layout, temp);

        rect = new Span("Rectorado");
        phone = new Anchor("", "(53)42281178");
        temp = new HorizontalLayout(phone, rect);
        contactLayout.add(temp);

        rect = new Span("Rectorado");
        phone = new Anchor("", "(53)42281178");
        temp = new HorizontalLayout(phone, rect);
        contactLayout.add(temp);


        // contactLayout.add(email, contactEmail);
        mainLayout.add(contactLayout);

        Span span = new Span("Copyright 2022 UCLV. Todos los derechos reservados");
        span.getStyle().set("font-size", "0.7em");
        mainLayout.setWidth("100%");
        mainLayout.setJustifyContentMode(JustifyContentMode.START);
        mainLayout.setAlignItems(Alignment.START);
        add(mainLayout);
        add(span);
    }
}
