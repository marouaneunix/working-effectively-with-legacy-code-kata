package org.craftedsw.tripservicekata.user;

public class IsUserSessionImpl implements IsUserSession {
    private static final IsUserSessionImpl userSession = new IsUserSessionImpl();

    public IsUserSessionImpl() {
    }

    public static IsUserSessionImpl getInstance() {
        return userSession;
    }

    @Override
    public User getLoggedUser() {
        return null;
    }
}
