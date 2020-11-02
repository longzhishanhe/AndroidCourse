package com.example.exercises1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button hint=findViewById(R.id.hint);
        Button register=findViewById(R.id.register);

        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog aldg;
                AlertDialog.Builder adBd=new AlertDialog.Builder(MainActivity.this);
                adBd.setTitle("提示对话框");
                adBd.setMessage("请登录");
                adBd.setPositiveButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                aldg=adBd.create();
                aldg.show();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDilaog dilaog = new CustomDilaog(MainActivity.this);
                dilaog.show();
            }
        });
    }
}