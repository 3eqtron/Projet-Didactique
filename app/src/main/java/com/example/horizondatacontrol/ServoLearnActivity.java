package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class ServoLearnActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerAdapter;
ImageButton imageButton;
Button button;
    List<Item> codelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servo_learn);
        recyclerView=findViewById(R.id.recycler);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ServoContolActivity.class);
                startActivity(i);
                finish();
            }
        });

        codelist = new ArrayList<>();


        codelist.add(new Item("#include <Servo.h>","1"));
        codelist.add(new Item("Servo servo;","2"));

        codelist.add(new Item("servo.attach(2);","3"));
        codelist.add(new Item("servo.write(0);","4"));
        codelist.add(new Item("delay(2000);","5"));
        codelist.add(new Item("}","6"));
        codelist.add(new Item("void loop() {","7"));
        codelist.add(new Item("servo.write(90);","8"));
        codelist.add(new Item("delay(1000);\");","9"));
        codelist.add(new Item("servo.write(0);","10"));
        codelist.add(new Item("delay(1000);","11"));
        codelist.add(new Item("}","12"));


            Collections.shuffle(codelist);


        imageButton=findViewById(R.id.imageButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retser=new Intent (getApplicationContext(),DescriptionServo.class);
                startActivity(retser);
                finish();
            }
        });

        recyclerAdapter = new RecyclerViewAdapter(codelist);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(codelist, fromPosition, toPosition);

          int [] i=new int[codelist.size()];
          for (int n=0;n<codelist.size();n++){

              i[n]=codelist.indexOf(codelist.get(n));
           if (i[n]==toPosition){
               Toast.makeText(getApplicationContext(),"Bravo",Toast.LENGTH_SHORT).show();
           }

          }


            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);


            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

}
