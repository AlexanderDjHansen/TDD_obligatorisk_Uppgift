package org.authorization;


import java.util.HashMap;


public class Authorization {


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
}
