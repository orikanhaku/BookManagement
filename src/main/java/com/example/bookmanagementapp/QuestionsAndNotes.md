* TODO
* https://site.mockito.org
1) implement mockito tests
* https://assertj.github.io/doc/
* 
2) implement assertj tests (testImplementation("org.assertj:assertj-core:3.23.1")


* https://spring.io/guides/gs/actuator-service/

3) Understand web servises architeecture (regarding endpoints etc.) 
4) Tutorial RESTful Web Service 
* $ curl http://localhost:9000/hello-world
* {"id":1,"content":"Hello, World!"} // response with JSON
* https://spring.io/guides/gs/rest-service/


5) What is the difference between actuator service vs rest service? (I will create a rest service at first))


./gradlew clean build && java -jar build/libs/gs-actuator-service-0.1.0.jar




6) Did you notice that there was not a single line of XML? There is no web.xml file, either - where do we use XML? Why don't we need to use it with spring (rest controller class example)
7) http://localhost:8080/greeting?name=User (variable increasing when visitin the website - how to make a GET request using a terminal?)

*QUESTION

```java
//how does it called? 
    //queston: anonoymous/functional?
    public static Comparator<BookInstance> BookAuthor = new Comparator<BookInstance> () {
        public int compare(BookInstance b1, BookInstance b2) {
            return String.CASE_INSENSITIVE_ORDER.compare(b1.getAuthor(), b2.getAuthor());
        }
    };

```


* SUMMARY TUTORIAL https://spring.io/guides/gs/actuator-service/


*** FINISHED *** 


1) https://spring.io/guides/gs/gradle/ 
* (how to set-up a spring boot project with gradle; not 100% complete - e.g. version management etc. untouched)
* "cheated" by using intellij 



GUIDES 

* (+) Building a restuful service: https://spring.io/guides/gs/rest-service/
* Consuming a restful service https://spring.io/guides/gs/consuming-rest/
* Rest Tutorial: https://spring.io/guides/tutorials/rest/ !!!


