package org.yava.endpoint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.yava.domain.User;
import org.yava.exceptions.RestieException;
import org.yava.service.UserCrudService;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith( SpringRunner.class )
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class UserRestControllerTest {

    private static String TEST_URL = "/api/users/";

    private static class Wrapper extends ResponseResult<User> {
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserCrudService userCrudService;

    private User testUser = new User( 100L );

    @Before
    public void setUp() throws Exception {
        testUser.setEmail( "test@test.com" );
        testUser.setFirstName( "test" );
        testUser.setUsername( "test" );
        testUser.setUsername( "test" );
    }

    @Test
    public void createFailed() throws Exception {
        given( this.userCrudService.create( testUser ) ).willThrow( new RestieException( "Failed" ) );
        Wrapper result = restTemplate.postForObject( TEST_URL, testUser, Wrapper.class );
        assertFalse( "Unsuccessful call", result.isSuccess() );
    }

    @Test
    public void createSucceeded() throws Exception {
        given( this.userCrudService.create( testUser ) ).willReturn( testUser );
        Wrapper result = restTemplate.postForObject( TEST_URL, testUser, Wrapper.class );
        assertTrue( "Unsuccessful call", result.isSuccess() );
    }

    @Test
    public void read() throws Exception {
        given( this.userCrudService.read( 0L ) ).willReturn( testUser );
        Wrapper result = restTemplate.getForObject( TEST_URL + "0", Wrapper.class );

        assertTrue( "Successful call", result.isSuccess() );
        assertNotNull( "User not empty", result.getData() );
        assertEquals( "Username", "test", result.getData().getUsername() );
    }

    @Test
    public void update() throws Exception {
        User newUser = new User( 2L );
        newUser.setEmail( "other@test.com" );
        newUser.setFirstName( "other" );
        newUser.setUsername( "other" );

        given( this.userCrudService.update( newUser ) ).willReturn( newUser );
        Wrapper result = restTemplate.exchange( TEST_URL, HttpMethod.PUT, new HttpEntity<>( newUser, new HttpHeaders() ), Wrapper.class ).getBody();

        assertTrue( "Successful call", result.isSuccess() );
        assertNotNull( "User not empty", result.getData() );
        assertEquals( "Username", "other", result.getData().getUsername() );
    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void handleException() throws Exception {

    }

}