package com.example.roomandretrofit;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.roomandretrofit.Room.room;
import com.example.roomandretrofit.retrofit.retrofit;

public class MainActivity extends AppCompatActivity {
  Button btnretrofit,btnroom,btnDTLMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnroom = (Button)findViewById(R.id.btnroom);
        btnretrofit = (Button)findViewById(R.id.btnretrofit);
        btnDTLMS = (Button)findViewById(R.id.btnDTLMS);



        btnretrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, retrofit.class);
                startActivity(i);
            }
        });

        btnroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, room.class);
                startActivity(i);
            }
        });

        btnDTLMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("https://bescomdtlms.com/");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

    }
}
