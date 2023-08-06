package utils;

import interfaces.Validation;
import models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements Validation {
    private final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$";
    private final Pattern pattern;
    private Matcher matcher;

    public Validator() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    @Override
    public boolean validate(User user) {
        matcher = pattern.matcher(user.getPassword());

        return matcher.matches();
    }
}
