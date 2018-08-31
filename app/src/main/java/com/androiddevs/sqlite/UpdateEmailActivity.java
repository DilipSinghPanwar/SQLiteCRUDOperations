package com.androiddevs.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androiddevs.sqlite.DBManager.DataBaseHandler;

public class UpdateEmailActivity extends Activity {

    private DataBaseHandler _mDBHandler;
    private EditText oldEmail;
    private EditText newEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateemail);
        //Instantiate Database object
        _mDBHandler = new DataBaseHandler(this, DataBaseHandler.DATABASE_NAME, null, DataBaseHandler.DATABASE_VERSION);
        oldEmail = (EditText) findViewById(R.id.etOldEmail);
        newEmail = (EditText) findViewById(R.id.etNewEmail);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String _OldEmail = oldEmail.getText().toString();
                String _NewEmail = newEmail.getText().toString();

                if (_OldEmail.equalsIgnoreCase("") || _NewEmail.equalsIgnoreCase("")) {
                    showSnackbar("ENTER VALUES !!!");
                } else {
                    if (_mDBHandler.updateEmail(_OldEmail, _NewEmail)) {
                        showSnackbar("ACTION SUCCESS !!!");
                        clearField();
                    }else{
                        showSnackbar("ACTION FAILED ,RECORD NOT EXISTS !!!");
                    }
                }
            }
        });
    }
    /*reset value*/
    public void clearField() {
        oldEmail.setText("");
        newEmail.setText("");
    }
    /*show message notification*/
    public void showSnackbar(String message) {
        Snackbar.make(findViewById(R.id.tvSnackBar), message, Snackbar.LENGTH_LONG).show();
    }

}
