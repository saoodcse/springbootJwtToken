# Saood Application JWT Token Based

## Overview

The **Saood Application** is a Spring Boot-based project demonstrating secure RESTful web services using JWT authentication. It integrates Spring Data JPA and MySQL for entity management and database operations.

---

## Features

- **JWT Authentication**: Secure APIs with JWT.
- **Spring Data JPA**: Manage entities easily.
- **MySQL Database**: Persistent storage.
- **Token Expiration Handling**: Automatic marking of expired tokens.
- **Spring Security**: Authentication and authorization.

----
## APIs Curl
- **Login API**

**Request-**

curl --location 'http://localhost:8080/api/login' \
  --header 'Content-Type: application/json' \
  --data '{

  "userName":"saoodcse",
  "password":"alam"
  }'
  
**Response-**

{
"data": {
"userName": "saoodcse",
"role": "USER",
"sessionId": "pfIX2OuDV6lLrWWQNziV",
"token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiZm9vIiwic3ViIjoicGZJWDJPdURWNmxMcldXUU56aVYiLCJpYXQiOjE3MzMwMzg1MDYsImp0aSI6IjJhYTQ5N2MyLWUxODYtNGFhZC04YjkwLWZkMGJhOWYyZWI1NiIsImlzcyI6InNhb29kLmNvbSIsImV4cCI6MTczMzAzOTEwNn0.at79Vrp7hdoB2O5Zldu6kZYBcx8eZ63Z1xwUbOtU4rA"
},
"status": "Successfully logged in",
"code": "6000"
}


- **User Details API**

**Request-**

curl --location 'http://localhost:8080/user/details' \
--header 'session-id: pfIX2OuDV6lLrWWQNziV' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiZm9vIiwic3ViIjoicGZJWDJPdURWNmxMcldXUU56aVYiLCJpYXQiOjE3MzMwMzg1MDYsImp0aSI6IjJhYTQ5N2MyLWUxODYtNGFhZC04YjkwLWZkMGJhOWYyZWI1NiIsImlzcyI6InNhb29kLmNvbSIsImV4cCI6MTczMzAzOTEwNn0.at79Vrp7hdoB2O5Zldu6kZYBcx8eZ63Z1xwUbOtU4rA' \
--data '{
"userName":"saoodcse"
}'

**Response-**

{
"data": {
"userName": "saoodcse",
"dob": "1995-01-01",
"firstName": "saood",
"middleName": null,
"lastName": "alam",
"mobileNumber": null,
"email": "saood@example.com",
"country_name": "India",
"role": "USER"
},
"status": "Success",
"code": "6000"
}

- **User Register API**

**Request-**

curl --location 'http://localhost:8080/api/registration' \
--header 'Content-Type: application/json' \
--data-raw '{
"userName":"alams",
"password": "alam",
"dob": "1995-01-01",
"firstName": "saood",
"lastName": "alam",
"mobileNumber": "9876543210",
"email": "saood@example.com",
"country_name": "India"
}
'

**Response-**

{
"data": {
"registrationId": "TVYvCi0wkfCrFL8Rzb"
},
"status": "Successfully Created Registration",
"code": "6000"
}

- **Generate token API**

**Request-**

curl --location 'http://localhost:8080/api/token' \
--header 'userName: pfIX2OuDV6lLrWWQNziV' \
--header 'role: USER'

**Response-**

eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6InBmSVgyT3VEVjZsTHJXV1FOemlWIiwiaWF0IjoxNzMzMDM5Mjg5LCJqdGkiOiJkZDVhOTQ4Ni03MWU1LTQ3MzgtODQ5Mi1mNzIzMTliNmI4Y2QiLCJpc3MiOiJzYW9vZC5jb20iLCJleHAiOjE3MzMwMzk4ODl9.je05WYbDjrEk31TvWpDu46gWGlsFXZrqsW07ryZyFm4

## Getting Started

### Prerequisites

- **Java 17**
- **Maven**
- **MySQL**

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/saoodcse/springbootJwtToken.git
   cd springbootJwtToken
