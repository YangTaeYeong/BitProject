package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class MainRecordActivity extends Activity {
    static final String RECORDED_FILE = "/sdcard/recorded.mp3";

    MediaPlayer player;
    MediaRecorder recorder;
    String url = "http://220.149.119.118/kalimba.mp3"; // your URL here
    MyService ms = new MyService();
    //MediaPlayer mediaPlayer = new MediaPlayer();


    int playbackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_record);

        Button recordBtn = (Button) findViewById(R.id.recordBtn);
        Button recordStopBtn = (Button) findViewById(R.id.recordStopBtn);
        Button playBtn = (Button) findViewById(R.id.playBtn);
        Button playStopBtn = (Button) findViewById(R.id.playStopBtn);
        Button streamingBtn = (Button)findViewById(R.id.StreamingBtn);
        Button streamingStopBtn = (Button)findViewById(R.id.StreamingStopBtn);

        recordBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(recorder != null){
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                }// TODO Auto-generated method stub
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(RECORDED_FILE);
                try{
                    Toast.makeText(getApplicationContext(),
                            "녹음을 시작합니다.", Toast.LENGTH_LONG).show();
                    recorder.prepare();
                    recorder.start();
                }catch (Exception ex){
                    Log.e("SampleAudioRecorder", "Exception : ", ex);
                }
            }
        });
        recordStopBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(recorder == null)
                    return;

                recorder.stop();
                recorder.release();
                recorder = null;

                Toast.makeText(getApplicationContext(),
                        "녹음이 중지되었습니다.", Toast.LENGTH_LONG).show();
                // TODO Auto-generated method stub

            }
        });




        playBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                try{
                    playAudio(RECORDED_FILE);

                    Toast.makeText(getApplicationContext(), "음악파일 재생 시작됨.", Toast.LENGTH_SHORT).show();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        playStopBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(player != null){
                    playbackPosition = player.getCurrentPosition();
                    player.pause();
                    Toast.makeText(getApplicationContext(), "음악 파일 재생 중지됨.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        streamingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "스트리밍 재생.", Toast.LENGTH_SHORT).show();

//                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                    try {
//                        mediaPlayer.setDataSource(url);
//                        mediaPlayer.prepare(); // might take long! (for buffering, etc)
//                        mediaPlayer.start();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                }

        });

        streamingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

        });

        streamingStopBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


//                mediaPlayer.stop();
//                mediaPlayer.release();


            }

        });


    }

    public class MyService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
        private static final String ACTION_PLAY = "com.example.action.PLAY";
        MediaPlayer mMediaPlayer = null;

        public int onStartCommand(Intent intent, int flags, int startId) {

            if (intent.getAction().equals(ACTION_PLAY)) {
                mMediaPlayer = new MediaPlayer(); // initialize it here
                mMediaPlayer.setOnPreparedListener(this);
                mMediaPlayer.prepareAsync(); // prepare async to not block main thread
            }
            return 0;
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        /** MediaPlayer가 준비되면 호출된다 */
        public void onPrepared(MediaPlayer player) {
            player.start();
        }

        public void initMediaPlayer() {
            // ...MediaPlayer를 초기화하는 함수...

            mMediaPlayer.setOnErrorListener(this);
        }

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            // ... 에러를 처리하기 ...
            // MediaPlayer를 error 상태로 돌입하였고, reset이 필요하다.
            return false;
        }

        @Override
        public void onDestroy() {
            if (mMediaPlayer != null) mMediaPlayer.release();
        }
    }


    private void playAudio(String url) throws Exception{
        killMediaPlayer();

        player = new MediaPlayer();
        player.setDataSource(url);
        player.prepare();
        player.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if(player != null){
            try {
                player.release();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    protected void onPause(){
        if(recorder != null){
            recorder.release();
            recorder = null;
        }
        if (player != null){
            player.release();
            player = null;
        }

        super.onPause();

    }
}