package org.authorization;

import java.util.HashMap;

public class Authorization {

    public boolean authorize(String username, String password) {
        HashMap<String, String> users = new HashMap<>();
        users.put("anna", "losen");
        users.put("berit", "123456");
        users.put("kalle", "password");

        return users.containsKey(username) && users.containsValue(password);
    }
}
