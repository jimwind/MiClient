package me.mi.miclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnBookManager;
    private Button btnMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBookManager = findViewById(R.id.book_manager);
        btnBookManager.setOnClickListener(this);
        btnMessenger = findViewById(R.id.messenger);
        btnMessenger.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.book_manager:{
                Intent intent = new Intent(this, BooManagerActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.messenger:{
                Intent intent = new Intent(this, MessengerActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
