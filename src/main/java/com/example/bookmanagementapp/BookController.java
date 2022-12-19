package com.example.bookmanagementapp;


import com.example.bookmanagementapp.book.BookClass;
import com.example.bookmanagementapp.book.BookInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private BookClass books;

    public BookController() {
        this.books = new BookClass();

    }

    /*
    Question: how to add multile parameters?



      ./gradlew bootRun
    http://localhost:8080/books
    http://localhost:8080/books?page=3
            *///http://localhost:8080/greeting?name=User

    //this means when I open http://localhost:8080/books I will get 10 books and they will be printed
    @GetMapping("/books")
    //public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    //http://localhost:8080/greeting?name=User
    ////http://localhost:8080/greeting?name=User


    /*
          Service is now running ->  http://localhost:8080/greeting => {"id":1,"content":"Hello, World!"}

       http://localhost:8080/greeting?name=User => {"id":2,"content":"Hello, User!"} (value of the mane has changed)



--per page: how many books can you return at once; default value is 10;
-- page: which page is returned; if per_page is 4, then page 3 should return 2 books.
--order:  ascending by title by default, otherwise descending by title as a default
-- order_by: what is yoused for ordering the books. (e.g. author,...)


         */
    // what is a better solution to -1?
    public List<BookInstance> getBooks
    (@RequestParam(value = "per_page", required = false, defaultValue = "10") int per_page,
     @RequestParam(value = "page", required = false, defaultValue = "-1") int page,
     @RequestParam(value = "order", required = false, defaultValue = "ascending") String order,
     @RequestParam(value = "order_by", required = false, defaultValue = "title") String orderBy
    ) {

        //ismatchException: Failed to convert value of type 'java.lang.String' to required type 'int'; For input string: "ten"]
        //at first I should sort the books;

        return books.getBooks(per_page, page, order, orderBy);
    }
}


/*
http://localhost:8080/books?page=3&per_page=4

[{"author":"Andrzej Sapkowski","title":"Sword of Destiny","year":1992,"bookId":2},{"author":"Andrzej Sapkowski","title":"Blood of Elves","year":1994,"bookId":3},{"author":"Andrzej Sapkowski","title":"Time of Contempt","year":1995,"bookId":4},{"author":"Andrzej Sapkowski","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"Andrzej Sapkowski","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"Andrzej Sapkowski","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"Andrzej Sapkowski","title":"Season of Storms","year":2013,"bookId":8}



 */












/*
https://www.amitph.com/spring-requestparam-annotation/


 */
/*
    @GetMapping("/data2")
    public String multiParam(
            @RequestParam String id,
            @RequestParam String name) {

        return "id: " + id + ", name: " + name;
    }
~ curl 'http://localhost:8080/data2?id=112&name=Jon'
        --
        id: 112, name: Jon



   OPTIONAL
@GetMapping("/data5")
public String optionalParams
    (@RequestParam(required = false) Long id) {

  return "id: " + id;

  ~ curl 'http://localhost:8080/data5'
--
id: null

DEFAULT

@GetMapping("/data7")
public String defaultParams
    (@RequestParam(defaultValue = "Unknown") String id) {

  return "id: " + id;

  ~ curl 'http://localhost:8080/data7'
--
id: Unknown

}
}


 */
/*
public List<BookInstance> getBooks(@RequestParam(value ="command", defaultValue = "get10books") String value) {



        switch (value) {
            case "command" : return books.getTenBooks();break;
            case ""
        }


;
       // books.printBooks();
        return books.getTenBooks();
    }

 */
