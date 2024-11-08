package org.craftedsw.tripservicekata.trip;


import java.util.List;
import org.craftedsw.tripservicekata.user.User;

public interface ITripDAO {
    List<Trip> findTripsByUser(User user);
}

