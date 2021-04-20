package com.siya.mylibraryrealworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    //to avoid errors while typing the key value each time create a constant
    public static final String BOOK_ID_KEY = "bookId";

    private TextView txtViewBookName, txtViewAuthor, txtViewPages, txtViewLongDesc;
    private Button buttonAddToCurrReading, buttonAddWantToRead, buttonAddAlreadyRead, buttonAddToFavorites;
    private ImageView imgViewBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //call the initailze method to initialize the views
        initializeViews ();                

        // get the intent from the parent activity   

        Intent intent = getIntent();
        if (null != intent){                    
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);          //and in case intent is not able to retrieve the bookid it will use the default value
                                                                                
             if (bookId != -1){                                                    
                Book inComingBook = UtilsDatabase.getInstance(this).getBookId(bookId);  
                    if (null != inComingBook){

                        //call the methods here
                        setData (inComingBook);                                    

                        handleAlreadyRead(inComingBook);
                        handleWantToReadBooks(inComingBook);
                        handleCurrentlyReading(inComingBook);
                        handleFavoriteBooks(inComingBook);

                    }
             }
        }
    }
    //when user clicks on buttonAddToFavorites button, user is navigated to the favorites list
    private void handleFavoriteBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = UtilsDatabase.getInstance(this).getFavoriteBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            buttonAddToFavorites.setEnabled(false);
        }else{
            buttonAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(UtilsDatabase.getInstance(BookActivity.this).addToMyFavorite(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, FavoriteBooksActivity.class);
                        startActivity(intent);

                    }else{

                        Toast.makeText(BookActivity.this, "Something happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //when user clicks on buttonAddToCurrReading button, user is navigated to the  list
    private void handleCurrentlyReading(final Book book) {
        ArrayList<Book> wantToReadBooks = UtilsDatabase.getInstance(this).getCurrentlyReading();           //create an array list and get the already read books from database

        boolean existInAlreadyReadBooks = false;

        for (Book b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            buttonAddToCurrReading.setEnabled(false);
        }else{
            buttonAddToCurrReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(UtilsDatabase.getInstance(BookActivity.this).addToCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingActivity.class);
                        startActivity(intent);

                    }else{

                        Toast.makeText(BookActivity.this, "Something happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //when user clicks on buttonAddAlreadyRead button, user is navigated to the  list
    private void handleWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = UtilsDatabase.getInstance(this).getWantToReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : wantToReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            buttonAddWantToRead.setEnabled(false);
        }else{
            buttonAddWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(UtilsDatabase.getInstance(BookActivity.this).addToWantToRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);

                    }else{

                        Toast.makeText(BookActivity.this, "Something happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //when user clicks on buttonAddAlreadyRead button, user is navigated to the  list
    private void handleAlreadyRead(final Book book) {

        ArrayList<Book> alreadyReadBooks = UtilsDatabase.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks){
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            buttonAddAlreadyRead.setEnabled(false);
        }else{
            buttonAddAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(UtilsDatabase.getInstance(BookActivity.this).addToAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                         Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);

                    }else{

                        Toast.makeText(BookActivity.this, "Something happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //populate the field
     
    private void setData(Book book) {
        txtViewBookName.setText(book.getName());
        txtViewAuthor.setText(book.getAuthor());
        txtViewPages.setText(String.valueOf(book.getPages()));
        txtViewLongDesc.setText(book.getLongDecs());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(imgViewBook);
    }

    private void initializeViews() {
        txtViewBookName = findViewById(R.id.txtViewBookName);
        txtViewAuthor  = findViewById(R.id.txtViewAuthor);
        txtViewPages = findViewById(R.id.txtViewPages);
        txtViewLongDesc = findViewById(R.id.txtViewLongDesc);

        buttonAddToCurrReading = findViewById(R.id.buttonAddToCurrReading);
        buttonAddWantToRead = findViewById(R.id.buttonAddWantToRead);
        buttonAddAlreadyRead = findViewById(R.id.buttonAddAlreadyRead);
        buttonAddToFavorites = findViewById(R.id.buttonAddToFavorites);

        imgViewBook = findViewById(R.id.imgViewBook);
    }
}
