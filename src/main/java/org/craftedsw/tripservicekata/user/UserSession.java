package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;

public interface UserSession {
	UserSession getInstance();

	User getLoggedUser();

}
