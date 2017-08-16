package org.yava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yava.dao.UserDao;
import org.yava.domain.User;
import org.yava.service.UserCrudService;

@Configuration
public class GlobalRestieConfiguration {

    @Bean
    public User getUser() {
        User result = new User( 1L );
        result.setFirstName( "Yaroslav" );
        result.setLastName( "Boychuk" );
        result.setUsername( "username" );
        result.setEmail( "yava.ua@gmail.com" );
        return result;
    }

    @Bean
    public UserDao getUserDao( User user ) {
        final UserDao userDao = new UserDao();
            userDao.create( user );
        return userDao;
    }

    @Bean
    public UserCrudService getUserService( UserDao userDao ) {
        return new UserCrudService( userDao );
    }

}
