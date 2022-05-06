package com.vaadin.tutorial.crm.ui.view.list;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.DateRangeValidator;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.IntegerRangeValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.tutorial.crm.backend.entity.Person;
import com.vaadin.tutorial.crm.backend.entity.University;
import com.vaadin.tutorial.crm.backend.service.PersonService;
import com.vaadin.tutorial.crm.backend.service.UniversityService;
import com.vaadin.tutorial.crm.backend.validation.Validation;
import com.vaadin.tutorial.crm.ui.MainLayout;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Formulario | UCLV")
@Route(value = "form", layout = MainLayout.class)
public class PersonForm extends FormLayout {
    //attributes
    @PropertyId("name")
    private static final TextField fNameTextField = new TextField("Nombre");
    @PropertyId("lastName")
    private static final TextField lNameTextField = new TextField("Apellido");
    @PropertyId("idNumber")
    private static final TextField idNumberTextField = new TextField("Carnet de Identidad");
    @PropertyId("email")
    private static final EmailField emailField = new EmailField("Correo Electronico");
    @PropertyId("address")
    private static final TextField address = new TextField("Direccion");
    @PropertyId("phone")
    private static final TextField phone = new TextField("Numero de Telefono");
    @PropertyId("birth_date")
    private static final DatePicker birth = new DatePicker("Fecha de Nacimiento");
    @PropertyId("sex")
    private static final ComboBox<String> sex = new ComboBox<>("Sexo");
    @PropertyId("years_experience")
    private static final IntegerField years_experience = new IntegerField("Anos de Experiencia");
    @PropertyId("graduation_year")
    private static final IntegerField graduation_year = new IntegerField("Ano de Graduacion");
    @PropertyId("college_degree")
    private static final ComboBox<String> college_degree = new ComboBox<>("Grado Universitario");
    @PropertyId("collegeGraduate")
    private static final ComboBox<University> collegeGraduate = new ComboBox<>("Universidad");
    @PropertyId("name")
    private static final TextField name = new TextField("Nombre");
    @PropertyId("acronym")
    private static final TextField acronym = new TextField("Acronimo");
    private final Button buttonSave = new Button("Guardar");
    private final Button buttonCancel = new Button("Cancelar");
    private final Button buttonDelete = new Button("Eliminar");
    private final Button buttonAddUniversity = new Button("Anadir");
    private final Dialog dialogAddUniversity = new Dialog();
    private static final Binder<Person> binder = new Binder<>(Person.class);
    private final PersonService personService;
    private final UniversityService universityService;
    private static Person person = new Person();
    private final University university = new University();
    private static final Binder<University> universityBinder = new Binder<>(University.class);

    //Constructor
    public PersonForm(PersonService personService, UniversityService universityService){
        this.personService = personService;
        this.universityService = universityService;
        add(new H4("Informacion:"));
        addClassName("contact-form");
        setResponsiveSteps(new ResponsiveStep("0", 1));
        FormLayout layout = new FormLayout(fNameTextField, lNameTextField, emailField, idNumberTextField);
        layout.setResponsiveSteps(new ResponsiveStep("0", 2));
        add(layout);
        FormLayout detailsFormLayout = new FormLayout();
        detailsFormLayout.add(address, phone, birth, sex);
        detailsFormLayout.add(years_experience, college_degree, configureCollegeGraduate(), graduation_year);
        detailsFormLayout.setResponsiveSteps(new ResponsiveStep("80", 2));
        detailsFormLayout.setWidthFull();
        AccordionPanel accordionPanel = new AccordionPanel("Mas Detalles", detailsFormLayout);
        add(accordionPanel);
        configure(universityService.findAll());
        add(createButtonsLayout());
        validate();
        addListener(PersonForm.SaveEvent.class, this::savePerson);
        addListener(PersonForm.DeleteEvent.class, this::deleteContact);
        addListener(PersonForm.CloseEvent.class, e -> UI.getCurrent().navigate("list"));
    }

    //Methods

    public static Person getPerson() {
        return person;
    }
    //set the Person and read the bean
    public static void setPerson(Person st){
        person = st;
        binder.readBean(person);
    }

    //Configure the field of College Graduate
    private HorizontalLayout configureCollegeGraduate(){
        HorizontalLayout layout = new HorizontalLayout(collegeGraduate, buttonAddUniversity);
        layout.expand(collegeGraduate);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        dialogAddUniversity.add(createDialogLayout());
        buttonAddUniversity.addClickListener(click -> dialogAddUniversity.open());
        return layout;
    }

    //Create the Dialog for add University
    private VerticalLayout createDialogLayout(){
        H2 head = new H2("Anadir Universidad");
        head.getStyle().set("font-size", "1.5em").set("font-weight", "bold");
        name.setPlaceholder("Universidad Central de Las Villas");
        acronym.setPlaceholder("UCLV");
        VerticalLayout fieldLayout = new VerticalLayout(name, acronym);
        fieldLayout.setPadding(false);
        fieldLayout.setSpacing(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        Button cancelButton = new Button("Cancelar", e -> dialogAddUniversity.close());
        cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        Button saveButton = new Button("Guardar", e -> {
            try {
                universityBinder.writeBean(university);
                universityService.save(university);
            }
            catch (ValidationException exc){
                System.out.print("Error Val");
            }

            collegeGraduate.setItems(universityService.findAll());

            if (universityBinder.isValid())
                dialogAddUniversity.close();
        });
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton, saveButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        VerticalLayout dialogLayout = new VerticalLayout(head, fieldLayout, buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "400px").set("max-width", "100%");

        return dialogLayout;
    }

    private void configure(List<University> universities){
        ArrayList<String> a = new ArrayList<>();
        a.add("F");
        a.add("M");
        a.add("Otro");
        sex.setItems(a);
        a = new ArrayList<>();
        a.add("Lic.");
        a.add("Ingeniero");
        a.add("Master");
        a.add("Doctor");
        college_degree.setItems(a);
        graduation_year.setMax(LocalDate.now().getYear());
        years_experience.setMax(100);
        collegeGraduate.setItems(universities);
        collegeGraduate.setItemLabelGenerator(university -> university.isPersisted()? university.getName() : "");
        phone.setPrefixComponent(new Icon(VaadinIcon.PHONE));
        emailField.setPrefixComponent(new Icon(VaadinIcon.ENVELOPE));
    }

    private static void validate(){
        //validation for name
        binder.forField(fNameTextField).asRequired("Todas las Personas deben de tener un Nombre.").withValidator(Validation::validateName, "Incorrect Name").bind(Person::getName, Person::setName);
        //validation for last name
        binder.forField(lNameTextField).asRequired("Todas las Personas deben de tener un Apellido.").withValidator(Validation::validateName, "Invalid Last Name").bind(Person::getLastName, Person::setLastName);
        //validation for email
        binder.forField(emailField).asRequired("Todas las Personas deben de tener un Email.").withValidator(new EmailValidator("Invalid Email Address")).bind(Person::getEmail, Person::setEmail);//Buscar como usar el validador de email
        //validation for id
        binder.forField(idNumberTextField).asRequired().withValidator(number -> Validation.validateNumber(number) && number.length() == 11, "Numero de identificacion incorrecto").bind(Person::getIdNumber, Person::setIdNumber);
        //validation for address
        binder.forField(address).bind(Person::getAddress, Person::setAddress);
        //validation for phone
        binder.forField(phone).withValidator(Validation::validateNumber, "Numero Incorrecto.").bind(Person::getPhone, Person::setPhone);
        //validation for birth
        binder.forField(birth).withValidator(new DateRangeValidator("Por favor introduzca una fecha correcta.", LocalDate.of(1920, 1, 1), LocalDate.now().minusYears(15))).bind(Person::getBirth_date, Person::setBirth_date);
        //validation for sex
        binder.forField(sex).bind(Person::getSex, Person::setSex);
        //validation for college degree
        binder.forField(college_degree).bind(Person::getCollege_degree, Person::setCollege_degree);
        //validation for graduation year .withValidator(year -> year <= LocalDate.now().getYear(), "Please Select a value that is no more than " + LocalDate.now().getYear())
        binder.forField(graduation_year).withValidator(new IntegerRangeValidator("Por favor introduzca un ano correcto.", 1900, LocalDate.now().getYear())).bind(Person::getGraduation_year, Person::setGraduation_year);
        //validation for years experience
        binder.forField(years_experience).withValidator(new IntegerRangeValidator("Por favor introduzca anos de experiencia correcto. ", 0, 60)).bind(Person::getYears_experience, Person::setYears_experience);

        binder.forField(collegeGraduate).bind(Person::getCollegeGraduate, Person::setCollegeGraduate);

        universityBinder.forField(name).asRequired("La Universidad debe tener un nombre.").bind(University::getName, University::setName);

        universityBinder.forField(acronym).asRequired().withValidator(new StringLengthValidator("El Acronimo es muy largo.", 1, 10)).bind(University::getAcronym, University::setAcronym);
    }

    private HorizontalLayout createButtonsLayout(){
        buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonSave.addClickListener(click -> validateAndSave());
        buttonDelete.addClickListener(click -> fireEvent(new DeleteEvent(this, person)));
        buttonCancel.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> buttonSave.setEnabled(binder.isValid()));
        HorizontalLayout layout = new HorizontalLayout(buttonSave, buttonDelete, buttonCancel);
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setWidthFull();
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        return layout;
    }

    private void validateAndSave(){
        try {
            binder.writeBean(person);
            fireEvent(new SaveEvent(this, person));
        }catch (ValidationException e){
            //System.out.println(e.getBeanValidationErrors());
            System.out.println(e.getMessage());
        }
    }

    public static abstract class ContactFormEvent extends ComponentEvent<PersonForm> {
        private final Person person;

        protected ContactFormEvent(PersonForm source, Person person) {
            super(source, false);
            this.person = person;
        }
        public Person getPerson() {
            return person;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(PersonForm source, Person person) {
            super(source, person);
        }
    }

    private void savePerson(SaveEvent e){
        personService.save(e.getPerson());
        success("Guardado Satisfactoriamente.");
        UI.getCurrent().navigate("list");
    }

    public static Notification success(String text){
        Notification notification = Notification.show("");
        notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);
        Icon icon = VaadinIcon.CHECK_CIRCLE.create();
        Text span = new Text(text);
        Div info = new Div(span);
        HorizontalLayout layout = new HorizontalLayout(icon, info);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        notification.add(layout);
        return notification;
    }
    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(PersonForm source, Person person) {
            super(source, person);

        }
    }

    private void deleteContact(PersonForm.DeleteEvent event) {
        personService.delete(event.getPerson());
        success("Delete Successfully");
        UI.getCurrent().navigate("list");
    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(PersonForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(
            Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}
