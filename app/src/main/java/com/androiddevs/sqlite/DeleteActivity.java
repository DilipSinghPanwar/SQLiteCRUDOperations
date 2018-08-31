package com.androiddevs.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;

import com.androiddevs.sqlite.DBManager.DataBaseHandler;
import com.androiddevs.sqlite.DBManager.UserModel;

public class DeleteActivity extends Activity implements View.OnClickListener {

    private EditText id;
    private EditText name;
    private DataBaseHandler mDBHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        //Instantiate Database object
        mDBHandler = new DataBaseHandler(this, DataBaseHandler.DATABASE_NAME, null, DataBaseHandler.DATABASE_VERSION);
        id = (EditText) findViewById(R.id.etId);
        name = (EditText) findViewById(R.id.etName);

        findViewById(R.id.btnDeleteWithKey).setOnClickListener(this);
        findViewById(R.id.btnDeleteAllValues).setOnClickListener(this);
        findViewById(R.id.btnDeleteWithName).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDeleteWithKey:
                String id = DeleteActivity.this.id.getText().toString();
                if (id.equalsIgnoreCase("")) {
                    showSnackbar("ENTER ID !!!");
                } else {
                    if (mDBHandler.deleteValueswithkey(new UserModel(id, null, null))) {
                        showSnackbar("ACTION SUCCESS !!!");
                    } else {
                        showSnackbar("ACTION FAILED , NO RECORD FOUND TO DELETE!!!");
                    }
                    clearField();
                }
                break;
            case R.id.btnDeleteWithName:
                String name = DeleteActivity.this.name.getText().toString();
                if (name.equalsIgnoreCase("")) {
                    showSnackbar("ENTER NAME");
                } else {
                    if (mDBHandler.deleteValueswithName(new UserModel(name, null))) {
                        showSnackbar("ACTION SUCCESS !!!");
                    } else {
                        showSnackbar("ACTION FAILED , NO RECORD FOUND TO DELETE NAME!!!");
                    }
                    clearField();
                }
                break;
            case R.id.btnDeleteAllValues:
                if (mDBHandler.deleteAllValues()) {
                    showSnackbar("ACTION SUCCESS !!!");
                    clearField();
                } else {
                    showSnackbar("ACTION FAILED , NO RECORD FOUND TO DELETE!!!");
                    clearField();
                }
                break;
        }
    }
    /*reset value*/
    public void clearField() {
        name.setText("");
        id.setText("");
    }
    /*show message notification*/
    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.tvSnackBar), message, Snackbar.LENGTH_LONG).show();
    }
}
