package ua.netcracker.model.service.impl;

import org.springframework.stereotype.Service;
import ua.netcracker.model.service.ValidationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 1:34 07.05.2016
 *
 * @author Bersik (Serhii Kisilchuk)
 */
public class ValidationServiceImpl implements ValidationService {

    private Pattern emailPattern;
    private Pattern namePattern;
    private Pattern passwordPattern;

    public ValidationServiceImpl(String emailRegexp,String nameRegexp,
                                 String passwordRegexp) {
        emailPattern = compile(emailRegexp);
        namePattern= compile(nameRegexp);
        passwordPattern = compile(passwordRegexp);
    }

    private Pattern compile(String regexp){
        return Pattern.compile(regexp,Pattern.UNICODE_CHARACTER_CLASS);
    }

    @Override
    public boolean emailValidation(String email) {
        return validation(emailPattern, email);
    }

    @Override
    public boolean nameValidation(String name) {
        return validation(namePattern, name);
    }

    @Override
    public boolean passwordValidation(String password) {
        return validation(passwordPattern, password);
    }

    private boolean validation(Pattern pattern, String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
