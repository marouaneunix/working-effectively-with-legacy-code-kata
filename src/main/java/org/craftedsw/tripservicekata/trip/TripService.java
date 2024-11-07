package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {
     private final ITripDaoImpl tripDao;
     private final UserSession userSession;

    public TripService(ITripDaoImpl tripDao, UserSession userSession) {
        this.tripDao = tripDao;
        this.userSession = userSession;
    }


    public List<Trip> getTripsByUser(User user) {
        List<Trip> tripList = new ArrayList<>();
        if (checkFriends(user)) {
            tripList = tripDao.getTripsByUser(user);
        }
        return tripList;

    }

    public boolean checkFriends(User user) throws UserNotLoggedInException {
        boolean isFriend = false;
        if (userSession.getLoggedUser() != null) {
            for (User friend : user.getFriends()) {
                if (friend.equals((userSession.getLoggedUser()))) {
                    isFriend = true;
                    break;
                }
            }
        } else {
            throw new UserNotLoggedInException();
        }
        return isFriend;
    }
}
