package com.example.administrator.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class RecommendListActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private ListView m_ListView; // 메인 리스트뷰
    ArrayList<Item> alist;
    private RecommendListAdapter m_Adapter; // 메인 어댑터
    private ImageButton playButton;
    private boolean isPlaying;
    private Button streamButton;
    private TextView textStreamed;
    private ProgressBar progressBar;
    private StreamingMediaPlayer audioStreamer;
    private Item item;
    // URL to get contacts JSON
    private static String url = "http://220.149.119.118:8082/proj_server/getJson.do";

    // JSON Node names
    private static final String TAG_MUSIC = "music";
    private static final String TAG_TITLE = "title";
    private static final String TAG_GENRE = "genre";
    private static final String TAG_ARTIST = "artist";
    private static final String TAG_NUM = "number";
    private static final String TAG_GENDER = "gender";
    private String output = null;
    // contacts JSONArray
    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_list);

        /////////////////

        Intent in = getIntent();
//
//        // Get JSON values from previous intent
        output = in.getStringExtra("output");

        ///////////////

        contactList = new ArrayList<HashMap<String, String>>();
        alist = new ArrayList<Item>(); // 메인 리스트 아이템 객체 생성
        m_ListView = (ListView) findViewById(R.id.listview); // 메인 리스트뷰 연결
        m_Adapter = new RecommendListAdapter(RecommendListActivity.this, alist); // 메인 어댑터 생성
        m_ListView.setAdapter(m_Adapter);


//        m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                // getting values from selected ListItem
//                String title = ((TextView) view.findViewById(R.id.txt_title))
//                        .getText().toString();
//
//
//
//
//                // Starting single contact activity
//                Intent in = new Intent(getApplicationContext(),
//                        DetailActivity.class);
//                in.putExtra("TITLE", title);
////                in.putExtra("ARTIST", artist);
////                in.putExtra("GENRE", genre);
//                in.putExtra("VALUE", item.getImageUrl());
//                startActivity(in);
//
//            }
//        });
        // Calling async task to get json
        new GetContacts().execute();

    }





    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(RecommendListActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();

            // Making a request to url and getting response
            //String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + output);

            if (output != null) {
                try {
                    JSONObject jsonObj = new JSONObject(output);

                    // Getting JSON Array node
                    contacts = jsonObj.getJSONArray(TAG_MUSIC);

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        Item item = new Item();

                        //item.setId( c.getString(TAG_NUM));
                        item.setTitle(c.getString(TAG_TITLE));
                        item.setArtist(c.getString(TAG_ARTIST));
                        item.setGenre(c.getString(TAG_GENRE));
                        //item.setId(i+1);

                        item.setId(Integer.parseInt(c.getString(TAG_NUM)));


                        alist.add(i,item);
                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();



        }

    }


}