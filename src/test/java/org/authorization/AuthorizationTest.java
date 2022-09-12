package org.authorization;

import org.junit.jupiter.api.BeforeEach;
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
    public void usernameAndPasswordAuthorization(String username, String password){

        // When
        boolean result = authorization.authorize(username, password);

        //Then
        assertFalse(result);
    }


}
