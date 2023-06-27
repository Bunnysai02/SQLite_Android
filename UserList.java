package com.location.recyclerviewsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class UserList extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> name,email,age;
    DBHelper DB;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        DB= new DBHelper(this);
        name= new ArrayList<>();
        email= new ArrayList<>();
        age= new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);
        myAdapter=new MyAdapter(this,name,email,age);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor= DB.getData();
        if(cursor.getCount()==0){
            Toast.makeText(UserList.this, "No entry exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            while (cursor.moveToNext())
            {
                name.add(cursor.getString(0));
                email.add(cursor.getString(1));
                age.add(cursor.getString(2));
            }
        }
    }
}