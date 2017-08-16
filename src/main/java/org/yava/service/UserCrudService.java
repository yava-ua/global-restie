package org.yava.service;

import org.yava.dao.UserDao;
import org.yava.domain.User;
import org.yava.exceptions.RestieException;

public class UserCrudService {

    private UserDao dao;

    public UserCrudService( UserDao dao ) {
        this.dao = dao;
    }

    public User read( Long id ) {
        return dao.read( id );
    }

    public User create( User user ) {
        User result = dao.create( user );
        if( result != null ) {
            throw new RestieException( "User already exists" );
        }
        return user;
    }

    public User update( User user ) {
        User result = dao.update( user );
        if( result == null ) {
            throw new RestieException( "User does not exist" );
        }
        return result;
    }

    public User delete( Long id ) {
        User result = dao.delete( id );
        if( result == null ) {
            throw new RestieException( "User does not exist" );
        }
        return result;
    }
}
