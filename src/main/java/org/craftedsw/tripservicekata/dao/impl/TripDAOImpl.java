package org.craftedsw.tripservicekata.dao.impl;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.dao.TripDAO;
import org.craftedsw.tripservicekata.entity.Trip;
import org.craftedsw.tripservicekata.entity.User;

public class TripDAOImpl implements TripDAO {

    public List<Trip> findTripsByUser(User user) {
        return Collections.emptyList();
    }

}
