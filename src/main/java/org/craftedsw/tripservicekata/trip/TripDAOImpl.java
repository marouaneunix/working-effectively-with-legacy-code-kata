package org.craftedsw.tripservicekata.trip;
import org.craftedsw.tripservicekata.user.User;

import java.util.Collections;
import java.util.List;

public class TripDAOImpl implements ITripDAO {
    @Override
    public List<Trip> findTripsByUser(User user) {
        return Collections.emptyList();
    }
}