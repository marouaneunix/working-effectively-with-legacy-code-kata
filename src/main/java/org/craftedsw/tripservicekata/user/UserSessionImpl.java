package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;

public class UserSessionImpl implements UserSession {
    private static final UserSession userSession = new UserSessionImpl();

    public UserSession getInstance() {
        return userSession;
    }

    public User getLoggedUser() {
        return new User();
    }
}
