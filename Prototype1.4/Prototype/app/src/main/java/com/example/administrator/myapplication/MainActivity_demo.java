package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MainActivity_demo extends AppCompatActivity implements View.OnClickListener {

    private Button btn_man;
    private Button btn_woman;
    private String gender = ""; //성별
    String url = "http://220.149.119.118:8082/proj_server/Recommendation.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo);

        btn_man = (Button) findViewById(R.id.btn_man);
        btn_woman = (Button) findViewById(R.id.btn_woman);

        btn_man.setOnClickListener(this);
        btn_woman.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent in = new Intent(getApplicationContext(),
                MusicListActivity.class);

        switch (v.getId()) {
            case R.id.btn_man:
                gender = "남자";
                break;
            case R.id.btn_woman:
                gender = "여자";
                break;
            default:
                break;
        }

        Task task = new Task();
        task.execute(new String[]{url});


    }

    private class Task extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String output = null;
            for (String url : urls) {
                output = getOutputFromUrl(url);
            }
            return output;
        }

        private String getOutputFromUrl(String url) {
            String output = null;
            try {
                String uurl = url + "?gender=" + gender;
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(uurl);
                Log.i("MY_TAG", "get >> " + httpGet);
                Log.i("MY_TAG", "getURI() >> " + httpGet.getURI());

                HttpResponse httpResponse = httpClient.execute(httpGet);
                Log.i("MY_TAG", "HttpResponse >> " + httpResponse);

                HttpEntity httpEntity = httpResponse.getEntity();
                output = EntityUtils.toString(httpEntity);
                Log.i("MY_TAG", "output >> " + output);

                //////////////////////////////
                Intent in = new Intent(getApplicationContext(),
                        RecommendListActivity.class);
                in.putExtra("output", output);
                startActivity(in);

                ///////////////////////////////

            } catch (Exception e) {
                Log.i("MY_TAG", e.getMessage());
                e.printStackTrace();
            }
            return output;
        }

        @Override
        protected void onPostExecute(String output) {
        }

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
}
