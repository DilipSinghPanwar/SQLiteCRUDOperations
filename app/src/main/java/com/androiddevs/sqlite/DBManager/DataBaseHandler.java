package com.androiddevs.sqlite.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    /**
     * Database Name
     */
    public static final String DATABASE_NAME = "ANDROIDDEVS";
    /* Database Version*/
    public static final int DATABASE_VERSION = 1;
    /* Database Table*/
    public static final String TABLE_USER = "User";
    /*Table Columns names*/
    public static final String KEY_USER_ID = "_id";
    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_EMAIL = "_email";
    public static final String[] ALL_TABLES = {TABLE_USER};
    private String create_USER_table;
    /*Declare SQLiteDatabase object*/
    private SQLiteDatabase _mdb;

    /* Constructor*/
    public DataBaseHandler(Context context, String name, CursorFactory factory,
                           int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        //  TODO Auto-generated constructor stub
    }

    /*Creating Tables*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        //  TODO Auto-generated method stub
        /** Create table syntax */
        create_USER_table = "CREATE TABLE " + TABLE_USER + " (" + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USER_NAME + " TEXT NOT NULL, " + KEY_USER_EMAIL + " TEXT NOT NULL)";
        db.execSQL(create_USER_table);
        db.execSQL("INSERT INTO " + TABLE_USER + "(" + KEY_USER_NAME + "," + KEY_USER_EMAIL + ") VALUES ('ANDROIDDEVS','INFO@ANDROIDDEVS.COM')");
    }

    /*Upgrading Database*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //   TODO Auto-generated method stub
        for (String table : ALL_TABLES) {
            /* Drop older table if existed*/
            db.execSQL("DROP TABLE IF EXISTS " + table);
        }
         /*Create tables again*/
        onCreate(db);
    }

    /* Open database connection for Read / Write*/
    public void open() throws SQLiteException {
        _mdb = this.getReadableDatabase();
        _mdb = this.getWritableDatabase();
    }

    /*Closing database connection*/
    public void close() {
        _mdb.close();
    }

    /* ************************Insert*/
     /*Adding new user*/
    public boolean insertValues(UserModel user) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());  //Name
        values.put(KEY_USER_EMAIL, user.getEmail());  //Email
         /*Insert user values in database*/
        long row = _mdb.insert(TABLE_USER, null, values);
        close();
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*  *************************** DELETE*/
     /*Deleting user using ID*/
    public boolean deleteValueswithkey(UserModel user) {
        open();
        int delRows = _mdb.delete(TABLE_USER, KEY_USER_ID + " = ?", new String[]{user.getId()});
        if (delRows > 0) {
            close();
            return true;
        } else {
            close();
            return false;
        }
    }

    /* Deleting user using Name*/
    public boolean deleteValueswithName(UserModel user) {
        open();
        int delRows = _mdb.delete(TABLE_USER, KEY_USER_NAME + " = ?", new String[]{user.getName()});
        if (delRows > 0) {
            close();
            return true;
        } else {
            close();
            return false;
        }

    }

    /*Delete All*/
    public boolean deleteAllValues() {
        open();
        int delRows = _mdb.delete(TABLE_USER, null, null);
        if (delRows > 0) {
            close();
            return true;
        } else {
            close();
            return false;
        }

    }

    /*********************
     * Updates
     */
     /*Updating user*/
    public boolean updateAllValues(UserModel user) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_EMAIL, user.getEmail());
         /*updating row*/
        int updatesRows = _mdb.update(TABLE_USER, values, KEY_USER_ID + " = ?", new String[]{Long.toString(Long.parseLong(user.getId()))});
        if (updatesRows > 0) {
            close();
            return true;
        } else {
            close();
            return false;
        }
    }

    /* Update Name*/
    public boolean updateName(String old_name, String new_name) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, new_name);
        /* updating row*/
        int updatesRows = _mdb.update(TABLE_USER, values, KEY_USER_NAME + " = ?", new String[]{old_name});
        if (updatesRows > 0) {
            close();
            return true;
        } else {
            close();
            return false;
        }
    }

    /* Update Email*/
    public boolean updateEmail(String old_email, String new_email) {
        open();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_EMAIL, new_email);
         /*updating row*/
        int updatesRows = _mdb.update(TABLE_USER, values, KEY_USER_EMAIL + " = ?", new String[]{old_email});
        if (updatesRows > 0) {
            close();
            return true;
        } else {
            close();
            return false;
        }
    }

    /****************
     * Display Data
     */

     /*Get Value USing ID*/
    public UserModel getAllValuesWithId(long _rowid) {
        UserModel user = null;
        open();
        String[] columns = new String[]{KEY_USER_ID, KEY_USER_NAME, KEY_USER_EMAIL};
        Cursor cursor = _mdb.query(TABLE_USER, columns, KEY_USER_ID + "=?", new String[]{String.valueOf(_rowid)}, null, null, null);
        /*If Cursor is valid*/
        if (cursor != null) {
        /*Move cursor to first row*/
            if (cursor.moveToFirst()) {
                do {
                    user = new UserModel(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                } while (cursor.moveToNext());// Move to next row
            }
            cursor.close();
        }
        close();
        return user;
    }

    /*Get All Values*/
    public List<UserModel> getAllValues() {
        List<UserModel> userList = new ArrayList<UserModel>();
        open();
        String[] columns = new String[]{KEY_USER_ID, KEY_USER_NAME, KEY_USER_EMAIL};
        Cursor cursor = _mdb.query(TABLE_USER, columns, null, null, null, null, null);
        /*If Cursor is valid*/
        if (cursor != null) {
             /*looping through all rows and adding to list*/
            if (cursor.moveToFirst()) { //Move cursor to first row
                do {
                    UserModel user = new UserModel();
                    user.setId(cursor.getString(0));
                    user.setName(cursor.getString(1));
                    user.setEmail(cursor.getString(2));
                    userList.add(user);
                } while (cursor.moveToNext()); //Move to next row
            }
            cursor.close();// Closing database connection
        }
        close();
        return userList; // return userList
    }

    /* Table Row Count*/
    public long getRowCount() {
        open();
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        Cursor cursor = _mdb.rawQuery(countQuery, null);
        long rowCount = cursor.getCount();
        close();// Closing database connection
        return rowCount;// return count
    }

}
