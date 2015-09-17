package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;


import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private RadioGroup radioGroup;
    private RadioButton radio_man, radio_woman;
    private NumberPicker np_age;
    private Spinner spin_genre;
    private EditText edit_name, edit_id, edit_pw, edit_pw_confirm;
    private Button btn_signup;
    ArrayList<String> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ///////////////////////////라디오 그룹////////////////////////
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.radio_man) {
                    Toast.makeText(getApplicationContext(), "choice: 남자",
                            Toast.LENGTH_SHORT).show();
                } else if(checkedId == R.id.radio_women) {
                    Toast.makeText(getApplicationContext(), "choice: 여자",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        radio_man = (RadioButton) findViewById(R.id.radio_man);
        radio_woman = (RadioButton) findViewById(R.id.radio_women);



        ////////////////////////버튼////////////////////////
        btn_signup = (Button)findViewById(R.id.btn_register);
        btn_signup.setOnClickListener(this);
        /////////////////////에디트 텍스트/////////////////
        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_id = (EditText)findViewById(R.id.edit_sign_ID);
        edit_pw = (EditText)findViewById(R.id.edit_sign_password);
        edit_pw_confirm = (EditText)findViewById(R.id.edit_sign_password_confirm);

        /////////////////// 넘버 피커//////////////////////
        np_age = (NumberPicker)findViewById(R.id.numpk_age);
        np_age.setMinValue(0);
        np_age.setMaxValue(100);
        np_age.setWrapSelectorWheel(true);
        np_age.setOnValueChangedListener(this);


        /////////////////////스피너//////////////////
        arraylist = new ArrayList<String>();
        arraylist.add("락");
        arraylist.add("댄스");
        arraylist.add("힙합");
        arraylist.add("팝");
        arraylist.add("발라드");
        arraylist.add("재즈");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraylist);
        //스피너 속성
        spin_genre = (Spinner) this.findViewById(R.id.spin_genre);
        spin_genre.setPrompt("골라봐"); // 스피너 제목
        spin_genre.setAdapter(adapter);
        spin_genre.setOnItemSelectedListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.v("NumberPicker", picker.getValue() + "");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

    }
}
