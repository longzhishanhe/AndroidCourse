package com.example.exercises2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    int s3=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();
        int s1=intent.getIntExtra("s1", 0);
        int s2=intent.getIntExtra("s2", 0);
        s3=s1+s2;
        Toast.makeText(this,s1+"+"+s2+"="+s3, Toast.LENGTH_LONG).show();

        Button b2=(Button)findViewById(R.id.act2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                intent.putExtra("s3",s3);
                setResult(0,intent);
                finish();
            }
        });
    }
}