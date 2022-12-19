package com.example.bookmanagementapp.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerApp {

    @GetMapping("/")
    public String index() {
        return "Hello, World! (17.12.2022)";
    }

}

//"Hello, World! (17.12.2022)"
//"Hello, World! (17.12.222)"



/*
(base) boredenginseer23@MacBook-Pro-Vladislav BookManagement % curl localhost:8080
Hello, World! (17.12.222)%                                                                                                                                                                                                                                                           (base) boredenginseer23@MacBook-Pro-Vladislav BookManagement %




 //shit+cmd+T for tests

The class is flagged as a @RestController, meaning it is ready for use by Spring MVC to handle web requests.
@GetMapping maps / to the index() method. When invoked from a browser or by using curl on the command line,
the method returns pure text.
That is because @RestController combines @Controller and @ResponseBody,
two annotations that results in web requests returning data rather than a view



 */