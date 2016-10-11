package com.example.l400.taskno2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JokesInDetail extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView listView1;
    ImageView imageView;
    TextView showjokes;
    ShowDataInListVies showDataInListVies;
    String pos;
    String it;
    List<String> title = new ArrayList<>() ;
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_in_detail);
         listView1 = (ListView)findViewById(R.id.listView2);
         databaseHelper = new DatabaseHelper(JokesInDetail.this);
          showDataInListVies = new ShowDataInListVies();
         it = getIntent().getStringExtra("type");
         title = databaseHelper.getFamily(it);
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(JokesInDetail.this, android.R.layout.simple_list_item_1, title);
         listView1.setAdapter(adapter);
      listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
           String pos = listView1.getItemAtPosition(i).toString();

              builder(pos);
              return true;

          }
      });


    }

    public void builder(final String  pos){

        AlertDialog.Builder builder = new AlertDialog.Builder(JokesInDetail.this);
        builder.setMessage("Are You Sure To Delete It");

        builder.setTitle("DELETE THIS JOKE");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(JokesInDetail.this, "d", Toast.LENGTH_SHORT).show();
                databaseHelper.deleteData(pos);
                title = databaseHelper.getFamily(it);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(JokesInDetail.this, android.R.layout.simple_list_item_1, title);
                listView1.setAdapter(adapter);
//               int count =  databaseHelper.countData1(pos);
//                showDataInListVies.t3.setText(count+"");

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}

