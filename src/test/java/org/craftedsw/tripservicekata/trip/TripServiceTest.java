package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;

import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    private TripService tripService;

    @Mock
    private UserSession userSession;

    @Mock
    private TripDAO tripDAO;

    private User loggedInUser;
    private User targetUser;
    private Trip trip;

    @BeforeEach
    void setUp() {
        tripService = new TripService(userSession, tripDAO);
        loggedInUser = new User();
        targetUser = new User();
        trip = new Trip();
    }

    @Test
    void should_throw_exception_when_user_not_logged_in() {
        // Arrange
        when(userSession.getLoggedUser()).thenReturn(null);

        // Act & Assert
        assertThrows(UserNotLoggedInException.class,
                () -> tripService.getTripsByUser(targetUser));

        verify(userSession).getLoggedUser();
        verifyNoMoreInteractions(tripDAO);
    }

    @Test
    void should_return_empty_trips_when_users_are_not_friends() throws UserNotLoggedInException {
        // Arrange
        when(userSession.getLoggedUser()).thenReturn(loggedInUser);

        // Act
        List<Trip> trips = tripService.getTripsByUser(targetUser);

        // Assert
        assertTrue(trips.isEmpty());
        verify(userSession).getLoggedUser();
        verifyNoMoreInteractions(tripDAO);
    }

    @Test
    void should_return_trips_when_users_are_friends() throws UserNotLoggedInException {
        // Arrange
        targetUser.addFriend(loggedInUser);
        List<Trip> expectedTrips = List.of(trip);

        when(userSession.getLoggedUser()).thenReturn(loggedInUser);
        when(tripDAO.findTripsByUser(targetUser)).thenReturn(expectedTrips);

        // Act
        List<Trip> actualTrips = tripService.getTripsByUser(targetUser);

        // Assert
        assertEquals(expectedTrips, actualTrips);
        verify(userSession).getLoggedUser();
        verify(tripDAO).findTripsByUser(targetUser);
    }

    @Test
    void should_return_empty_list_when_friend_has_no_trips() throws UserNotLoggedInException {
        // Arrange
        targetUser.addFriend(loggedInUser);

        when(userSession.getLoggedUser()).thenReturn(loggedInUser);
        when(tripDAO.findTripsByUser(targetUser)).thenReturn(new ArrayList<>());

        // Act
        List<Trip> trips = tripService.getTripsByUser(targetUser);

        // Assert
        assertTrue(trips.isEmpty());
        verify(userSession).getLoggedUser();
        verify(tripDAO).findTripsByUser(targetUser);
    }
}