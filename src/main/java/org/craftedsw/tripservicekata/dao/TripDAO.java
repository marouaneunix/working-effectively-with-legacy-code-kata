package org.craftedsw.tripservicekata.dao;

import org.craftedsw.tripservicekata.entity.Trip;
import org.craftedsw.tripservicekata.entity.User;

import java.util.List;

public interface TripDAO {
    List<Trip> findTripsByUser(User user);
}
