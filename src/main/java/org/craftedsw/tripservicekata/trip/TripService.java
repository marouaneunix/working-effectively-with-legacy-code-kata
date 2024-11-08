package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripDAO;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {

    private final UserSession userSession;
    private final TripDAO tripDAO;

    public TripService(UserSession userSession, TripDAO tripDAO) {
        this.userSession = userSession;
        this.tripDAO = tripDAO;
    }

    private void notLoggedUser(User user) throws UserNotLoggedInException {
        if (user == null) {
            throw new UserNotLoggedInException();
        }
    }


    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<Trip>();
        User loggedUser = userSession.getLoggedUser();
        notLoggedUser(loggedUser);
        boolean isFriend = false;


        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                isFriend = true;
                break;
            }
        }
        if (isFriend) {
            tripList = tripDAO.findTripsByUser(user);
        }
        return tripList;


    }
}