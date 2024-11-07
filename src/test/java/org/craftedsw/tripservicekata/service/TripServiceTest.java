package org.craftedsw.tripservicekata.service;

import org.craftedsw.tripservicekata.entity.Trip;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.dao.impl.TripDAOImpl;
import org.craftedsw.tripservicekata.entity.User;
import org.craftedsw.tripservicekata.service.impl.TripServiceImpl;
import org.craftedsw.tripservicekata.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TripServiceTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private TripDAOImpl tripDAO;

    @InjectMocks
    private TripServiceImpl tripService;

    @Test
    void shouldThrowExceptionWhenUserIsNotLoggedIn() {
        when(userService.getLoggedUser()).thenReturn(null);

        assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(new User()));

        verify(userService).getLoggedUser();
    }

    @Test
    void shouldReturnEmptyTripsWhenUsersAreNotFriends() {
        User loggedUser = new User();
        User anotherUser = new User();

        when(userService.getLoggedUser()).thenReturn(loggedUser);

        List<Trip> result = tripService.getTripsByUser(anotherUser);

        assertEquals(Collections.emptyList(), result);
        verify(userService).getLoggedUser();
    }

    @Test
    void shouldReturnTripsWhenUsersAreFriends() {
        User loggedUser = new User();
        User friendUser = new User();
        friendUser.addFriend(loggedUser);

        List<Trip> trips = List.of(new Trip());
        when(userService.getLoggedUser()).thenReturn(loggedUser);
        when(tripDAO.findTripsByUser(friendUser)).thenReturn(trips);

        List<Trip> result = tripService.getTripsByUser(friendUser);

        assertEquals(trips, result);
        verify(userService).getLoggedUser();
        verify(tripDAO).findTripsByUser(friendUser);
    }
}