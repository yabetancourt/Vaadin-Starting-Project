package cu.uclv.postgrade.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import cu.uclv.postgrade.ui.view.list.ContentView;

@PreserveOnRefresh
@Theme(value = Lumo.class, variant = Lumo.DARK)
@CssImport("./styles/shared-styles.css")
@PWA(name = "Postgrado UCLV",
        shortName = "PostUCLV",
        offlineResources = {
            "./styles/offline.css",
            "./images/offline.png",
            "./offline.html"
        }
)
public class MainLayout extends AppLayout{

    public MainLayout() {

        createHeader();
        createDrawer();

    }

    private void createHeader() {
        Icon icon = new Icon(VaadinIcon.ACADEMY_CAP);
        icon.setSize("1.25em");
        H1 text = new H1("Postgrado UCLV");
        HorizontalLayout logo = new HorizontalLayout(text, icon);
        text.addClassName("logo");
        Avatar user = new Avatar();
        user.setName("Yadier Betancourt");
        user.setWidth("1.8em");
        user.setHeight("1.8em");
        Anchor a = new Anchor("login", "Log out");
        a.getStyle().set("padding-right", "15px");
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), icon, logo, user, a);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.addClassName("header");
        addToNavbar(header);
    }

    private void createDrawer() {
        Icon iconList = new Icon(VaadinIcon.LIST);
        iconList.setSize("1.0em");
        iconList.setColor("rgb(124, 42, 239)");
        RouterLink listLink = new RouterLink("Personas", ContentView.class);
        HorizontalLayout listLayout = new HorizontalLayout(iconList, listLink);
        listLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());


        VerticalLayout layout = new VerticalLayout(listLayout);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        addToDrawer(layout);
    }

}
