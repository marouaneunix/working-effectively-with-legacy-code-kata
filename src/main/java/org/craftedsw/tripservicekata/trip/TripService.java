package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.IUserSession;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

    private final IUserSession userSession;
    private final ITripDAO tripDAO;


    public TripService(IUserSession userSession, ITripDAO tripDAO) {
        this.userSession = userSession;
        this.tripDAO = tripDAO;
    }


    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        if (this.userSession.isLoggedIn()) {
            return hasFriends(user) ? this.tripDAO.findTripsByUser(user) : Collections.emptyList();
        } else {
            throw new UserNotLoggedInException();
        }
    }

    public boolean hasFriends(User user) {
        return user.getFriends()
                .stream()
                .anyMatch(friend -> friend.equals(this.userSession.getLoggedUser()));
    }

}
