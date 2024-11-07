package org.craftedsw.tripservicekata.user;

public class UserSession implements IUserSession {

    public User getLoggedUser() {
       return null;
    }

    @Override
    public boolean isLoggedIn() {
        return getLoggedUser() != null;
    }
}
