package am.itspace.overnight.controller;

import am.itspace.overnight.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFoundException(UserNotFoundException ex) {
        return "error404";
    }
}
