package com.example.demo1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
//SharedPreferences in Android is like a small local storage.
// inside this you can store little pieces of information like username, last Login time etc.
// Store data in key value pairs.
    EditText edtuname,edtpassword;
    Button btnlogin;
    TextView txtshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtuname=findViewById(R.id.edtuname);
        edtpassword=findViewById(R.id.edtpassword);
        btnlogin=findViewById(R.id.btnlogin);
        txtshow=findViewById(R.id.txtshow);


        SharedPreferences pref = getSharedPreferences("demo1.txt",MODE_PRIVATE);

        //show last Login date and time
        String lastlogin = pref.getString("lastlogintime","First Time Login");
        txtshow.setText("Last Login: "+ lastlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = edtuname.getText().toString();
                String password = edtpassword.getText().toString();

                //current date and time
                String currtime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(new Date());

                if(uname.equals("admin") && password.equals("1234")) {

                    //save login detail and time in sharedpreferences
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("uname", uname);
                    editor.putString("lastlogintime", currtime);
                    editor.apply();

                    //redirect to MaintActivity2
                    Intent intent1 = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent1);
                    finish();

                }else{
                    Toast.makeText(MainActivity.this, "Invalid Detail", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}