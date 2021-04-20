package com.siya.mylibraryrealworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);

                //create an instance of the recycler view and RecyclerViewAdapter and set the adapter
        RecyclerView recyclerView = findViewById(R.id.currentlyReadingRecyclerView);

        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, "CurrentlyReadingActivity");

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter.setBooks(UtilsDatabase.getInstance(this).getCurrentlyReading());
    }

    //so when you press back to it takes you to previous activity.
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CurrentlyReadingActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        //use the setflag method(s) to clear previous task | press again the application will exit
        startActivity(intent);
    }

}
