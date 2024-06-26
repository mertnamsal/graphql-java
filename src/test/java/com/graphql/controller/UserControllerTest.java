package com.graphql.controller;

import com.graphql.exception.GraphqlException;
import com.graphql.exception.UserNotFoundException;
import com.graphql.model.Role;
import com.graphql.model.User;
import com.graphql.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureGraphQlTester
class UserControllerTest {

  @Mock
  private UserService userService;
  @Autowired
  GraphQlTester graphQlTester;

  @BeforeEach
  void setUp() {
    createUser(new User("mert", "mert@gmail.com", Role.ADMIN));
    createUser(new User("ali", "ali@gmail.com", Role.USER));
    createUser(new User("mehmet", "mehmet@gmail.com", Role.USER));

  }

  @Test
  void when_getAllUser_should_return_userList() {
    // language=graphql
    String query = """
        query {
           getAllUsers{
               id
               username
               role
               created
               updated
           }
        }
        """;
    graphQlTester.document(query)
        .execute()
        .path("getAllUsers")
        .entityList(User.class)
        .hasSize(3);
  }
  @Test
  void when_createUser_should_createNewUserAndReturnUser(){
    //language=graphql
    String mutation = """
        mutation{
        createUser(userRequest:{username:"mert",mail:"mert@gmail.com",role: ADMIN}){
          id
          username
          role
          created
          updated
              }
          }
        """;
    graphQlTester.document(mutation).execute()
        .path("createUser")
        .entity(User.class);
  }
  @Test
  void when_getUserById_should_returnUser(){
    //language=graphql
    String query = """
        query{
          getUserById(id:1){
            id
            username
            role
          }
        }
        """;
    graphQlTester.document(query).execute()
        .path("getUserById")
        .entity(User.class);
  }

  // To create user before test
  void createUser(User user) {
    String mutation = """
        mutation{
        createUser(userRequest:{username:"%s",mail:"%s",role:%s}){
          id
          username
          role
          created
          updated
              }
          }
        """.formatted(user.getUsername(), user.getMail(), user.getRole());
    graphQlTester.document(mutation).execute().path("createUser");
  }
}