package com.example.exercises4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //SharedPreferences文件名
    private final static String SharedPreferencesFileName="config";
    //学号
    private final static String Key_number="student number";
    //姓名
    private final static String Key_name="name";

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=getSharedPreferences(SharedPreferencesFileName,MODE_PRIVATE);
        editor=sp.edit();
    }

    public void read(View view) {
        String number=sp.getString(Key_number,null);
        String name=sp.getString(Key_name,null);
        if(number==null||name==null){
            Toast.makeText(MainActivity.this,"数据不存在",Toast.LENGTH_LONG).show();
        }else{
            String msg;
            msg=number+"-"+name;
            Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
        }
    }

    public void write(View view) {
        editor.putString(Key_number,"2018011192");
        editor.putString(Key_name,"刘宏远");
        Toast.makeText(MainActivity.this,"已保存", Toast.LENGTH_LONG).show();
        editor.apply();
    }
}