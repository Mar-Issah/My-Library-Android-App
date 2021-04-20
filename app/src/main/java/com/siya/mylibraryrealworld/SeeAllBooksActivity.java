package com.siya.mylibraryrealworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class SeeAllBooksActivity extends AppCompatActivity {

    private RecyclerView booksRecyclerView;
    private BookRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_books);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         //create an instance of the recycler view and RecyclerViewAdapter and set the adapter
        booksRecyclerView = findViewById(R.id.booksRecyclerView);
        adapter = new BookRecyclerViewAdapter(this,"SeeAllBooksActivity");

        booksRecyclerView.setAdapter(adapter);

        booksRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter.setBooks(UtilsDatabase.getInstance(this).getAllBooks());
    }

    //same functionality as the code for on back pressed
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
