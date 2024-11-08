package org.craftedsw.tripservicekata.user;

public interface IUserSession {
    public  UserSession getInstance();
    public User getLoggedUser();
    public User getLoggedUserNull();
}
