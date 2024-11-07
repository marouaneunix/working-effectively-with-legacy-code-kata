package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.IUserSession;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    TripService tripService;
    @Mock
    IUserSession userSession;
    @Mock
    ITripDAO tripDAO;

    @BeforeEach
    void setUp() {
        tripService = new TripService(userSession, tripDAO);
    }

    @Test
    void should_throw_user_not_logged_in() {
        assertThrows(UserNotLoggedInException.class,()-> tripService.getTripsByUser(null));
    }

    @Test
    void should_return_empty_list_when_user_logged_in_and_has_no_friends() {
        when(this.userSession.isLoggedIn()).thenReturn(true);
        assertEquals(Collections.emptyList(), tripService.getTripsByUser(new User()));
    }

    @Test
    void should_return_list_when_user_logged_in_and_has_friends() throws UserNotLoggedInException {
        User friend1 = new User();
        User loggedUser = new User();
        User user = new User();
        user.addFriend(friend1);
        user.addFriend(loggedUser);
        //
        Trip trip = new Trip();
        user.addTrip(trip);
        List<Trip> trips = Collections.singletonList(trip);
        //
        when(this.userSession.isLoggedIn()).thenReturn(true);
        when(this.userSession.getLoggedUser()).thenReturn(loggedUser);
        when(this.tripDAO.findTripsByUser(user)).thenReturn(trips);
        assertEquals(trips, tripService.getTripsByUser(user));
    }

}
