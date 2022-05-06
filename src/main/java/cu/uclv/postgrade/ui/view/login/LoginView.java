package cu.uclv.postgrade.ui.view.login;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "login")
@PageTitle("Login | UCLV")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm answer = new LoginForm();

    public LoginView(){
        //addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        answer.setAction("login");
//        LoginI18n login = LoginI18n.createDefault();
//        LoginI18n.Form loginForm = login.getForm();
//        loginForm.setUsername("Usuario");
//        loginForm.setPassword("Contrasena");
//        loginForm.setForgotPassword("Olvido su contrasena?");
//        loginForm.setSubmit("Acceder");
//        login.setForm(loginForm);
//        LoginI18n.ErrorMessage loginError = login.getErrorMessage();
//        loginError.setTitle("Usuario o Contrasena incorrecta");
//        loginError.setMessage("Chequee que haya introducido el usuario y contrasena correcta y pruebe otra vez.");
//        login.setErrorMessage(loginError);
//        answer.setI18n(login);
        add(answer);
        setJustifyContentMode(JustifyContentMode.CENTER);

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
// inform the user about an authentication error
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            answer.setError(true);
        }
    }

}
