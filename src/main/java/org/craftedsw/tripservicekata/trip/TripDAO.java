package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

public class TripDAO implements ITripDao {


	@Override
	public List<Trip> findTripsByUser(User user) {
		return Collections.emptyList();
	}
}
