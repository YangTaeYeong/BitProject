package com.example.administrator.myapplication;

/**
 * Created by Administrator on 2015-08-28.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

class RecommendListAdapter extends BaseAdapter {
    // 레이아웃 XML을 읽어들이기 위한 객체
    private LayoutInflater mInflater;
    private boolean isPlaying;
    private ArrayList<Item> itemList;
    private StreamingMediaPlayer audioStreamer;
    private Context context;
    private LinearLayout layout;

    public RecommendListAdapter(Context context, ArrayList<Item> itemList) {
        // 상위 클래스의 초기화 과정
        // context, 0, 자료구조
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemList = itemList;
        this.context = context;

    }


    // 자신이 만든 xml의 스타일로 보이기 위한 구문
    @Override
    public View getView(int position, View v, final ViewGroup parent) {
        if (v == null) {
            // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
            v = mInflater.inflate(R.layout.custom_recommend_list, null);
        }


        TextView tv_title = (TextView) v.findViewById(R.id.txt_title);
        final TextView textStreamed = (TextView) v.findViewById(R.id.text_kb_streamed);
        final ImageButton streamButton = (ImageButton) v.findViewById(R.id.button_stream);
        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        final ImageButton playButton = (ImageButton) v.findViewById(R.id.button_play);
        ImageButton recordButton = (ImageButton) v.findViewById(R.id.recordSongBtn);
        layout = (LinearLayout)v.findViewById(R.id.goDetailLayout);


        final Item data = itemList.get(position);
        if (data != null) {
            // 화면 출력
            tv_title.setText(data.getArtist() + " - " + data.getTitle());
        }


        recordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent in = new Intent(parent.getContext(),
                        SongRecoringActivity.class);
//                in.putExtra("TITLE", title);
////                in.putExtra("ARTIST", artist);
////                in.putExtra("GENRE", genre);
//                in.putExtra("VALUE", data.getImageUrl());
//                in.putExtra("Mp3URL",data.getMp3Url());
                parent.getContext().startActivity(in);

            }
        });



        // 자료를 받는다.
        //item = (Item)m_Adapter.getItem(position);

        streamButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    if (audioStreamer != null) {
                        audioStreamer.interrupt();
                    }
                    audioStreamer = new StreamingMediaPlayer(context, textStreamed, playButton, streamButton, progressBar);
                    //audioStreamer.startStreaming("http://www.pocketjourney.com/downloads/pj/tutorials/audio.mp3",1717, 214);
                    audioStreamer.startStreaming("http://220.149.119.118/BitProject/Mp3/" + data.getId() + ".mp3", 5208, 216);
                    //streamButton.setEnabled(false);
                    playButton.setImageResource(R.drawable.button_pause);
                } catch (IOException e) {
                    Log.e(getClass().getName(), "Error starting to stream audio.", e);
                }
            }
        });

        playButton.setEnabled(false);
        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {


                if (audioStreamer.getMediaPlayer().isPlaying()) {
                    audioStreamer.getMediaPlayer().pause();
                    playButton.setImageResource(R.drawable.button_play);
                } else {
                    audioStreamer.getMediaPlayer().start();
                    audioStreamer.startPlayProgressUpdater();
                    playButton.setImageResource(R.drawable.button_pause);
                }
                isPlaying = !isPlaying;

            }


        });


        layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = data.getTitle();
                String artist = data.getArtist();
                String genre = data.getGenre();
                // Starting single contact activity
                Intent in = new Intent(parent.getContext(),
                        DetailActivity.class);
                in.putExtra("TITLE", title);
                in.putExtra("ARTIST", artist);
                in.putExtra("GENRE", genre);
                in.putExtra("VALUE", data.getImageUrl());
                in.putExtra("Mp3URL",data.getMp3Url());
                parent.getContext().startActivity(in);

            }


        });



        // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
        return v;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}

