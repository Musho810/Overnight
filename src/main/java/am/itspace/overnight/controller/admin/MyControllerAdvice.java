package am.itspace.overnight.controller.admin;

import am.itspace.overnight.entity.User;
import am.itspace.overnight.repository.UserRepository;
import am.itspace.overnight.security.CurrentUser;
import liquibase.pro.packaged.C;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyControllerAdvice {

    @ModelAttribute(name = "currentUser")
    public User currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            return currentUser.getUser();
        }
        return null;
    }
    @ModelAttribute(name = "currentUrl")
    public String currenyUrl(HttpServletRequest request){
        return request.getRequestURI();

    }
}
