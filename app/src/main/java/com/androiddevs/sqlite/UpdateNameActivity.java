package com.androiddevs.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androiddevs.sqlite.DBManager.DataBaseHandler;

public class UpdateNameActivity extends Activity {

    private EditText oldName;
    private EditText newName;
    private DataBaseHandler _mDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatename);
        //Instantiate Database object
        _mDBHandler = new DataBaseHandler(this, DataBaseHandler.DATABASE_NAME, null, DataBaseHandler.DATABASE_VERSION);
        oldName = (EditText) findViewById(R.id.etOldName);
        newName = (EditText) findViewById(R.id.etNewName);
        Button Update = (Button) findViewById(R.id.btnUpdate);

        Update.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String _OldName = oldName.getText().toString();  // Old name
                String _NewName = newName.getText().toString();  // new name
                if (_OldName.equalsIgnoreCase("") || _NewName.equalsIgnoreCase("")) {
                    showSnackbar("ENTER VALUES !!!");
                } else {
                    if (_mDBHandler.updateName(_OldName, _NewName)) {
                        showSnackbar("ACTION SUCCESS !!!");
                        clearField();
                    } else {
                        showSnackbar("ACTION FAILED ,RECORD NOT EXISTS !!!");
                    }
                }
            }
        });
    }

    /*reset value*/
    public void clearField() {
        oldName.setText("");
        newName.setText("");
    }
    /*show message notification*/
    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.tvSnackBar), message, Snackbar.LENGTH_LONG).show();
    }
}
