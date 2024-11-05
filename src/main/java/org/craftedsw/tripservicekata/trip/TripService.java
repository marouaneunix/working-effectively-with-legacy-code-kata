package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {

    private final UserSession userSession;

    public TripService(UserSession userSession) {
        this.userSession = userSession;
    }

    private void isLoggedIn() {
        User loggedUser = this.userSession.getLoggedUser();
        if(loggedUser == null) {
            throw new UserNotLoggedInException();
        }
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        this.isLoggedIn();
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = this.userSession.getLoggedUser();
		boolean isFriend = false;
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                isFriend = true;
                break;
            }
        }
        if (isFriend) {
            tripList = TripDAO.findTripsByUser(user);
        }
        return tripList;
	}

}
