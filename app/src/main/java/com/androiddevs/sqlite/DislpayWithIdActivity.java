package com.androiddevs.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.androiddevs.sqlite.DBManager.DataBaseHandler;
import com.androiddevs.sqlite.DBManager.UserModel;

import java.util.List;

public class DislpayWithIdActivity extends Activity implements View.OnClickListener {

    private EditText id;
    private DataBaseHandler _mDBHandler;
    private TextView displayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayid);
        //Instantiate Database object
        _mDBHandler = new DataBaseHandler(this, DataBaseHandler.DATABASE_NAME, null, DataBaseHandler.DATABASE_VERSION);
        id = (EditText) findViewById(R.id.etRowID);
        displayData = (TextView) findViewById(R.id.tvDisplayWithID);
        findViewById(R.id.btnDisplayWithId).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDisplayWithId:
                String dID = id.getText().toString();
                if (dID.equalsIgnoreCase("")) {
                    showSnackbar("ENTER ID !!!");
                } else {
                    long row_id = Long.parseLong(dID.toString());
                    // Reading all info
                    UserModel user = _mDBHandler.getAllValuesWithId(row_id);
                    if (user != null) {
                        showSnackbar("ACTION SUCCESS !!!");
                        String val = "\nId: " + user.getId() + "\nName: " + user.getName() + "\nEmail: " + user.getEmail();
                        displayData.setText(val);
                    }
                        else
                        showSnackbar("ACTION FAILED , NO RECORD FOUND!!!");
                    }
                    break;
                    default:
                        break;
                }
        }
    /*show message notification*/
    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.tvSnackBar), message, Snackbar.LENGTH_LONG).show();
    }
}
