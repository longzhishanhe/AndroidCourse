package com.example.huahanshu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView txtFunction=findViewById(R.id.txtFunction);
        final CustomView customView=findViewById(R.id.plotview);

        Button buttonZoomIn=findViewById(R.id.big);
        buttonZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.big();
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
        Button buttonZoomOut=findViewById(R.id.small);
        buttonZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.small();
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
        Button buttonUp=findViewById(R.id.up);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.up();
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
        Button buttonRight=findViewById(R.id.right);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.right();
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
        Button buttonDown=findViewById(R.id.down);
        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.down();
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
        Button buttonLeft=findViewById(R.id.left);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.left();
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
        Button buttonPlot=findViewById(R.id.buttonPlot);
        buttonPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.original();
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
        Button clean=findViewById(R.id.clean);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.original();
                if(customView!=null){
                    txtFunction.setText("");
                    String strFunction=txtFunction.getText().toString();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
    }
}