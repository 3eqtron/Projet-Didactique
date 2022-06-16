package com.example.horizondatacontrol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DhtLearn extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerAdapter;
    ImageButton imageButton;
    Button button;
    List<Item> codelist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dht_learn);
        recyclerView=findViewById(R.id.recycler);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),DhtControl.class);
                startActivity(i);
                finish();
            }
        });
        codelist = new ArrayList<>();

        codelist.add(new Item("#include \"DHT.h\" ","1"));
        codelist.add(new Item("#define DHTPIN 4","2"));

        codelist.add(new Item("#define DHTTYPE DHT11 ","3"));
        codelist.add(new Item("DHT dht(DHTPIN, DHTTYPE);","4"));
        codelist.add(new Item("void setup() {","5"));
        codelist.add(new Item("Serial.begin(9600);","6"));
        codelist.add(new Item("dht.begin() };","7"));
        codelist.add(new Item("void loop() {;","8"));
        codelist.add(new Item("delay(2000);","9"));
        codelist.add(new Item("float h = dht.readHumidity();","10"));
        codelist.add(new Item("float t = dht.readTemperature();","11"));
        codelist.add(new Item("Serial.print(F(\"Humidity & Temperature: \"));","12"));
        codelist.add(new Item("Serial.print(h + t);","13"));
        codelist.add(new Item("}","14"));
        Collections.shuffle(codelist);


        imageButton=findViewById(R.id.imageButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retser=new Intent (getApplicationContext(),DescriptionLcd.class);
                startActivity(retser);
                finish();
            }
        });  Collections.shuffle(codelist);



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
