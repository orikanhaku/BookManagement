package com.example.bookmanagementapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    //TODO: test cases for very big input (more than 100 books)
    //Solution:
    //1: Store the output in a file and put it in the resources folder and then read the file to a string and compare with that
    //2: just use """ """


    String defaultOutput = """
            [{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2},{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4}]
            """;

    //default output: ascending by title, per_page=10
    @Test
    public void defaultOutputTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(defaultOutput));
        //TODO: why only json accepted and not string.equalsTo?
        //.andExpect(content().string(equalTo(defaultOutput)));
        //e.g. it has worked with Greeting class
    }


    /*
TestCase: Descending by title + Returning 10 Books
Input:http://localhost:8080/books?order=descending
Output:String descendingByTitle="""
[{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9},{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5}]
"""
 */
    String descendingByTitle = """
            [{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9},{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5}]
            """;

    @Test
    public void descendingByTile() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?order=descending").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(descendingByTitle));
        //TODO: why only json accepted and not string.equalsTo?
        //.andExpect(content().string(equalTo(defaultOutput)));
        //e.g. it has worked with Greeting class inside demo package
    }

    /*
TestCase: Descending by author
Input:http://localhost:8080/books?order_by=author&order=descending
Output:[{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2}]

 */

    String descendingByAuthor = """
            [{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2}]
            """;


    @Test
    public void descendingByAuthor() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?order_by=author&order=descending").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(descendingByAuthor));
    }


    /*
TestCase: Ascending by author
Input:http://localhost:8080/books?order_by=author
 */
    String ascendingByAuthor = """
            [{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4},{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9}]
            """;

    @Test
    public void ascendingByAuthor() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?order_by=author").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(descendingByAuthor));
    }


    /*
TestCase: Descending by year
Input:http://localhost:8080/books?order_by=year&order=descending
 */
    String descendingByYear = """
            [{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2}]
            """;

    @Test
    public void descendingByYear() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?order_by=year&order=descending").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(descendingByYear));
    }


    /*
TestCase: Ascending by year
Input:localhost:8080/books?order_by=year
 */

    String ascendingByEar = """
            [{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4},{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9}]
            """;


    @Test
    public void ascendingByYear() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?order_by=year").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(ascendingByEar));
    }


    /*
    TestCase: Ascending by title
    Input:http://localhost:8080/books?order_by=title
     */
    String ascendingByTitle = """
            [{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8},{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2},{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4}]
            """;

    @Test
    public void ascendingByTitle() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?order_by=title").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(ascendingByTitle));
    }


    /*
    TestCase: Return 5 books (+they must by default be ascended by title)
    Input:http://localhost:8080/books?per_page=5
     */
    String fiveBooksDescendingByTitle = """
            [{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4},{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"Andrzej Sapkowski","title":"The Last Wish","year":1993,"bookId":1},{"author":"Charles Petzold","title":"The Hidden Language of Computer Hardware and Software","year":2022,"bookId":9},{"author":"A1","title":"Sword of Destiny","year":1992,"bookId":2}]
            """;

    @Test
    public void fiveBooks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?per_page=5&order=descending").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(fiveBooksDescendingByTitle));
    }


    String fiveBooksAscendingByTitle= """
            [{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5},{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"Cay S. Horstmann","title":"Core Java: Volume I - Fundamentals","year":1993,"bookId":10},{"author":"A6","title":"Lady of the Lake","year":1999,"bookId":7},{"author":"A7","title":"Season of Storms","year":2013,"bookId":8}]
            """;

    @Test
    public void setFiveBooksAscendingByTitle() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?per_page=5").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(fiveBooksAscendingByTitle));
    }



/*
TestCase: 10 Books, per_page=4, page=3, descended by title (Default sorting: descended by title) => last 2 with 2 smallest titles lexicographically
Input:http://localhost:8080/books?per_page=4&page=3&order=descending
 */

    String tenBooksFourPerPagePageThreeDescendedByTitle = """
            [{"author":"A2","title":"Blood of Elves","year":1994,"bookId":3},{"author":"A4","title":"Baptism of Fire","year":1996,"bookId":5}]
            """;

    @Test
    public void tenBooksFourPerPagePageThreeDescendedByTitle() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?per_page=4&page=3&order=descending").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(tenBooksFourPerPagePageThreeDescendedByTitle));
    }


    /*
TestCase:  page3, per_page4 (Default sorting: ascended by title) => last 2 books with title > lexicographically
Input:http://localhost:8080/books?per_page=4&page=3
 */
    String page3per_Page4_ascendingByTitle="""
[{"author":"A5","title":"The Tower of Swallows","year":1997,"bookId":6},{"author":"A3","title":"Time of Contempt","year":1995,"bookId":4}]
 """;

    @Test
    public void page3per_Page4_ascendingByTitle() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/books?per_page=4&page=3").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(page3per_Page4_ascendingByTitle));
    }

}


