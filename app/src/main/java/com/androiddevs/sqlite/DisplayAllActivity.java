package com.androiddevs.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.androiddevs.sqlite.DBManager.DataBaseHandler;
import com.androiddevs.sqlite.DBManager.UserModel;

import java.util.List;

public class DisplayAllActivity extends Activity implements OnClickListener {


    private TextView dislpay;
    private DataBaseHandler _mDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydata);
        //Instantiate Database object
        _mDBHandler = new DataBaseHandler(this, DataBaseHandler.DATABASE_NAME, null, DataBaseHandler.DATABASE_VERSION);
        dislpay = (TextView) findViewById(R.id.tvDisplay);
        findViewById(R.id.btnShowAll).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnShowAll:
                try {
                    dislpay.setText("");
                    List< UserModel> user = _mDBHandler.getAllValues();
                    if(user.size() > 0){
                        showSnackbar("ACTION SUCCESS !!!");
                    for (UserModel u : user) {
                        String val = "\nId: "+u.getId()+"\nName: " + u.getName() + "\nEmail: " + u.getEmail();
                        dislpay.append(val + "\n");
                    }}
                else{showSnackbar("ACTION FAILED , NO RECORD FOUND!!!");;}
                } catch (Exception e) {
                    e.printStackTrace();
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
