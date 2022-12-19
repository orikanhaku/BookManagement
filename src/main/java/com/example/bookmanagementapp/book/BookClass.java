package com.example.bookmanagementapp.book;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

import static com.example.bookmanagementapp.book.BookInstance.*;

public class BookClass {

    private final AtomicLong bookId = new AtomicLong(); //does not really makes sense because it will count the amount of HTTP requests
    private List<BookInstance> books;


    public BookClass() {
        this.books = createTenBooks();
    }

    //question: concurrency stable id for every book instead of creating a static variable inside a bookinstance class
    //is it a good solution to instantiate atomicLong inside a BookClass (which is "sorta" a factory?)
    //the problem is, I cannot instantiate atomiclong inside a bookinstance class because I will have
    //to instantiate the instance again every time, but at the same time if bookId is here, then inside bookinstance it is not synchronzed
    // solutin => pass the instance of the AtomicLong bookId to the book? (not possible, only long)


    /*

        BookInstance b1 = new BookInstance("Andrzej Sapkowski", "The Last Wish", 1993, bookId.incrementAndGet());
        BookInstance b2 = new BookInstance("A1", "Sword of Destiny", 1992, bookId.incrementAndGet());
        BookInstance b3 = new BookInstance("A2", "Blood of Elves", 1994, bookId.incrementAndGet());
        BookInstance b4 = new BookInstance("A3", "Time of Contempt", 1995, bookId.incrementAndGet());
        BookInstance b5 = new BookInstance("A4", "Baptism of Fire", 1996, bookId.incrementAndGet());
        BookInstance b6 = new BookInstance("A5", "The Tower of Swallows", 1997, bookId.incrementAndGet());
        BookInstance b7 = new BookInstance("A6", "Lady of the Lake", 1999, bookId.incrementAndGet());
        BookInstance b8 = new BookInstance("A7", "Season of Storms", 2013, bookId.incrementAndGet());
        BookInstance b9 = new BookInstance("Charles Petzold", "The Hidden Language of Computer Hardware and Software", 2022, bookId.incrementAndGet());
        BookInstance b10 = new BookInstance("Cay S. Horstmann", "Core Java: Volume I - Fundamentals", 1993, bookId.incrementAndGet());
        BookInstance b11 = new BookInstance("Cay S. Horstmann", "Core Java: Volume II -Advanced Features", 2006, bookId.incrementAndGet());
        BookInstance b12 = new BookInstance("Noam Nisan", "The Elements of Computing Systems", 2021, bookId.incrementAndGet());
        BookInstance b13 = new BookInstance("Robert Sedgewick", "Algorithms: Part I", 2007, bookId.incrementAndGet());
        BookInstance b14 = new BookInstance("Robert Sedgewick", "Algorithms: Part II", 2014, bookId.incrementAndGet());
        BookInstance b15 = new BookInstance("Miran Lipovaca", "Learn You a Haskell for Great Good!", 2011, bookId.incrementAndGet());


     */

    public List<BookInstance> createTenBooks() {

      //  Map<Long, BookInstance> books = new HashMap<>();

        List<BookInstance> bookList = new ArrayList<>();

        BookInstance b1 = new BookInstance("Andrzej Sapkowski", "The Last Wish", 1993, bookId.incrementAndGet());
        BookInstance b2 = new BookInstance("A1", "Sword of Destiny", 1992, bookId.incrementAndGet());
        BookInstance b3 = new BookInstance("A2", "Blood of Elves", 1994, bookId.incrementAndGet());
        BookInstance b4 = new BookInstance("A3", "Time of Contempt", 1995, bookId.incrementAndGet());
        BookInstance b5 = new BookInstance("A4", "Baptism of Fire", 1996, bookId.incrementAndGet());
        BookInstance b6 = new BookInstance("A5", "The Tower of Swallows", 1997, bookId.incrementAndGet());
        BookInstance b7 = new BookInstance("A6", "Lady of the Lake", 1999, bookId.incrementAndGet());
        BookInstance b8 = new BookInstance("A7", "Season of Storms", 2013, bookId.incrementAndGet());
        BookInstance b9 = new BookInstance("Charles Petzold", "The Hidden Language of Computer Hardware and Software", 2022, bookId.incrementAndGet());
        BookInstance b10 = new BookInstance("Cay S. Horstmann", "Core Java: Volume I - Fundamentals", 1993, bookId.incrementAndGet());
        BookInstance b11 = new BookInstance("Cay S. Horstmann", "Core Java: Volume II -Advanced Features", 2006, bookId.incrementAndGet());
        BookInstance b12 = new BookInstance("Noam Nisan", "The Elements of Computing Systems", 2021, bookId.incrementAndGet());
        BookInstance b13 = new BookInstance("Robert Sedgewick", "Algorithms: Part I", 2007, bookId.incrementAndGet());
        BookInstance b14 = new BookInstance("Robert Sedgewick", "Algorithms: Part II", 2014, bookId.incrementAndGet());
        BookInstance b15 = new BookInstance("Miran Lipovaca", "Learn You a Haskell for Great Good!", 2011, bookId.incrementAndGet());

        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        bookList.add(b4);
        bookList.add(b5);
        bookList.add(b6);
        bookList.add(b7);
        bookList.add(b8);
        bookList.add(b9);
        bookList.add(b10);
        /*
        bookList.add(b11);
        bookList.add(b12);
        bookList.add(b13);
        bookList.add(b14);
        bookList.add(b15);

         */


        return bookList;
    }


    public List<BookInstance> getTenBooks() {
        return books;
    }

    public List<BookInstance> getBooks(int per_page, int page, String order, String order_by) {

        // Step 1: sort the books
        //default: ascending by title


        if (order.equals("descending")) {
            if (order_by.equals("author")) {
                Collections.sort(books, BookAuthorDescending);
            } else if (order_by.equals("year")) {
                Collections.sort(books, BookYearDescending);

            } else {
                Collections.sort(books, BookTitleDescending);
            }
            //default ascending
        } else {


            //if I sort 1 time, will the books list remain sorted for the all duration of the server? depending on implementation inside BookClass or?
            if (order_by.equals("author")) {
                Collections.sort(books, BookAuthorAscending);
            } else if (order_by.equals("year")) {
                Collections.sort(books, BookYearAscending);
                //default sort by title
            } else {
                Collections.sort(books, BookTitleAscending);
            }
        }

        List<BookInstance> newList = books;

                /*
            combination: per_page; page default  => return <per_page> books or all books;
                         per_page default; page => return books on page number <page>; 10 books per page;
                         page per_page => return books on <page> if there are per_page books

            TODO: edge cases, e.g. per_page <=0 or page <=0 or page>=books.size() or per_page>books.size();or page X does not exists;

         */
        //per_page is always 10 as a default;
        //return sorted list if per_page is bigger than the amount of books or per_page <0
        //TODO: Handling illegal Input: Should I add try catch here and display an error ? but it would "break" the program
        if (per_page>books.size() || per_page<0) {
            return newList;
        }
        //TODO: if page is default -1, just return all books at once?
        if (page == -1) {
            newList = books.subList(0, per_page); //per_page is either default 10 or equals to user input
            // if a specific page is required, then do:
            return newList;


        } else {

            int currentPage = 1;

            for (int i = 0; i < books.size(); i++) {

                if (page == currentPage) {

                    int chunkSize = i + (per_page); //1234, per_page is 4 => size = 4
                    int endIndex = (chunkSize) >= books.size() ? books.size() : chunkSize;
                    newList = newList.subList(i, endIndex);
                    return newList;

                } else {
                    currentPage++;
                    i += per_page -1 ;
                }

            }
        }
        //TODO: how to handle a situation when the page given does not exist? <0 || too big
        //SO FAR if page <= 0 or page is to big, it returns a list tha is too big;


        //TODO: I could add return null here so that in case page value is illegal (<=0 or not exists because to big)
        //so far it will just return full list
        return newList;
    }











    //./gradlew bootRun


    public void printBooks() {
        for (BookInstance b: books) {
            System.out.println(b);
            System.out.println();
        }
    }

}





















// ############# not relevant  #############
// ############# not relevant  #############
// ############# not relevant  #############
// ############# not relevant  #############// ############# not relevant  #############
// ############# not relevant  #############





        // TODO: TEST WHY THIS SOLUTION DIDN'T WORK (index sublist 0 to -10

            /*




            //checks if there are more books than in books per_page;

            //later: exception / error for the case when page is bigger than amount of pages? just return 0 in that case
            //later: another exception, where e.g. page ...

            //if per_rage is 4, then page 3 should return 2 books
            int startIndex = 0;
            int endIndex = 0;
            int currentPage = 1;


            int amountOfBooksInCurrentPage = 0;

            for (int i = 0; i < books.size(); i++) {

                //first iteration: check page number and whether the page is completely iterated over;
                // functional solution?
                //capture a book;
                amountOfBooksInCurrentPage++;

                if (currentPage == page) {

                    // desired page is acquired, but there are no more books left => return what's left on the page
                    if (i + 1 == books.size() || amountOfBooksInCurrentPage == per_page) {
                        newList = books.subList(startIndex, i+1);
                        break;
                    }
                    // currentPage != page;
                } else {

                    if (amountOfBooksInCurrentPage == per_page) {
                        currentPage++;
                        amountOfBooksInCurrentPage = 0;
                        startIndex = i + 1;
                    }


                }
            }
            //java.lang.IllegalArgumentException: fromIndex(10) > toIndex(0)
         //   newList =  books.subList(startIndex, endIndex);
        }

        return newList;


    }
    */

/*

BUILD FAILED in 2s
3 actionable tasks: 1 executed, 2 up-to-date
what?

 */