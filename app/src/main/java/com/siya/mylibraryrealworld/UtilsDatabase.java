package com.siya.mylibraryrealworld;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UtilsDatabase {
    //singleton (one instance class) in the entire application
    private static UtilsDatabase instance;

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS_KEY = "already_read_books";
    private static final String WANT_TO_READ_BOOKS_KEY = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS_KEY = "currently_reading_books";
    private static final String FAVORITE_BOOKS_KEY = "favorite_books";

    //declare shared preferences, it is an interface
    private SharedPreferences sharedPreferences;

    //singleton method constructor. gets data from shared preference 
    private UtilsDatabase(Context context) {
       sharedPreferences =  context.getSharedPreferences("database", Context.MODE_PRIVATE);

        if (null == getAllBooks()) {
            // if this is the first time the user is launching application call the method if not ignore
            initializeData();
        }

        //global access to the method
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        //if the list does not exist create a new json list
        if (null == getCurrentlyReading()) {
            editor.putString (CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getAlreadyReadBooks()) {
            editor.putString (ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();                                                       
        }
        if (null == getWantToReadBooks()) {
            editor.putString (WANT_TO_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavoriteBooks()) {
            editor.putString (FAVORITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initializeData() {
    
          //static arraylist for the shared preferences
        ArrayList<Book>books = new ArrayList<>();             

        books.add(new Book(1, "Head First Java", "Bert Bates & Kathy Sierra", 722, "https://s.pdfdrive.com/assets/thumbs/224/224c6bfec6d59734fcd89060d4a821d7.jpg",
                "A complete learning experience in Java and OOP", "A complete learning experience in Java and OOP. This Book helps you learn the Java language with" +
                "unique method that goes beyond syntax and how-to-manuals and helps you understand how to be a great programmer.\n\nYou will learn the language fundamentals, generic," +
                "threading, networking and distributed programming and you will even build a \"sink the dot com\" game and networked drum machine chat client along the way."));

        books.add(new Book(2, "Head First Android Development", "Dawn Griffiths & David Griffiths", 930, "https://s.pdfdrive.com/assets/thumbs/dbe/dbec34cbe0eb5b02be4d133592a47147.jpg",
                "A Brain - Friendly Guide", "If you have an idea for a killer Android app, this fully revised and updated edition will help you build your first" +
                "application in a jiffy.\n\nYou will learn hands-om how to structure your app, design flexible and interactive interfaces, run services in the background, make your" +
                "app work on various smart phones and tablet and much more. It is like having an experienced Android developer sitting right next to you. All you need to get started" +
                "is some Java know- how."));

        books.add(new Book(3, "Head First JavaScript", "Michael Morrison", 652, "https://s.pdfdrive.com/assets/thumbs/c5e/c5ed58b8af169aad2e3748fd2955ee14.jpg",
                "A Brain - Friendly Guide", "So you are ready to make the leap of writing HTML and CSS so scripting dynamic web application? Start here" +
                "Head first JavaScript is your guided tour to exciting and interactive web page creation. Built for your brain this book covers all the JavaScript essentials" +
                "from basic web programming techniques including variable, functions and looping to more advanced topics like form validation DOM manipulation, custom objects" +
                "debugging - and even Ajax! So get ready, responsive websites are pages away."));

        books.add(new Book(4, "A Common Sense to Data Structure and Algorithms", "Jay Wengrow", 218, "https://s.pdfdrive.com/assets/thumbs/4c4/4c488c270805256e4348a63a118d9e16.jpg",
                "Level up your core programming skills", "To keep the book somewhat language-agnostic, our examples draw from several programming languages, " +
                "including Ruby, Python, and JavaScript, so having a basic understanding of these languages would be helpful. That being" +
                "said, I’ve tried to write the examples in such a way that even if you’re familiar with a different language, you should be able to follow along. To that end, I" +
                "don’t always follow the popular idioms for each language where I feel that an idiom may confuse someone new to that particular language."));

        books.add(new Book(5, "Android Programming for Beginners", " John Horton", 698, "https://s.pdfdrive.com/assets/thumbs/25e/25ecf04119247a5286ea0ce374b2a30f.jpg",
                "Learn all the Java and Android skills you need to start making powerful mobile applications", "Android uses Java to make its apps respond, think, and communicate with users." +
                "Every Android book, even those aimed at so-called beginners, assumes at least an intermediate level of Java and at most, a fairly advanced level. So, good to excellent" +
                "Java knowledge is a prerequisite for learning Android.\n\nUnfortunately, learning Java in a completely different context to Android can" +
                "sometimes be a little dull, and some of what you learn is not directly transferable into the world of Android either." +
                "I think it makes more sense, is vastly more enjoyable, and is significantly quicker and more rewarding, to teach Java in a purely Android environment—to teach Java" +
                "with the single overriding goal of learning to develop professional standard Android apps. And that's what this book is about."));

        books.add(new Book(6, "C Programming", "Greg Perry and Dean Miller", 617, "https://www.pearsonhighered.com/assets/bigcovers/0/7/8/9/0789751984.jpg",
                "An Absolute Beginners Guide", "ePUB is an open, industry-standard format for eBooks. However, support of ePUB and its many features varies " +
                "across reading devices and applications.\n\nUse your device or app settings to customize the presentation to your liking. Settings that you can customize often " +
                "include font, font size, single or double column, landscape or portrait mode, and figures that you can click or tap to enlarge. For" +
                "additional information about the settings and features on your reading device or app, visit the device manufacturer’s Web site."));

        books.add(new Book(7, "Learn C++ Programming Language", "Tutorials Point", 54, "https://www.tutorialspoint.com/cplusplus/images/cpp-mini-logo.jpg",
                "Tutorials Point Easy learning", "C++ is a middle-level programming language developed by Bjarne Stroustrup in 1979 at Bell Labs. C++ runs on a " +
                "variety of platforms, such as Windows, Mac OS, and the various versions of UNIX. This tutorial adopts a simple and practical approach to describe the concepts " +
                "of C++."));

        books.add(new Book(8, "Data Analysis From Scratch With Python", "Peters Morgan", 104, "https://s.pdfdrive.com/assets/thumbs/bd4/bd4e122c2de48657b7c9e9487ae6406f.jpg",
                "A Step by Step Guide", "The Book give complete instructions for manipulating, processing, cleaning,modeling and crunching datasets " +
                "in Python. This is a hands-on guide with practical case studies of data analysis problems effectively. You will learn pandas, NumPy, IPython, and Jupiter in the Process."));

        books.add(new Book(9, "Data Structures And Algorithms Made Easy", "Narasimha Karumanchi", 828, "https://s.pdfdrive.com/assets/thumbs/308/3081609e34f18c035373e91b1885640d.jpg",
                "Data Structures And Algorithmic Puzzles", "It is not the main objective of this book to present you with the theorems and proofs on data" +
                "structures and algorithms. I have followed a pattern of improving the problem solutions with different complexities (for each problem, you will find multiple " +
                "solutions with different, and reduced, complexities).\n\nBasically, it’s an enumeration of possible solutions. With this approach," +
                "even if you get a new question, it will show you a way to think about the possible solutions. You will find this book useful for interview preparation, " +
                "competitive exams preparation, and campus interview preparations"));

        books.add(new Book(10, "Excel VBA Programming", "John Walkenbach", 411, "https://s.pdfdrive.com/assets/thumbs/311/311b6a170596d15ff3fe98e3a9e32b36.jpg",
                "Excel VBA Programming for Dummies", "Greetings, prospective Excel programmer. . . .\n\n" +
                "Thanks for buying my book. I think you’ll find that it offers a fast, enjoyable way to discover the ins and outs of Microsoft Excel programming. Even" +
                "if you don’t have the foggiest idea of what programming is all about, this book can help you make Excel jump through hoops in no time (well, it will" +
                "take some time).\n\n" + "Unlike most programming books, this one is written in plain English, and even normal people can understand it. Even better, it’s " +
                "filled with information of the “just the facts, ma’am” variety — and not the drivel you might need once every third lifetime."));

        books.add(new Book(11, "JavaScript and JQuery", "Jon Duckett", 645, "https://s.pdfdrive.com/assets/thumbs/e48/e4881ba4d8fe0e34794f1d6e2f6a777f.jpg",
                "JavaScript and JQuery: Interactive Front-End Web Development", "This book explains how JavaScript can be used in browsers to make websites more " +
                "interactive, interesting, and user-friendly. You will also learn about jQuery because it makes writing JavaScript a lot easier." +
                "To get the most out of this book, you will need to know how to bui ld web pages using HTML and CSS. Beyond that. no prior experience with programming is necessary."));

        books.add(new Book(12, "Full-stack web development with Vue.js and Node", "Aneeta Sharma", 358, "https://s.pdfdrive.com/assets/thumbs/332/332491573ebec55f464c1d061007b58d.jpg",
                "Full-stack web development with Vue.js and Node : build scalable and powerful web apps with modern web", "This book is designed for web " +
                "developers who are interested in learning how to build a full-stack application with only one programming language as JavaScript using the" +
                "technology stack: Mongo DB, Express.js, Vue.js, and Node.js.\n\n" +
                "This book is suitable for beginners and intermediate developers with a basic knowledge of HTML, CSS, and JavaScript. If you are a web or full-stack JavaScript " +
                "developer JavaScript developer and has tried hands on the traditional stacks, such as LAMP, MEAN, or MERN," +
                "and wish to explore a new stack with modern web technologies, then this book is for you."));

        books.add(new Book(13, "The Full Stack Developer", "Chris Northwood", 354, "https://s.pdfdrive.com/assets/thumbs/625/625293b5e90c91b2811163b573694d29.jpg",
                "Your Essential Guide to the Everyday Skills Expected of a Modern Full Stack Developer", "Your Essential Guide to the Everyday " +
                "Skills Expected of a Modern Full Stack Developer."));

        books.add(new Book(14, "JavaScript", "Stephen Blumenthal", 115, "https://s.pdfdrive.com/assets/thumbs/28a/28af716d5125df62215719b121524ebd.jpg",
                "Lear JavaScript with Ease", "From Beginner to expert in less than a week."));

        books.add(new Book(15, "Learning Web Design", "Jennifer Niederst Robbins", 810, "https://s.pdfdrive.com/assets/thumbs/033/033ce90f832de5a2bc33e1e25a48a037.jpg",
                "A Beginner's Guide to HTML, CSS, JavaScript and Web Graphics", "Do you want to build web pages but have no prior experience? This friendly" +
                " guide is the perfect place to start. You will begin at square one, learning how the web and web pages work and then steadily build from there. By the end " +
                "of this book, you will have the skills to create a simple site to create a simple site with multicolumn pages that adapt for mobile devices." +
                "\n\nEach Chapter provides exercise to help you learn various techniques and short quizzes to make sure you understand key concepts.\n\nThis thoroughly" +
                "revised edition is ideal for students and professionals of all backgrounds and skills levels. It is simple and clear enough for beginners. yet thorough" +
                "enough to be a useful reference for experienced developers keeping their skills up to date."));

        books.add(new Book(16, "Head First HTML and CSS", "Elisabeth Robson and Eric Freeman", 764, "https://s.pdfdrive.com/assets/thumbs/2fd/2fd504621adff4316d937a544df25af8.jpg",
                "A Learner’s Guide to Creating Standards-Based Web Pages", "Tired of reading HTML books that only make sense after you are an expert? Then it is" +
                "about time you picked up the newly revised Head First HTML and CSS and really learn HTML. You want to learn HTML and CSS so you can finally create those web pages" +
                "you have always wanted, so you can communicate more effectively with friends, family, fans and fanatic customers. You also want to do it right, using the latest " +
                "HTML5 standards, so you can actually maintain and expand your web pages over time so they work in all browsers and mobile devices."));

       books.add(new Book(17, "Head First C", "David Griffiths and Dawn Griffiths", 632, "https://s.pdfdrive.com/assets/thumbs/641/641a4ce572716beafc63f241aef979d0.jpg",
                "A Brain - Friendly Guide", "Head First C is an accessible, light-hearted introduction to C programming, in the classic Head First style. " +
               "Pictures, jokes, exercises, and labs take the reader gently but steadily through the fundamentals of C— including arrays, pointers, structs, and functions—before " +
               "moving into more advanced topics in Posix and Linux system programming, such as processes and threads."));

        books.add(new Book(18, "PHP, MySQL, JavaScript & HTML5", "Steve Suehring and Janet Valade", 724, "https://s.pdfdrive.com/assets/thumbs/e1e/e1ec7abe963086aec83a0335c51d2fce.jpg",
                "PHP, MySQL, JavaScript & HTML5.All-in-One For Dummies", "This book looks at many aspects of web development, including the language" +
                "used to make web pages and ways to make web pages look good, make web pages accept information from visitors, and create programs to create other web pages! " +
                "If that seems like a lot of information, don’t worry. It’s all broken up into manageable pieces so that you can consume the information" +
                "at your own pace.\n\nThis book is intended as both a reference and, in certain places, a tutorial.Most of the information in the book doesn’t need to be read " +
                "in a certain order. However, certain areas build on each other and, if you find that you’re stuck in one of the later chapters, you might find that reading an" +
                "earlier chapter will reveal the information that you need."));


        books.add(new Book(19, "Head First SQL", "Lynn Beighley", 586, "https://s.pdfdrive.com/assets/thumbs/09e/09ec05b185482fbd0079f34fe91a876d.jpg",
                "A Brain - Friendly Guide", "A  Brain - Friendly Guide"));


        //saving all books in shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

         Gson gson = new Gson();
         editor.putString (ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();


    }

    //this method checks to see if there has not been any instance created
    public static UtilsDatabase getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else {
            instance = new UtilsDatabase(context);
            return instance;
        }
    }

    //retrieve/get  the json file from the list
    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null), type);
        return books;
    }

    public  ArrayList<Book> getCurrentlyReading() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book>books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY, null),type);
        return books;
    }

    public  ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book>books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY, null),type);
        return books;
    }

    public  ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList <Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS_KEY, null),type);
        return books;
    }

    public  ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList <Book> books = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY, null),type);
        return books;
    }

    //retrieve the book id from the all books list
    public Book getBookId(int id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books){
            for (Book b : books) {
                if (b.getId() == id)
                    return b;
                }
        }
        return null;
    }

    //create add methods to add books to array list when the button in the book activity is clicked
    //clear the old list and refresh it with updated list
    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS_KEY);
                editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
        }
    }
    return false;
 }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS_KEY);
                editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;

    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReading();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS_KEY);
                editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;

    }

    public boolean addToMyFavorite(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    // create methods to remove books when the delete button is clicked
    //  clear the old list and refresh it with updated list
    public boolean removeFromAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS_KEY);
                        editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
    return false;
    }

   
    public boolean removeFromWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();                            
        if (null != books){                                                       
            for(Book b: books){
                if(b.getId() == book.getId()){                                  
                    if(books.remove(b)){                                        
                        Gson gson = new Gson();                                 
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS_KEY);                  
                        editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReading();
        if (null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS_KEY);
                        editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavoriteBooks(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books){
            for(Book b: books){
                if(b.getId() == book.getId()){
                    if(books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
