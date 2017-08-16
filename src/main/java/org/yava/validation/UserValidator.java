package org.yava.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.yava.domain.User;

public class UserValidator implements Validator {
    @Override
    public boolean supports( Class<?> aClass ) {
        return User.class.equals( aClass );
    }

    @Override
    public void validate( Object o, Errors errors ) {
        User user = (User) o;
        if( user.getId() == 0 ) {
            errors.rejectValue( "id", "Id must be set" );
        }
        if( StringUtils.isEmpty( user.getUsername() ) ) {
            errors.rejectValue( "username", "Username must be set" );
        }
    }
}
