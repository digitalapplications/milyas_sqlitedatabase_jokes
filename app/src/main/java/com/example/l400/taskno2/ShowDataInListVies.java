package com.example.l400.taskno2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowDataInListVies extends AppCompatActivity {

    ListView listView;
    List<String> tit = new ArrayList<>();
    DatabaseHelper databaseHelper;
   public static TextView t2,t3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_in_list_vies);
        listView = (ListView)findViewById(R.id.listView);
        databaseHelper = new DatabaseHelper(ShowDataInListVies.this);
        databaseHelper.open();
        tit = databaseHelper.getTitle();

        Log.d("ddd" ,tit.toString());
        ArrayAdapter customarray = new Customarray(ShowDataInListVies.this,tit );
        listView.setAdapter(customarray);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShowDataInListVies.this,JokesInDetail.class);
                String ty = listView.getItemAtPosition(i).toString();

                intent.putExtra("type", ty);
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        tit = databaseHelper.getTitle();
        Log.d("ddd" ,tit.toString());
        ArrayAdapter customarray = new Customarray(ShowDataInListVies.this,tit );
        listView.setAdapter(customarray);



    }

    public class Customarray extends ArrayAdapter<String>{
      Context context;
    List<String> tit;

    public Customarray(Context context, List<String> tit) {
        super(context,R.layout.custom_layout, tit);
        this.context = context;
        this.tit = tit;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String title = tit.get(position);
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout,null);
        ImageView imageView = (ImageView)view.findViewById(R.id.i);

        t2 = (TextView)view.findViewById(R.id.textView2);
        t3 = (TextView)view.findViewById(R.id.textView3);

          String d = new String();
        d = databaseHelper.getDescription(title);
         int res = getResources().getIdentifier(d.toString(),"drawable",getPackageName());
        imageView.setImageResource(res);
        Log.d("image" , d.toString());
        int count = databaseHelper.countData(title);

        t2.setText(tit.get(position));
        t3.setText(count+"");

        return view;



    }


}


}
