package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    private TripService tripService;

    @Mock
    private UserSession userSession;

    @Mock
    private ITripDaoImpl tripDao;

    private User user;
    private User loggedInUser;

    @BeforeEach
    void setUp() {
        tripService = new TripService(tripDao, userSession);
        user = new User();
        loggedInUser = new User();
    }

    @Test
    void should_throw_exception_when_user_not_logged_in() {
        when(userSession.getLoggedUser()).thenReturn(null);

        assertThrows(UserNotLoggedInException.class, () ->
                tripService.checkFriends(user)
        );
    }

    @Test
    void should_return_false_when_users_are_not_friends() {
        when(userSession.getLoggedUser()).thenReturn(loggedInUser);
        user.addFriend(new User());

        assertFalse(tripService.checkFriends(user));
    }

    @Test
    void should_return_true_when_users_are_friends() {
        when(userSession.getLoggedUser()).thenReturn(loggedInUser);
        user.addFriend(loggedInUser);

        assertTrue(tripService.checkFriends(user));
    }

    @Test
    void should_return_empty_trip_list_when_users_are_not_friends() {
        when(userSession.getLoggedUser()).thenReturn(loggedInUser);

        List<Trip> trips = tripService.getTripsByUser(user);

        assertTrue(trips.isEmpty());
    }

    @Test
    void should_return_trips_when_users_are_friends() {
        when(userSession.getLoggedUser()).thenReturn(loggedInUser);
        user.addFriend(loggedInUser);
        Trip trip = new Trip();
        when(tripDao.getTripsByUser(user)).thenReturn(List.of(trip));

        List<Trip> trips = tripService.getTripsByUser(user);

        assertFalse(trips.isEmpty());
        assertEquals(1, trips.size());
        verify(tripDao).getTripsByUser(user);
    }
}