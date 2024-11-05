package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {

    @Mock
    private UserSession userSession;
    private TripService tripService;

    @BeforeEach
    public void setUp() {
        this.tripService = new TripService(userSession);
    }

    // 1

    @Test
    public void should_throw_UserNotLoggedInException_when_user_not_logged() {
        // Given
        when(this.userSession.getLoggedUser()).thenReturn(null);
        User user = new User();
        // Then
        Assertions.assertThrows(UserNotLoggedInException.class, () -> this.tripService.getTripsByUser(user));

    }

    // 2
    @Test
    public void should_return_empty_trip_list_when_user_is_not_friend() {
        // Given
        User loggedUser = new User();
        User user = new User();
        when(this.userSession.getLoggedUser()).thenReturn(loggedUser);
        // Then
        Assertions.assertTrue(this.tripService.getTripsByUser(user).isEmpty());
    }

    // 3
//    @Test
//    public void should_return_trip_list_when_user_is_friend() {
//        // Given
//        User loggedUser = new User();
//        User user = new User();
//        user.addFriend(loggedUser);
//        when(this.userSession.getLoggedUser()).thenReturn(loggedUser);
//        // Then
//        Assertions.assertFalse(this.tripService.getTripsByUser(user).isEmpty());
//    }
}
