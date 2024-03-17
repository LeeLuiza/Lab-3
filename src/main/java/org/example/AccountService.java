package org.example;
import Model.AccountUser;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static Map<String, AccountUser> loginToPtofile = new HashMap<>();
    private static Map<String, AccountUser> idSessionToProfile = new HashMap<>();

    public static void addNewUser(AccountUser user){
        loginToPtofile.put(user.getLogin(), user);
    }

    public static AccountUser getUserByLogin(String login){
        return loginToPtofile.get(login);
    }

    public static void deleteSession(String idSession){
        idSessionToProfile.remove(idSession);
    }
}
