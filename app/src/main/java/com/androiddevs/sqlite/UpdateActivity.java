package com.androiddevs.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androiddevs.sqlite.DBManager.DataBaseHandler;
import com.androiddevs.sqlite.DBManager.UserModel;

public class UpdateActivity extends Activity implements OnClickListener {


    private EditText _id;
    private EditText _name;
    private EditText _email;
    private long rowId;
    private DataBaseHandler _mDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //Instantiate Database object
        _mDBHandler = new DataBaseHandler(this, DataBaseHandler.DATABASE_NAME, null, DataBaseHandler.DATABASE_VERSION);
        _id = (EditText) findViewById(R.id.etId);
        _name = (EditText) findViewById(R.id.etName);
        _email = (EditText) findViewById(R.id.etEmail);
        findViewById(R.id.btnUpdateAll).setOnClickListener(this);
        findViewById(R.id.btnUpdateEmail).setOnClickListener(this);
        findViewById(R.id.btnUpdateName).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnUpdateAll:
                String id = _id.getText().toString();
                String name = _name.getText().toString();
                String email = _email.getText().toString();
                if (id.equalsIgnoreCase("") || name.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
                    showSnackbar("ENTER VALUES !!!");
                } else {
                    if(_mDBHandler.updateAllValues(new UserModel(id,name,email)))
                        showSnackbar("ACTION SUCCESS !!!");
                    else
                        showSnackbar("ACTION FAILED , NO RECORD FOUND TO UPDATE!!!");
                    clearField();
                }
                break;
            case R.id.btnUpdateEmail:
                startActivity(new Intent(UpdateActivity.this, UpdateEmailActivity.class));
                break;
            case R.id.btnUpdateName:
                startActivity(new Intent(UpdateActivity.this, UpdateNameActivity.class));
                break;
            default:
                break;
        }
    }
    /*reset value*/
    public void clearField() {
        _id.setText("");
        _name.setText("");
        _email.setText("");
    }
    /*show message notification*/
    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.tvSnackBar), message, Snackbar.LENGTH_LONG).show();
    }
}
