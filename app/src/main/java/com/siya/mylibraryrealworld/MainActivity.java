package com.siya.mylibraryrealworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSeeAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWishList, btnMyFavorites, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        // tell android what to do when user clicks on eacach button
        btnSeeAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SeeAllBooksActivity.class);
                startActivity(intent);

            }
        });
        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CurrentlyReadingActivity.class);
                startActivity(intent);


            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlreadyReadBookActivity.class);
                startActivity(intent);


            }
        });
        btnWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WantToReadActivity.class);
                startActivity(intent);
            }
        });
        btnMyFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoriteBooksActivity.class);
                startActivity(intent);
            }
        });
        //when the user clicks on the about button it opens a dialog box
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);           //create a dialog box to navigate user to website
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and developed by Marsiya at siya.com.\n" +
                        "Check my website for more awesome applications.");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent (MainActivity.this, WebsiteActivity.class);          //when you click on the visit, it take to the website activity
                        intent.putExtra("url", "https://www.google.com/");
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });

        UtilsDatabase.getInstance(this);
    }

    private void initializeViews() {
        btnSeeAllBooks = findViewById(R.id.btnSeeAllBooks);
        btnCurrentlyReading= findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead= findViewById(R.id.btnAlreadyRead);
        btnWishList= findViewById(R.id.btnWishList);
        btnMyFavorites= findViewById(R.id.btnMyFavorites);
        btnAbout= findViewById(R.id.btnAbout);

    }
}