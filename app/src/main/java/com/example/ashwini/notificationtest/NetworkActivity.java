package com.example.ashwini.notificationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }

    public void networkTest(View v){
        Button networkButton = (Button)findViewById(R.id.network_test);
        Intent intent;

        if(networkButton.getText().toString().contains("Start")) {
            networkButton.setText("Stop Network Test");
            intent = new Intent(this, NetworkIntentService.class );   //TODO: if using fragment, replace "this" by "getActivity()"
            this.startService(intent);
        } else {
            networkButton.setText("Start Network Test");
            intent = new Intent(this, NetworkIntentService.class );
            this.stopService(intent);
        }
    }
}
