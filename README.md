# Pictogram

## Table of Contents
* [Introduction](#introduction)
* [REST API](#rest-api)
* [Project Demo](#project-demo)

## Introduction

> Pictogram is a social network platform that tries to bring the best from both Instagram and 9gag.
It is written in Java and powered by Spring Boot.

## REST 
> When sending POST, PUT and PATCH requests make sure to include Authorization header:
`
Authorization:Bearer token
`

#### Users

> POST /auth/register
```
{
    "username": "user",
    "password": "password",
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "profileImage": "image.jpg"
}
```

> POST /auth
```
{
    "username": "user",
    "password": "password"
}
```

#### Posts

> POST /posts
```
{
    "title": "title",
    "description": "desc",
    "postImage": "image.jpg"
}
```

> GET /posts

> GET /users/{userId}/posts

> GET /posts/{postId}

#### Comments

> GET /posts/{postId}/comments

> GET /users/{userid}/comments


#### Upvotes

> PUT /posts/{postId}

> PUT /comments/{commentId}

#### Reports

> PATCH /posts/{postId}

> PATCH /comments/{commentId}

## Project Demo

> To showcase this project in action a React application will be provided.

> This project will be hosted on Heroku.