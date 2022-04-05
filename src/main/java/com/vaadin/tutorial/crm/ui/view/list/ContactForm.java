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
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.PropertyId;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.tutorial.crm.backend.entity.Student;
import com.vaadin.tutorial.crm.backend.entity.University;
import com.vaadin.tutorial.crm.backend.service.StudentService;
import com.vaadin.tutorial.crm.ui.MainLayout;

import java.util.List;

@PageTitle("Form | UCLV")
@Route(value = "form", layout = MainLayout.class)
public class ContactForm extends FormLayout {
    //attributes
    @PropertyId("firstName")
    private static final TextField fNameTextField = new TextField("First Name");
    @PropertyId("lastName")
    private static final TextField lNameTextField = new TextField("Last Name");
    @PropertyId("university")
    private static final ComboBox<University> universityField = new ComboBox<>("University");
    @PropertyId("email")
    private static final EmailField emailField = new EmailField("Email");
    private final Button buttonSave = new Button("Save");
    private final Button buttonCancel = new Button("Cancel");
    private final Button buttonDelete = new Button("Delete");
    private static final Binder<Student> binder = new Binder<>(Student.class);
    private final StudentService studentService;
    private static Student student = new Student();

    public ContactForm(StudentService studentService){
        this.studentService = studentService;
        add(new H4("Information:"));
        add(new H4(""));
        addClassName("contact-form");
        configureComboBox(studentService.findAllUniversities());
        add(fNameTextField);
        add(lNameTextField);
        add(emailField);
        add(universityField);
        add(createButtonsLayout());
        validate();
        addListener(ContactForm.SaveEvent.class, this::saveStudent);
        addListener(ContactForm.DeleteEvent.class, this::deleteContact);
        addListener(ContactForm.CloseEvent.class, e -> UI.getCurrent().navigate("list"));
    }

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student st){
        student = st;
        binder.readBean(student);
    }

    private void configureComboBox(List<University> universities){
        universityField.setItems(universities);
        universityField.setItemLabelGenerator(university -> university.isPersisted()? university.getName() : "");
    }
    private void configureAccordion(){
        FormLayout detailsFormLayout = new FormLayout();
        TextField address = new TextField("Address");
        TextField phone = new TextField("Phone Number");
        DatePicker birth = new DatePicker("Birth");
        //binder.forField(phone).withValidator(number -> validateNumber(number), "Only Numbers").bind(Person::getId,Person::setId);
        detailsFormLayout.add(address, phone, birth);
        AccordionPanel accordionPanel = new AccordionPanel("More Details", detailsFormLayout);
        accordionPanel.addOpenedChangeListener(e -> {
        });
    }

    private boolean validateNumber(String s){
        for (int i = 0; i < s.length(); i++){
            if (!Character.isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }
    private static void validate(){
        binder.forField(fNameTextField).bind(Student::getFirstName, Student::setFirstName);
        binder.forField(lNameTextField).bind(Student::getLastName, Student::setLastName);
        binder.forField(emailField).bind(Student::getEmail, Student::setEmail);//Buscar como usar el validador de email
        binder.forField(universityField).bind(Student::getUniversity, Student::setUniversity);
    }

    private boolean validateName(String s){
        for (int i = 0; i < s.length(); i++){
            if (!Character.isLetter(s.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private HorizontalLayout createButtonsLayout(){
        buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonDelete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        buttonSave.addClickListener(click -> validateAndSave());
        buttonDelete.addClickListener(click -> fireEvent(new DeleteEvent(this, student)));
        buttonCancel.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> buttonSave.setEnabled(binder.isValid()));
        HorizontalLayout layout = new HorizontalLayout(buttonSave, buttonDelete, buttonCancel);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setMargin(true);
        return layout;
    }

    private void validateAndSave(){
        try {
            binder.writeBean(student);
            fireEvent(new SaveEvent(this, student));
        }catch (ValidationException e){
            //System.out.println(e.getBeanValidationErrors());
            System.out.println(e.getMessage());
        }
    }

    public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {
        private Student student;

        protected ContactFormEvent(ContactForm source, Student student) {
            super(source, false);
            this.student = student;
        }
        public Student getStudent() {
            return student;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(ContactForm source, Student student) {
            super(source, student);
        }
    }

    private void saveStudent(SaveEvent e){
        studentService.save(e.getStudent());
        success("Saved Successfully");
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
        DeleteEvent(ContactForm source, Student student) {
            super(source, student);

        }
    }

    private void deleteContact(ContactForm.DeleteEvent event) {
        studentService.delete(event.getStudent());
        success("Delete Successfully");
        UI.getCurrent().navigate("list");
    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(ContactForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(
            Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}
