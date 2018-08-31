package com.androiddevs.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.androiddevs.sqlite.DBManager.DataBaseHandler;

public class DisplayRowCountActivity extends Activity {

    private TextView displayRowCount;
    DataBaseHandler _mDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayrowcount);
        //Instantiate Database object
        _mDBHandler = new DataBaseHandler(this, DataBaseHandler.DATABASE_NAME, null, DataBaseHandler.DATABASE_VERSION);
        displayRowCount = (TextView) findViewById(R.id.tvDiplayRowCount);
        Button btnRowCount = (Button) findViewById(R.id.btnDisplayRowCount);
        btnRowCount.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long rowCount = _mDBHandler.getRowCount();
                displayRowCount.setText("\n"+"TOTAL NUMBER OF ROW IN TABLE\n"+String.valueOf(rowCount) + "");
                showSnackbar("ACTION SUCCESS !!!");
            }
        });
    }
    /*show message notification*/
    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.tvSnackBar), message, Snackbar.LENGTH_LONG).show();
    }
}
