package org.yava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.yava.dao.UserDao;
import org.yava.domain.User;
import org.yava.exceptions.RestieException;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith( MockitoJUnitRunner.class )
public class UserCrudServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserCrudService userCrudService;

    private User testUser = new User( 0L );

    @Before
    public void setUp() throws Exception {
        testUser.setUsername( "test" );
    }

    @Test
    public void readSuccess() {
        given( userDao.read( 0L ) ).willReturn( testUser );
        User result = userCrudService.read( 0L );
        assertNotNull( "User exists", result );
        assertEquals( "Username", "test", result.getUsername() );
    }

    @Test
    public void readFail() {
        given( userDao.read( 1L ) ).willReturn( null );
        User result = userCrudService.read( 1L );
        assertNull( "User does not exists", result );
    }

    @Test
    public void createSuccess() {
        given( userDao.create( testUser ) ).willReturn( null );
        User result = userCrudService.create( testUser );
        assertNotNull( "User exists", result );
        assertEquals( "Username", "test", result.getUsername() );
    }

    @Test( expected = RestieException.class )
    public void createFail() {
        given( userDao.create( testUser ) ).willReturn( testUser );
        userCrudService.create( testUser );
    }

    @Test
    public void updateSuccess() {
        given( userDao.update( testUser ) ).willReturn( testUser );
        User result = userCrudService.update( testUser );
        assertNotNull( "User exists", result );
        assertEquals( "Username", "test", result.getUsername() );
    }

    @Test( expected = RestieException.class )
    public void updateFail() {
        given( userDao.update( testUser ) ).willReturn( null );
        userCrudService.update( testUser );
    }

    @Test
    public void deleteSuccess() {
        given( userDao.delete( testUser.getId() ) ).willReturn( testUser );
        User deleted = userCrudService.delete( testUser.getId() );
        assertNotNull( "User deleted", deleted );
    }

    @Test( expected = RestieException.class )
    public void deleteFailed() {
        given( userDao.delete( testUser.getId() ) ).willReturn( null );
        userCrudService.delete( testUser.getId() );
    }

}