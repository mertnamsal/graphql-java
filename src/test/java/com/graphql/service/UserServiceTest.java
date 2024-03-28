package com.graphql.service;

import com.graphql.exception.UserNotFoundException;
import com.graphql.model.Role;
import com.graphql.model.User;
import com.graphql.model.UserRequest;
import com.graphql.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllUsers() {

    List<User> userList = new ArrayList<>();
    userList.add(new User("mert", "mert@example.com", Role.USER));
    userList.add(new User("cem", "cem@example.com", Role.ADMIN));

    Mockito.when(userRepository.findAll()).thenReturn(userList);

    List<User> result = userService.getAllUsers();

    Assertions.assertEquals(2, result.size());
  }

  @Test
  public void testGetUserById() {

    User user = new User("mert", "mert@example.com", Role.USER);
    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    User result = userService.getUserById(1L);

    Assertions.assertEquals(user, result);
  }

  @Test
  public void testGetUserById_UserNotFoundException() {

    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

    Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
  }

  @Test
  public void testCreateUser() {

    UserRequest userRequest = new UserRequest(1L,"cem", "cem@example.com", Role.ADMIN);
    User user = new User("mert", "mert@example.com", Role.USER);
    Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

    User result = userService.createUser(userRequest);

    Assertions.assertEquals(user, result);
  }

}
