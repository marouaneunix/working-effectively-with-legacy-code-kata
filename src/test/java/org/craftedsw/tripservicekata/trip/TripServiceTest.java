package org.craftedsw.tripservicekata.trip;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.ITripDAO;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.IUserSession;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TripServiceTest {

    @Mock
    private IUserSession userSession;

    @Mock
    private ITripDAO tripDAO;

    @InjectMocks
    private TripService tripService;

    private User loggedUser;
    private User targetUser;
    private Trip trip1;
    private Trip trip2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Création de données de test
        loggedUser = new User();
        targetUser = new User();

        trip1 = new Trip();
        trip2 = new Trip();
    }

    @Test
    void should_throw_exception_when_user_notLoggedIn() {
        when(userSession.getLoggedUser()).thenReturn(null);

        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(targetUser));
    }

    @Test
    void should_return_empty_list_when_users_areNotFriends() throws UserNotLoggedInException {
        when(userSession.getLoggedUser()).thenReturn(loggedUser);

        targetUser.addFriend(new User());
        List<Trip> trips = tripService.getTripsByUser(targetUser);
        assertTrue(trips.isEmpty());
    }

    @Test
    void should_return_trips_when_users_areFriends() throws UserNotLoggedInException {

        when(userSession.getLoggedUser()).thenReturn(loggedUser);
        targetUser.addFriend(loggedUser);
        when(tripDAO.findTripsByUser(targetUser)).thenReturn(Arrays.asList(trip1, trip2));

        List<Trip> trips = tripService.getTripsByUser(targetUser);
        assertEquals(2, trips.size());
        assertEquals(trip1, trips.get(0));
        assertEquals(trip2, trips.get(1));
    }
}
