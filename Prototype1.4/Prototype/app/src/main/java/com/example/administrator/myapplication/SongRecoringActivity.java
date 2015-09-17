package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SongRecoringActivity extends AppCompatActivity {

    private Button btn_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_recoring);

        btn_result = (Button)findViewById(R.id.btn_result);
        btn_result.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent in = new Intent(SongRecoringActivity.this, RecordingResultActivity.class);
//                in.putExtra("TITLE", title);
////                in.putExtra("ARTIST", artist);
////                in.putExtra("GENRE", genre);
//                in.putExtra("VALUE", data.getImageUrl());
//                in.putExtra("Mp3URL",data.getMp3Url());
                startActivity(in);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_song_recoring, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
