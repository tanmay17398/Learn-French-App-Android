package com.example.shaurya.learnfrench;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final MediaPlayer.OnCompletionListener completion = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };
        // Create a list of listItem
        final ArrayList<ListItem> listItem = new ArrayList<>();

        listItem.add(new ListItem("Un", "one", R.drawable.number_one, R.raw.one));
        listItem.add(new ListItem("Deux", "two", R.drawable.number_two, R.raw.two));
        listItem.add(new ListItem("Trois", "three", R.drawable.number_three, R.raw.three));
        listItem.add(new ListItem("Quatre", "Four", R.drawable.number_four, R.raw.four));
        listItem.add(new ListItem("Cinq", "Five", R.drawable.number_five, R.raw.five));
        listItem.add(new ListItem("Six", "Six", R.drawable.number_six, R.raw.six));
        listItem.add(new ListItem("Sept", "Seven", R.drawable.number_seven, R.raw.seven));
        listItem.add(new ListItem("Huit", "Eight", R.drawable.number_eight, R.raw.eight));
        listItem.add(new ListItem("Neuf", "Nine", R.drawable.number_nine, R.raw.nine));
        listItem.add(new ListItem("Dix", "Ten", R.drawable.number_ten, R.raw.ten));

        ListItemAdapter adapter = new ListItemAdapter(this, listItem, R.color.category_numbers);

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
                media = MediaPlayer.create(NumbersActivity.this, row.getAudioResourceId());
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