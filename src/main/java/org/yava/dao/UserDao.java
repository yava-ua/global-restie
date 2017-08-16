package org.yava.dao;

import org.yava.domain.User;
import org.yava.exceptions.RestieException;

import javax.validation.constraints.NotNull;
import java.util.concurrent.ConcurrentHashMap;

public class UserDao implements Dao<User> {

    private ConcurrentHashMap<Long, User> repository = new ConcurrentHashMap<>();

    @Override
    public User create( @NotNull User entity ) {
        return this.repository.putIfAbsent( entity.getId(), entity );
    }

    @Override
    public User read( Long id ) {
        return this.repository.get( id );
    }

    @Override
    public User update( User entity ) {
        return this.repository.computeIfPresent( entity.getId(), ( key, value ) -> entity );
    }

    @Override
    public User delete( Long id ) {
        return this.repository.remove( id );
    }
}
