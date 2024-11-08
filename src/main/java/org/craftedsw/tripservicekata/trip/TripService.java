package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.IUserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private final IUserSession userSession;
    private final ITripDAO tripDAO;

    public TripService(IUserSession userSession, ITripDAO tripDAO) {
        this.userSession = userSession;
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<>();

        if (isFriend(user)) {
            tripList = tripDAO.findTripsByUser(user);
        }

        return tripList;
    }

    private boolean isFriend(User user) throws UserNotLoggedInException {
        User loggedUser = userSession.getLoggedUser();

        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                return true;
            }
        }

        return false;
    }
}
