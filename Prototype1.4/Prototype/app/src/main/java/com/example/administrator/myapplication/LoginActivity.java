package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_login;
    private Button btn_signUp;
    private EditText edit_ID;
    private EditText edit_PW;
    //String url = "http://220.149.119.118:8082/proj_server/Recommendation.do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edit_ID = (EditText) findViewById(R.id.edit_ID);
        edit_PW = (EditText) findViewById(R.id.edit_passWord);

        btn_login = (Button)findViewById(R.id.btn_Login);
        btn_signUp = (Button)findViewById(R.id.btn_SignUp);

        btn_login.setOnClickListener(this);
        btn_signUp.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
        if(v == btn_login){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            // 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!

//            Task task = new Task();
//            task.execute(new String[]{url});
        }
        if(v == btn_signUp){
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            // 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!

        }

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
                String uurl = url + "?ID=" + edit_ID.getText() + "?PW=" + edit_PW.getText();
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
}
