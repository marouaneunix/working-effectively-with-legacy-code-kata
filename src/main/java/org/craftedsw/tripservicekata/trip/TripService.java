package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.IsUserSession;
import org.craftedsw.tripservicekata.user.User;

import java.util.ArrayList;
import java.util.List;

public class TripService {
    private final IsUserSession isUserSession;

    public TripService(IsUserSession isUserSession) {
        this.isUserSession = isUserSession;
    }
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (isUserSession.getLoggedUser() != null) {
            return userIsFriend(user, isUserSession.getLoggedUser()) ? TripDAO.findTripsByUser(user) : new ArrayList<>();
        }else {
            throw new UserNotLoggedInException();
        }
    }
    private boolean userIsFriend(User user,User loggedUser){
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                return true;
            }
        }
        return false;
    }

}
