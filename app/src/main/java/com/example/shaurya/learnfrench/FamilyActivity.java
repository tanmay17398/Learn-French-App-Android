package com.example.shaurya.learnfrench;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        final MediaPlayer.OnCompletionListener completion = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        // Create a list of listItem
        final ArrayList<ListItem> listItem = new ArrayList<>();

        listItem.add(new ListItem("grand-mère", "GrandMother", R.drawable.family_grandmother, R.raw.grandmother));
        listItem.add(new ListItem("grand-PÈRE", "GrandFather", R.drawable.family_grandfather, R.raw.grandfather));
        listItem.add(new ListItem("père", "Father", R.drawable.family_father, R.raw.father));
        listItem.add(new ListItem( "mère", "Mother", R.drawable.family_mother, R.raw.mother));
        listItem.add(new ListItem("soeur aînée", "Elder Sister", R.drawable.family_older_sister, R.raw.elder_sister));
        listItem.add(new ListItem("frère aîné", "Elder Brother", R.drawable.family_older_brother, R.raw.elder_brother));
        listItem.add(new ListItem("fille", "Daughter", R.drawable.family_daughter, R.raw.daughter));
        listItem.add(new ListItem("fils", "Son", R.drawable.family_son, R.raw.son));
        listItem.add(new ListItem("sœur cadette", "Younger Sister", R.drawable.family_younger_sister, R.raw.younger_sister));
        listItem.add(new ListItem("frère cadet", "Younger Brother", R.drawable.family_younger_brother, R.raw.younger_brother));

        ListItemAdapter adapter = new ListItemAdapter(this, listItem, R.color.category_family);

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
                media = MediaPlayer.create(FamilyActivity.this, row.getAudioResourceId());
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

