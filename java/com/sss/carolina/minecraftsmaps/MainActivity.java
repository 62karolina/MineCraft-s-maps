package com.sss.carolina.minecraftsmaps;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.IntentSender;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends ListActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String[] values = new String[] { "Fly Dream", "Halloween Town", "Tower Defense" };

        setListAdapter(new MyArrayAdapter(this, values));
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        switch (item){
            case "Fly Dream":
                Intent intent = new Intent(MainActivity.this, FlyDream.class);
                startActivity(intent);
                break;
            case "Halloween Town":
                Intent intent1 = new Intent(MainActivity.this, Halloween.class);
                startActivity(intent1);
                break;
            case "Tower Defense":
                Intent intent2 = new Intent(MainActivity.this, Tower.class);
                startActivity(intent2);
                break;
        }
    }








}
