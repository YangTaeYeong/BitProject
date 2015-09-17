package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgBtn_Recording;
    ImageButton imgBtn_MusicList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtn_Recording = (ImageButton) findViewById(R.id.imgBtn_RecordingMic);
        imgBtn_MusicList = (ImageButton) findViewById(R.id.imgBtn_MusicList);

        imgBtn_Recording.setOnClickListener(this);
        imgBtn_MusicList.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View v) {

        if (v == imgBtn_Recording) {
            Intent intent = new Intent(MainActivity.this, MainRecordActivity.class);
            startActivity(intent);
            // 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!

        } else if (v == imgBtn_MusicList) {
            Intent intent = new Intent(MainActivity.this, MainActivity_demo.class);
            startActivity(intent);
            // 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!

        }
    }
}
