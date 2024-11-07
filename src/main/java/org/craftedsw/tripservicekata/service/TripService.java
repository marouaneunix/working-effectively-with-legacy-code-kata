package org.craftedsw.tripservicekata.service;

import org.craftedsw.tripservicekata.entity.Trip;
import org.craftedsw.tripservicekata.entity.User;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;

import java.util.List;

public interface TripService {

    List<Trip> getTripsByUser(User user) throws UserNotLoggedInException;

}
