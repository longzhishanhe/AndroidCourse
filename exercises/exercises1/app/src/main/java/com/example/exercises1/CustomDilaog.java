package com.example.exercises1;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CustomDilaog {
    private Context context;
    private Dialog dialog;

    public CustomDilaog(Context context) {

        this.context = context;
        dialog = new Dialog(context);
    }

    public void show() {
        final View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        dialog.setContentView(view);
        dialog.setTitle("自定义对话框");
        dialog.show();

        Button b=view.findViewById(R.id.b_register);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name=view.findViewById(R.id.u);
                EditText pass=view.findViewById(R.id.p);

                String n=name.getText().toString().trim();
                String p=pass.getText().toString().trim();

                if (n.equals("abc")&&p.equals("123")){
                    Toast.makeText(context, "登录成功", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button bno=view.findViewById(R.id.no);
        bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.hide();
            }
        });
    }
}
