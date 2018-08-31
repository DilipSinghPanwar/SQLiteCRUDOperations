package com.androiddevs.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.btnInsert).setOnClickListener(this);
        findViewById(R.id.btnUpdate).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
        findViewById(R.id.btnDisplay).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {

            case R.id.btnInsert:
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
                break;
            case R.id.btnUpdate:
                startActivity(new Intent(MainActivity.this, UpdateActivity.class));
                break;
            case R.id.btnDelete:
                startActivity(new Intent(MainActivity.this, DeleteActivity.class));
                break;
            case R.id.btnDisplay:
                startActivity(new Intent(MainActivity.this, DisplayActivity.class));
                break;
            default:
                break;
        }
    }

}
