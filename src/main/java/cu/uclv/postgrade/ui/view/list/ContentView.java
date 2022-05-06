package cu.uclv.postgrade.ui.view.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cu.uclv.postgrade.backend.entity.Person;
import cu.uclv.postgrade.backend.service.PersonService;
import cu.uclv.postgrade.ui.MainLayout;
import cu.uclv.postgrade.ui.elements.Footer;
import cu.uclv.postgrade.ui.elements.PersonDetailsFormLayout;

@Route(value = "list", layout = MainLayout.class)
@PageTitle("Postgrado | UCLV")
public class ContentView extends VerticalLayout {

    //Attributes
    private final PersonService personService;
    private final Grid<Person> grid = new Grid<>(Person.class);
    private final TextField nameFilter = new TextField();
    //private final TextField idFilter = new TextField();

    //Constructor
    public ContentView(PersonService personService) {
        this.personService = personService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        Footer footer = new Footer();
        add(getToolbar(), grid, footer);
        updateListByName();
    }

    //Methods
    //Toolbar with the filter and the button for add students
    private Component getToolbar() {
        nameFilter.setPlaceholder("Buscar...");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        nameFilter.setValueChangeMode(ValueChangeMode.LAZY);
        nameFilter.addValueChangeListener(e -> updateListByName());
        HorizontalLayout toolbar = new HorizontalLayout(nameFilter);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    //configure the grid
    private void configureGrid() {
        grid.addClassName("contact-grid");
        grid.setColumns("name", "lastName", "email", "idNumber");
        grid.getColumnByKey("name").setHeader("Nombre");
        grid.getColumnByKey("lastName").setHeader("Apellido");
        grid.getColumnByKey("email").setHeader("Correo Electronico");
        grid.getColumnByKey("idNumber").setHeader("Carnet de Identidad");
        grid.addColumn(createToggleDetailsRenderer(grid));
        grid.setDetailsVisibleOnClick(false);
        grid.setItemDetailsRenderer(createPersonDetailsRenderer());
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(e -> editPerson(e.getValue()));
        grid.appendFooterRow();
        Button add_Person = new Button("Anadir Persona");
        add_Person.setIcon(new Icon(VaadinIcon.PLUS));
        add_Person.addClickListener(click -> {
            grid.asSingleSelect().clear();
            editPerson(new Person());
        });
        grid.getFooterRows().get(0).getCells().get(0).setComponent(add_Person);
//        grid.appendHeaderRow();
//        grid.getHeaderRows().get(1).getCells().get(0).setComponent(new TextField());
//        grid.getHeaderRows().get(1).getCells().get(1).setComponent(new TextField());
//        grid.getHeaderRows().get(1).getCells().get(2).setComponent(new TextField());
//        grid.getHeaderRows().get(1).getCells().get(3).setComponent(new TextField());
    }

    private static ComponentRenderer<PersonDetailsFormLayout, Person> createPersonDetailsRenderer() {
        return new ComponentRenderer<>(PersonDetailsFormLayout::new,
                PersonDetailsFormLayout::setPerson);
    }

    private static TemplateRenderer<Person> createToggleDetailsRenderer(Grid<Person> grid){
        return TemplateRenderer.<Person>of("<vaadin-button theme=\"tertiary\" on-click=\"handleClick\">Detalles</vaadin-button>")
                .withEventHandler("handleClick", Person -> grid.setDetailsVisible(Person, !grid.isDetailsVisible(Person)));
    }
    private void editPerson(Person value) {
        if (value != null){
            UI.getCurrent().navigate("form");
            PersonForm.setPerson(value);
        }else PersonForm.setPerson(new Person());
    }

    private void updateListByName() {
        grid.setItems(personService.findAllPeople(nameFilter.getValue()));
    }

}
