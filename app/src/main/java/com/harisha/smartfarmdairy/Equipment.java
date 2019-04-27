package com.harisha.smartfarmdairy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Equipment extends AppCompatActivity {
    WebAdapter adapter;
    RecyclerView recyclerView;
    List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);

        list=new ArrayList<>();
        list.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ppHnsNcPLzM\" frameborder=\"0\" allowfullscreen></iframe>");
        list.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/FNn5DB1Zen4\" frameborder=\"0\" allowfullscreen></iframe>");
        list.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/uIgpJMXIIhM\" frameborder=\"0\" allowfullscreen></iframe>");
       list.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/pktOc4ulwF8\" frameborder=\"0\" allowfullscreen></iframe>");
       list.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/MYePXkxSaQE\" frameborder=\"0\" allowfullscreen></iframe>");

        adapter=new WebAdapter(list,this);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
