package com.androiddevs.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class DisplayActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        findViewById(R.id.btnDisplayAll).setOnClickListener(this);
        findViewById(R.id.btnDisplayID).setOnClickListener(this);
        findViewById(R.id.btnDisplayRowCount).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnDisplayID:
                startActivity(new Intent(DisplayActivity.this, DislpayWithIdActivity.class));
                break;
            case R.id.btnDisplayAll:
                startActivity(new Intent(DisplayActivity.this, DisplayAllActivity.class));
                break;
            case R.id.btnDisplayRowCount:
                startActivity(new Intent(DisplayActivity.this, DisplayRowCountActivity.class));
                break;
            default:
                break;
        }
    }
}
