package org.craftedsw.tripservicekata.service.impl;

import org.craftedsw.tripservicekata.dao.TripDAO;
import org.craftedsw.tripservicekata.entity.Trip;
import org.craftedsw.tripservicekata.entity.User;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class TripServiceImpl {
    private final UserService userService;
    private final TripDAO tripDAO;

    public TripServiceImpl(UserService userService, TripDAO tripDAO) {
        this.userService = userService;
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        List<Trip> tripList = new ArrayList<>();
        User loggedUser = userService.getLoggedUser();

        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        if (isFriend(user, loggedUser)) {
            tripList = tripDAO.findTripsByUser(user);
        }

        return tripList;
    }

    private boolean isFriend(User user, User loggedUser) {
        for (User friend : user.getFriends()) {
            if (friend.equals(loggedUser)) {
                return true;
            }
        }
        return false;
    }
}
