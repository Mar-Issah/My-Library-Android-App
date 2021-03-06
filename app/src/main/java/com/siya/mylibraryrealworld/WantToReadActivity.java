package com.siya.mylibraryrealworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class WantToReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);

         //create an instance of the recycler view and RecyclerViewAdapter and set the adapter
        RecyclerView recyclerView = findViewById(R.id.wantToReadRecyclerView);

        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this,"WantToReadActivity");

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        adapter.setBooks(UtilsDatabase.getInstance(this).getWantToReadBooks());
    }

         @Override
        public void onBackPressed() {
        Intent intent = new Intent(WantToReadActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
        //use the setflag method(s) to clear previous task | press again the application will exit
        startActivity(intent);
    }

}

