package com.example.horizondatacontrol;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
    {

        List<Item> code;

        public RecyclerViewAdapter(List<Item> moviesList) {
            this.code = moviesList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            Item item = code.get(position);
            holder.textView.setText(item.getNumber());
            holder.textView1.setText(item.getState());
            Random r = new Random();
            int red=r.nextInt(255 - 0 + 1)+0;
            int green=r.nextInt(255 - 0 + 1)+0;
            int blue=r.nextInt(255 - 0 + 1)+0;

            GradientDrawable draw = new GradientDrawable();
            draw.setShape(GradientDrawable.OVAL);
            draw.setColor(Color.rgb(red,green,blue));
            holder.textView.setBackground(draw);


        }

        @Override
        public int getItemCount() {
            return code.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


            TextView textView ;
            TextView textView1 ;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);


                textView = itemView.findViewById(R.id.textviewTitle);
                textView1=itemView.findViewById(R.id.textviewNumber);

                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {

            }
        }

    }


