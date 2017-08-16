package org.yava.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yava.domain.User;
import org.yava.service.UserCrudService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping( value = "/api/users/" )
public class UserRestController {

    private final UserCrudService userCrudService;

    @Autowired
    public UserRestController( UserCrudService userCrudService ) {this.userCrudService = userCrudService;}

    @RequestMapping( method = RequestMethod.POST )
    public ResponseResult<User> create( @RequestBody User user ) {
        return ResponseResult.success( userCrudService.create( user ) );
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public ResponseResult<User> read( @PathVariable( name = "id" ) Long id ) {
        return ResponseResult.success( userCrudService.read( id ) );
    }

    @RequestMapping( method = RequestMethod.PUT )
    public ResponseResult<User> update( @RequestBody User user ) {
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
        return ResponseResult.failure( exception );
    }

}
