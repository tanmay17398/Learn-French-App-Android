package com.example.shaurya.learnfrench;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final MediaPlayer.OnCompletionListener completion = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        // Create a list of listItem
        final ArrayList<ListItem> listItem = new ArrayList<>();

        listItem.add(new ListItem("où allez-vous", "Where are you Going?", R.raw.where_going));
        listItem.add(new ListItem("Comment vous appelez-vous?", "What's your name?", R.raw.what_name));
        listItem.add(new ListItem("Mon nom est ", "My name is...", R.raw.my_name_is));
        listItem.add(new ListItem("comment allez-vous?", "How are you feeling?", R.raw.how_feeling));
        listItem.add(new ListItem("je me sens bien", "I am feeling Good", R.raw.feeling_well));
        listItem.add(new ListItem("Viens-tu?", "Are you Coming?", R.raw.are_you_coming));
        listItem.add(new ListItem("oui, je viens.", "Yes, I am Coming", R.raw.yes_coming));
        listItem.add(new ListItem("Allons-y", "Let's Go", R.raw.lets_go));
        listItem.add(new ListItem("Venez ici", "Come Here", R.raw.come_here));
        listItem.add(new ListItem("Bonjour!", "Good Morning", R.raw.good_morning));
        listItem.add(new ListItem("Bon voyage!", "Good Journey", R.raw.good_journey));
        listItem.add(new ListItem("Je t'aime", "I LOVE YOU", R.raw.i_love_you));

        ListItemAdapter adapter = new ListItemAdapter(this, listItem, R.color.category_phrases);

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
                media = MediaPlayer.create(PhrasesActivity.this, row.getAudioResourceId());
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
