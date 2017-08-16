package org.yava.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.yava.domain.User;
import org.yava.exceptions.RestieException;
import org.yava.service.UserCrudService;
import org.yava.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping( value = "/api/users/" )
public class UserRestController {

    private final UserCrudService userCrudService;

    private final UserValidator validator;

    @Autowired
    public UserRestController( UserCrudService userCrudService, UserValidator validator ) {
        this.userCrudService = userCrudService;
        this.validator = validator;
    }

    @RequestMapping( method = RequestMethod.POST )
    public ResponseResult<User> create( @Valid @RequestBody User user ) {
        return ResponseResult.success( userCrudService.create( user ) );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public ResponseResult<User> read( @PathVariable( name = "id" ) Long id ) {
        return ResponseResult.success( userCrudService.read( id ) );
    }

    @RequestMapping( method = RequestMethod.PUT )
    public ResponseResult<User> update( @Valid @RequestBody User user ) {
        return ResponseResult.success( userCrudService.update( user ) );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
    public ResponseResult<User> delete( @PathVariable( name = "id" ) Long id ) {
        return ResponseResult.success( userCrudService.delete( id ) );
    }

    @ExceptionHandler( {Exception.class} )
    @ResponseBody
    public ResponseResult<User> handleException( HttpServletRequest req, Exception exception ) {
        log.error( "Controller exception ", exception );
        RestieException restieException = new RestieException( exception.getMessage() );
        return ResponseResult.failure( restieException );
    }

    @InitBinder( "user" )
    public void setupBinder( WebDataBinder binder ) {
        binder.addValidators( validator );
    }
}
