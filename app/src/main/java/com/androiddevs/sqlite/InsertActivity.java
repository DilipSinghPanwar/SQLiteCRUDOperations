package com.androiddevs.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androiddevs.sqlite.DBManager.DataBaseHandler;
import com.androiddevs.sqlite.DBManager.UserModel;

public class InsertActivity extends Activity {

    protected boolean insertFlag;
    private EditText dName;
    private EditText dEmail;
    private DataBaseHandler _mDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        //Instantiate Database object
        _mDBHandler = new DataBaseHandler(this, DataBaseHandler.DATABASE_NAME, null, DataBaseHandler.DATABASE_VERSION);
        dName = (EditText) findViewById(R.id.etName);
        dEmail = (EditText) findViewById(R.id.etEmail);
        Button insertValues = (Button) findViewById(R.id.btnInsertValues);
        insertValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String name = dName.getText().toString();
                String email = dEmail.getText().toString();
                if (name.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
                    showSnackbar("ENTER VALUES !!!");
                } else {
                    insertFlag = _mDBHandler.insertValues(new UserModel(name,email));
                    if (insertFlag) {
                        showSnackbar("ACTION SUCCESS !!!");
                        clearField();
                    } else {
                        showSnackbar("ACTION FAILED !!!");
                    }
                }
            }
        });
    }
    /*reset value*/
    public void clearField() {
        dName.setText("");
        dEmail.setText("");
    }
    /*show message notification*/
    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.tvSnackBar), message, Snackbar.LENGTH_LONG).show();
    }
}
