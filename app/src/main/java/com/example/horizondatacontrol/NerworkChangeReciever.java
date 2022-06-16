package com.example.horizondatacontrol;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import es.dmoral.toasty.Toasty;

public class NerworkChangeReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Common.isConnectedToInternet(context)){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            View layout_dialog= LayoutInflater.from(context).inflate(R.layout.checkinternet,null);
            AppCompatButton btnretry=layout_dialog.findViewById(R.id.btncx);
builder.setView(layout_dialog);
AlertDialog dialog=builder.create();
dialog.show();
dialog.setCancelable(false);
dialog.getWindow().setGravity(Gravity.CENTER);
btnretry.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dialog.dismiss();
        onReceive(context,intent);
    }
});
        }

    }
}