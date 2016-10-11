package com.example.l400.taskno2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EnterNewJokes extends AppCompatActivity {

    Button insertdata;
    EditText title, description;
    Spinner spinner;
    DatabaseHelper datahelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_jokes);
        insertdata = (Button)findViewById(R.id.insertdata);
        title = (EditText)findViewById(R.id.joketitle);
        description = (EditText)findViewById(R.id.jokedescription);
        spinner = (Spinner)findViewById(R.id.spinner);
        datahelper = new DatabaseHelper(EnterNewJokes.this);

        insertdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tit = title.getText().toString();
                String desc = description.getText().toString();
                String typ = spinner.getSelectedItem().toString();

                boolean insert = datahelper.insertdata(tit,desc,typ);
                if(insert == true){
                    Toast.makeText(EnterNewJokes.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(EnterNewJokes.this, "data not inserted succfully", Toast.LENGTH_SHORT).show();
                }
                


            }
        });
    }




}
