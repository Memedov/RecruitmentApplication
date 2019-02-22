package recruitment.presentation.recr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import recruitment.application.RecruiterService;
import recruitment.domain.PersonDTO;

/**
 * Handles all HTTP requests to context root.
 */
@Controller
@Scope("session")
public class RecruiterController {
    static final String DEFAULT_PAGE_URL = "/";
    static final String REGISTER_PAGE_URL = "register";
    static final String LOGIN_PAGE_URL = "login";
    static final String APPLY_PAGE_URL = "apply";
    static final String LIST_APPLICATIONS_PAGE_URL = "list-applications";

    private static final String CURRENT_REG_OBJ_NAME = "currentRegistration";
    private static final String REGISTER_FORM_OBJ_NAME = "registerForm";
    private static final String LOGIN_FORM_OBJ_NAME = "loginForm";


    @Autowired
    private RecruiterService service;
    //private PersonDTO currentRole;

    /**
     * No page is specified, redirected to the login page.
     *
     * @return A response that redirects the browser to the login page.
     */
    @GetMapping(DEFAULT_PAGE_URL)
    public String showDefaultView() { return "redirect:" + LOGIN_PAGE_URL; }

    /**
     * A GET request for the registration page.
     *
     * @param registerForm Used in the register form.
     * @return The registration page.
     */
    @GetMapping("/" + REGISTER_PAGE_URL)
    public String showRegisterPageView(RegisterForm registerForm) { return REGISTER_PAGE_URL; }

    /**
     * A GET request for the login page.
     * @param model Model objects used by the login page.
     * @return The login page.
     */
    @GetMapping("/" + LOGIN_PAGE_URL)
    public String showLoginPageView(Model model) {
        return showLoginPage(model, new LoginForm()); }

    private String showLoginPage(Model model, LoginForm loginForm) {
        if(loginForm != null) {
            model.addAttribute(LOGIN_FORM_OBJ_NAME, loginForm);
        }
        return LOGIN_PAGE_URL;
    }
    /**
     * A GET request for the apply page.
     *
     * @param model Model objects used by the apply page.
     * @return the apply page.
     */
    @GetMapping("/" + APPLY_PAGE_URL)
    public String showApplyPageView(Model model) {
        if(!existAuthenticatedUser(model)){
            return LOGIN_PAGE_URL;
        }
        return APPLY_PAGE_URL;
    }

    /**
     * a GET request for the ListApplications page.
     *
     * @param model Model objects used by the ListApplications page.
     * @return The ListApplications page.
     */
    @GetMapping("/" + LIST_APPLICATIONS_PAGE_URL)
    public String showListApplicationsPageView(Model model) {
        if(!existAuthenticatedUser(model)){
            return LOGIN_PAGE_URL;
        }
        return LIST_APPLICATIONS_PAGE_URL;
    }

    /**
     * The register form has been submitted.
     *
     * @param registerForm Content of the register form.
     * @param bindingResult Validation result for the register form.
     * @param model Model objects used by the register page.
     * @return The login page if successful registration, else the register page.
     */
    @PostMapping("/" + REGISTER_PAGE_URL)
    public String sendRegistration(@Valid RegisterForm registerForm, BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()) {

            if (!registerForm.getPassword().equals(registerForm.getConfirmPwd())) {
                bindingResult.rejectValue("confirmPwd", null, "Passwords do not match, try again.");

            } else {
                
                service.registerUser(registerForm.getFname(), registerForm.getLname(), registerForm.getEmail(),
                            registerForm.getSsn(), registerForm.getUsername(), registerForm.getPassword());

                LoginForm loginForm = new LoginForm();
                loginForm.setUsername(registerForm.getUsername());

                return showLoginPage(model, loginForm);
            }

        } else {

            if (service.checkUsername(registerForm.getUsername()) == true) {
                bindingResult.rejectValue("username", null, "There is already an account registered with that username");
            }
            if (service.checkEmail(registerForm.getEmail()) == true) {
                bindingResult.rejectValue("email", null, "There is already an account registered with that email");
            }
            if (service.checkSsn(registerForm.getSsn()) == true) {
                bindingResult.rejectValue("ssn", null, "There is already an account registered with that social security number");
            }
            if (!registerForm.getPassword().equals(registerForm.getConfirmPwd())) {
                bindingResult.rejectValue("confirmPwd", null, "Passwords do not match, try again.");
            }
        }

        return REGISTER_PAGE_URL;
    }

//    /**
//     *  The login form has been submitted.
//     *
//     * @param loginForm Content of the login form.
//     * @param bindingResult Validation result for the login form.
//     * @param model Model objects used by the login page.
//     * @return The appropriate page depending on role or login page if unsuccessful authentication.
//     */
//    @PostMapping("/" + LOGIN_PAGE_URL)
//    public String sendLogin(@Valid LoginForm loginForm, BindingResult bindingResult, Model model) {
//        if(!bindingResult.hasErrors()) {
//            //If an applicant logs in
//            if(service.authorize(loginForm.getUsername(), loginForm.getPassword()) == 2) {
//                return APPLY_PAGE_URL;
//            }
//            //If a recruiter logs in
//            else if(service.authorize(loginForm.getUsername(), loginForm.getPassword()) == 1) {
//                return LIST_APPLICATIONS_PAGE_URL;
//            }
//        }

        /*else {
            if (service.checkUsername(loginForm.getUsername()) == false) {
                bindingResult.rejectValue("username", null, "Username or password is incorrect.");
            }
        }
       if (bindingResult.hasErrors()) {
            model.addAttribute(CURRENT_REG_OBJ_NAME, new RegisterForm());
            return LOGIN_PAGE_URL;
        }

       if (currentConv == null) {
            model.addAttribute(ExceptionHandlers.ERROR_TYPE_KEY, ExceptionHandlers.NO_CONVERSION_FOUND_FOR_UPDATE);
            model.addAttribute(ExceptionHandlers.ERROR_INFO_KEY, ExceptionHandlers.NO_CONVERSION_FOUND_FOR_UPDATE_INFO);
            return ExceptionHandlers.ERROR_PAGE_URL;
        }*/
//        return LOGIN_PAGE_URL;
//    }

    private boolean existAuthenticatedUser(Model model){
        PersonDTO person = service.getAuthenticatedUsername();
        if(person == null){
            return false;
        }
        model.addAttribute("username", person.getName());
        return true;
    }
}
