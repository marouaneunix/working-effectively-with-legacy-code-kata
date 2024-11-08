package org.craftedsw.tripservicekata.user;

public class UserSession implements IsUserSession {

    private static final UserSession userSession = new UserSession();

    private UserSession() {
    }

    public static UserSession getInstance() {
        return userSession;
    }
    @Override
    public User getLoggedUser() {
       return null;
    }

}
