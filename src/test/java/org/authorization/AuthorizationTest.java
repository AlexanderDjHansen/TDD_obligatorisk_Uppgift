package org.authorization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorizationTest {

    private Authorization authorization;


    @BeforeEach
    public void setUp(){
        authorization = new Authorization();
    }

    @ParameterizedTest
    @CsvSource (value = {"anna, losen", "berit, 123456", "kalle, password"}) // Given
    public void usernameAndPasswordAuthorization(String username, String password) throws IllegalAccessException {

        // When
        boolean result = authorization.authorize(username, password);

        //Then
        assertTrue(result);
    }

    @ParameterizedTest
    @CsvSource (value = {"anna, losen, YW5uYWxvc2Vu", "berit, 123456, YmVyaXQxMjM0NTY=", "kalle, password, a2FsbGVwYXNzd29yZA=="}) // Given
    public void base64Authorization(String username, String password, String expected) throws IllegalAccessException {


        // When
        String result = authorization.getAuthorizationToken(username, password);

        //Then
        assertEquals(expected, result);


    }


}
