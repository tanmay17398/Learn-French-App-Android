package com.example.shaurya.learnfrench;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final MediaPlayer.OnCompletionListener completion = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        // Create a list of listItem
        final ArrayList<ListItem> listItem = new ArrayList<>();

        listItem.add(new ListItem("Noir", "Black", R.drawable.color_black,R.raw.black));
        listItem.add(new ListItem("Vert", "Green", R.drawable.color_green,R.raw.green));
        listItem.add(new ListItem("jaune moutarde", "Mustard Yellow", R.drawable.color_mustard_yellow,R.raw.mustard_yellow));
        listItem.add(new ListItem("Marron", "Brown", R.drawable.color_brown,R.raw.brown));
        listItem.add(new ListItem("Rouge", "Red", R.drawable.color_red,R.raw.red));
        listItem.add(new ListItem("jaune poussiéreux", "Dusty Yellow", R.drawable.color_dusty_yellow,R.raw.dusty_yellow));
        listItem.add(new ListItem("Gris", "Gray", R.drawable.color_gray,R.raw.gray));
        listItem.add(new ListItem("Blanc", "White", R.drawable.color_white,R.raw.white));

        ListItemAdapter adapter = new ListItemAdapter(this, listItem,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ListItem row = listItem.get(position);
                /*
                * release the mediaPlayer if audio is stopped in the middle means it was already playing.
                * */
                releaseMediaPlayer();
                media = MediaPlayer.create(ColorsActivity.this, row.getAudioResourceId());
                media.start();
                media.setOnCompletionListener(completion);
//                Toast.makeText(NumbersActivity.this,"working ",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (media != null) {
            media.release();
            media = null;
        }
    }
}

