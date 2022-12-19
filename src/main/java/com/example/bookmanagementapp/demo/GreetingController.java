package com.example.bookmanagementapp.demo;
// Source: https://spring.io/guides/gs/rest-service/

import com.example.bookmanagementapp.demo.greeting.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;
// This code uses Spring @RestController annotation, which marks the class as a controller
// where every method returns a domain object instead of a view.
// It is shorthand for including both @Controller and @ResponseBody.
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
// The @GetMapping annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.
    @GetMapping("/greeting")
// @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method.
// If the name parameter is absent in the request, the defaultValue of World is used.
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        /*
        The implementation of the method body creates and returns a new Greeting object with id and content attributes
        based on the next value from the counter and formats the given name by using the greeting template.
         */
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}


/*
       HOW TO RUN IT
       ./gradlew bootRun || ./gradlew build => java -jar target/gs-rest-service-0.1.0.jar

       (Maven: ./mvnw spring-boot:run => ./mvnw clean package => java -jar target/gs-rest-service-0.1.0.jar
        ?Logging output is displayed. The service should be up and running within a few seconds.
       for classic war: https://spring.io/guides/gs/convert-jar-to-war/

        HOW TO TEST IT

        ./gradlew bootRun
        http://localhost:8080/books

       Service is now running ->  http://localhost:8080/greeting => {"id":1,"content":"Hello, World!"}

       http://localhost:8080/greeting?name=User => {"id":2,"content":"Hello, User!"} (value of the mane has changed)


        his change demonstrates that the @RequestParam arrangement in GreetingController is working as expected.
        The name parameter has been given a default value of World but can be explicitly overridden through the query string.

        Notice also how the id attribute has changed from 1 to 2.
        This proves that you are working against the same GreetingController instance across multiple requests
        and that its counter field is being incremented on each call as expected.


 */



/*
-       There are companion annotations for other HTTP verbs (e.g. @PostMapping for POST).
        There is also a @RequestMapping annotation that they all derive from, and can serve as
        a synonym (e.g. @RequestMapping(method=GET)).

-       A key difference between a traditional MVC controller and the RESTful web service controller shown earlier
        is the way that the HTTP response body is created. Rather than relying on a view technology
        to perform server-side rendering of the greeting data to HTML,
        this RESTful web service controller populates and returns a Greeting object.
        The object data will be written directly to the HTTP response as JSON.



        The Greeting object must be converted to JSON. Thanks to Spring’s HTTP message converter support,
        you need not do this conversion manually. Because Jackson 2 is on the classpath,
        Spring’s MappingJackson2HttpMessageConverter
        is automatically chosen to convert the Greeting instance to JSON


        @SpringBootApplication is a convenience annotation that adds all of the following:

        @Configuration: Tags the class as a source of bean definitions for the application context.

        @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,
        other beans, and various property settings. For example,
        if spring-webmvc is on the classpath, this annotation flags the application as a web application
        and activates key behaviors, such as setting up a DispatcherServlet.

        @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package,
        letting it find the controllers


        The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.
        Did you notice that there was not a single line of XML? There is no web.xml file, either.
        This web application is 100% pure Java and you did not have to deal with configuring any plumbing or infrastructure



 */