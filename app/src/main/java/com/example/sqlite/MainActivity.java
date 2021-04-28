package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqlite.adapter.TemanAdapter;
import com.example.sqlite.database.DBController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.AnimatorDurationScaleProvider;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<Teman> temanArrayList;
    DBController controller = new DBController(this);
    String id, nm, tlp;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        BacaData();
        adapter = new TemanAdapter(temanArrayList);
        fab = findViewById(R.id.floatingButton);

        //membuat layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TemanBaru.class);
                startActivity(intent);
            }
        });
    }

    public void  BacaData(){
        ArrayList<HashMap<String , String >> daftarTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();

        //memindah dari hasil query ke dalam teman
        for(int i = 0; i < daftarTeman.size(); i++){
            Teman teman = new Teman();

            teman.setId(daftarTeman.get(i).get("id"));
            teman.setNama(daftarTeman.get(i).get("nama"));
            teman.setTelpon(daftarTeman.get(i).get("telpon"));

            //memindahkan dari Teman  kedalam ArrayList teman di adapter
            temanArrayList.add(teman);
        }
    }
}