package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;

public class UserSession implements IUserSession{

    private static final UserSession userSession = new UserSession();
 private final User user;
    public UserSession() {
        this.user = new User();
    }


    @Override
    public UserSession getInstance() {
        return userSession;
    }

    @Override
    public User getLoggedUserNull() {
        return null;
    }
    @Override
    public User getLoggedUser() {
        return user;
    }
}
