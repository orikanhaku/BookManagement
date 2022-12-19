package com.example.bookmanagementapp.demo.greeting;

public class Greeting {


    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

/*
The service will handle GET requests for /greeting, optionally with a name parameter in the query string.
        The GET request should return a 200 OK response with JSON in the body that represents a greeting.
        It should resemble the following output:


        The id field is a unique identifier for the greeting, and content is the textual representation of the greeting.

        To model the greeting representation, create a resource representation class. To do so,
        provide a plain old Java object with fields, constructors, and accessors for the id and content data,
        as the following listing

	This application uses the Jackson JSON library to automatically marshal instances of type Greeting into JSON.
	 Jackson is included by default by the web starter.


STEPS:
1) Create a Resource Controller



 */