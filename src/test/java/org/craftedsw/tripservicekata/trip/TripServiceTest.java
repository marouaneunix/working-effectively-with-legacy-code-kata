package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    private UserSession userSession;
    @InjectMocks
    private TripService tripService;

    @Test
    void should_throw_UserNotLoggedInException_when_loggedUser_is_null(){
        User user = new User();
        when(userSession.getLoggedUser()).thenReturn(null);
        Assertions.assertThrows(UserNotLoggedInException.class, () -> tripService.getTripsByUser(user));
    }

    @Test
    void should_return_empty_list_when_user_is_not_friend(){
        User user = new User();
        User friend = new User();
        when(userSession.getLoggedUser()).thenReturn(friend);
        Assertions.assertEquals(0, tripService.getTripsByUser(user).size());
    }

    @Test
    void should_return_friends_list_when_user_is_friend(){
        User user = new User();
        User friend = new User();
        user.addFriend(friend);
        when(userSession.getLoggedUser()).thenReturn(friend);
        Assertions.assertEquals(0, tripService.getTripsByUser(user).size());
    }
}
