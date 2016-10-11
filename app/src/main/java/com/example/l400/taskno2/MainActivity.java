package com.example.l400.taskno2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
DatabaseHelper databaseHelper;
    Button btnfornewentery,btnforshowjokes;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newEntryOfJokes();
        databaseHelper = new DatabaseHelper(MainActivity.this);
        btnforshowjokes = (Button)findViewById(R.id.btnclickforjokes);
        List<String> myList=new ArrayList<>();
        DatabaseHelper dbHelper=new DatabaseHelper(this);
        myList=dbHelper.getTitle();
        Log.d("K",myList.toString());
        btnforshowjokes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowDataInListVies.class);
                startActivity(intent);
            }
        });
    }


    public void newEntryOfJokes(){
        btnfornewentery = (Button)findViewById(R.id.btnenterjoke);
        btnfornewentery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EnterNewJokes.class);
                startActivity(intent);
            }
        });
    }

}
