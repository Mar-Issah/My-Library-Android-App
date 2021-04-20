package com.siya.mylibraryrealworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alreaddy_read_book);

        RecyclerView recyclerView = findViewById(R.id.alReadBookRecyclerView);

        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, "AlreadyReadBookActivity");

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter.setBooks(UtilsDatabase.getInstance(this).getAlreadyReadBooks());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AlreadyReadBookActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}