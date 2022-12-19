package com.example.bookmanagementapp.book;


import java.util.Comparator;

public class BookInstance {


    private String author;
    private String title;
    private int year;
    private long bookId;




    public BookInstance(String author, String title, int year, long bookId) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.bookId = bookId;
    }
    @Override
    public String toString() {
        return "Author: " + author + " title: " + title + " id: " + bookId;
    }



    public long getBookId() {
        return this.bookId;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }


    //queston: anonoymous/functional interface? what does it called?
    //question: 6 comparators for descending/ascending order or reverse - reverse should result in slighlty less performance?
    public static Comparator<BookInstance> BookAuthorAscending = new Comparator<BookInstance> () {
        public int compare(BookInstance b1, BookInstance b2) {
            return String.CASE_INSENSITIVE_ORDER.compare(b1.getAuthor(), b2.getAuthor());
        }
    };


    public static Comparator<BookInstance> BookTitleAscending = new Comparator<BookInstance> () {
        public int compare(BookInstance b1, BookInstance b2) {
            return String.CASE_INSENSITIVE_ORDER.compare(b1.getTitle(), b2.getTitle());
        }
    };

    public static Comparator<BookInstance> BookYearAscending = new Comparator<BookInstance> () {
        public int compare(BookInstance b1, BookInstance b2) {
            //ascending order
            return (b1.getYear() - b2.getYear());
            //descending order
            //b2.getYear() - b1.getYear();

        }
    };

    public static Comparator<BookInstance> BookAuthorDescending = new Comparator<BookInstance> () {
        public int compare(BookInstance b1, BookInstance b2) {
            return String.CASE_INSENSITIVE_ORDER.compare(b2.getAuthor(),b1.getAuthor());
        }
    };


    public static Comparator<BookInstance> BookTitleDescending = new Comparator<BookInstance> () {
        public int compare(BookInstance b1, BookInstance b2) {
            return String.CASE_INSENSITIVE_ORDER.compare(b2.getTitle(),b1.getTitle());
        }
    };

    public static Comparator<BookInstance> BookYearDescending = new Comparator<BookInstance> () {
        public int compare(BookInstance b1, BookInstance b2) {
            //ascending order
            return b2.getYear() - b1.getYear();
            //descending order
            //b2.getYear() - b1.getYear();

        }
    };



}
