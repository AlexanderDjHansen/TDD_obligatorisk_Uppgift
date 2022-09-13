package org.authorization;


import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;


public class Authorization {

    public static ArrayList<Users> userList = new ArrayList<>(List.of(
            new Users("anna", "losen"),
            new Users("berit", "123456"),
            new Users("kalle", "password")
    ));

    public static ArrayList<String> tokens = new ArrayList<>();


    public boolean authorize(String username, String password) throws IllegalAccessException {
        HashMap<String, String> users = new HashMap<>();
        users.put("anna", "losen");
        users.put("berit", "123456");
        users.put("kalle", "password");

        if (!users.containsKey(username) && users.containsValue(password)){
            throw new IllegalAccessException("Wrong username");
        }
        if (users.containsKey(username) && !users.containsValue(password)){
            throw new IllegalAccessException("Wrong password");
        }
        if (!users.containsKey(username) && !users.containsValue(password)){
            throw new IllegalAccessException("Wrong username and password");
        }

        return users.containsKey(username) && users.containsValue(password);
    }

    public String getAuthorizationToken(String username, String password) throws IllegalAccessException {

        for (Users users : userList){
            if (users.getUsername().equals(username) && users.getPassword().equals(password)){

                String userAsOriginal = users.getUsername() + users.getPassword();
                byte[] userAsBytes = userAsOriginal.getBytes();
                byte[] base64Bytes = Base64.getEncoder().encode(userAsBytes);
                tokens.add(new String(base64Bytes));
                return new String(base64Bytes);

            }
        }
        throw new IllegalAccessException("Username or password does not exists");


    }

    public boolean isUserValid(String token) {

        byte [] backAsBase64Bytes = token.getBytes();
        byte [] backAsBytes = Base64.getDecoder().decode(backAsBase64Bytes);
        String backAsString = new String(backAsBytes);

        for (Users users: userList){
            if ((users.getUsername() + users.getPassword()).equals(backAsString)){
                return false;
            }
        }
            return true;

    }
}
