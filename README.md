# Cinema-room-REST-service

https://hyperskill.org/projects/189?track=12
Hypersill project

Using SpringBoot created a REST service that helps with managing theatres.
Handle HTTP requests in controllers, create services and respond with JSON objects.


---
#### `GET /seats`

return all the available seats in the cinema room.


#### `POST /purchase`



request body 

```
{
    "row": 3,
    "column": 4
}
```

A correct request body would get a token and seat info:

```
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```


#### `POST /return`


In the request body, include the token to return the ticket

```
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```

The response body would be:

```
{
    "returned_ticket": {
        "row": 1,
        "column": 2,
        "price": 10
    }
}
```

#### `POST /stats`

used by the manager of the cinema room, if the URL parameters contain a correct password key, it will return the statistics of the cinema room in the following format:

```
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```
if the password is null or wrong, a response with a `401` status code will be returned.


