## Spring Boot and GraphQL Example

This project provides a simple example application using Spring Boot and GraphQL.

### Project Description
- This project creates a GraphQL API server using Spring Boot.
- It facilitates data exchange through GraphQL queries.
- An example database integration is included.

### Features
- Simple GraphQL API server
- Example GraphQL queries for CRUD operations
- Usage of H2 in-memory database

### Technologies
- Spring Boot
- GraphQL
- H2 Database

### Example GraphQL Queries for CRUD Operations

Below are the example GraphQL queries for performing CRUD (Create, Read, Update, Delete) operations in this project.

#### Create a New User

This mutation creates a new user with the specified name, email and role.

Roles defined in scheme.graphqls files.

```graphql
mutation {
  createUser(input: {username:"mert", mail:"mert@gmail.com", role:ADMIN}) {
    id
    name
    email
    role
  }
}
```

### Read All Users

This query fetches all users from the database.

```graphql
query {
  getAllUsers {
    id
    username
    role
    created
    updated
  }
}
```
### Read a Specific User by ID

This query fetches a specific user by their ID.

```graphql
query {
  getUserById(id:1) {
    id
    username
    role
  }
}
```

#### Update a User

This mutation updates the details of an existing user with the specified ID.

```graphql
mutation{
  updateUser(userRequest:{id:1, username:"can", mail:"can@gmail.com", role:ADMIN}) {
    id
    username
    mail
    role
  }
}
```

### Delete a User

This mutation deletes a user with the specified ID.

```graphql
mutation {
  deleteUser(id: 1)
}
```


