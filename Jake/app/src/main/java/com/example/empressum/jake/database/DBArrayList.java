package com.example.empressum.jake.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by empressum on 9/8/16.
 */
public class DBArrayList extends SQLiteOpenHelper {
    //Database Version
    private static final int DATABASE_VERSION=1;
    //Database Name
    private static final String DATABASE_NAME="arraylistdatabase";//database data
    //Contacts table data
    private static final String TABLE_NAME="login";//TABLE PRODUCTS
    //Shops Tables Columns data
    private static final String COLUMN_ID="_id";//column id
    private static final String USR_NAME="data";//product data
    private static final String USR_PASS="password";//product data
    private static final String USR_ADDR="address";
    private static final String USR_PH="phone";
    String name,password,address,phone;
    ContentValues values =null;
    //UserSpace us;
    Saver us;

    public DBArrayList(Context context, String data, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query="CREATE TABLE "+TABLE_NAME+"("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USR_NAME + " TEXT ,"+ USR_PASS + " TEXT ,"+ USR_ADDR +" TEXT ," + USR_PH +" TEXT "+");";

        db.execSQL(query);

//        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    //Add a new row to the database
    public void addUsers(ArrayList<Saver> ar){

        values=new ContentValues();
        values.put(USR_NAME,ar.get(0).getName());
        values.put(USR_PASS,ar.get(0).getPass());
        values.put(USR_ADDR,ar.get(0).getAddress());
        values.put(USR_PH,ar.get(0).getPhone());
        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
        }

    //Delete a product from the database
    public void deleteUser(String userName){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+" WHERE "+USR_NAME+"=\""+userName+"\";");
    }

    //public UserSpace databaseToString(){
    public Saver databaseToString(){
        String TAG="DBArraylist";
        String dbString="",dbString1="";
        SQLiteDatabase db= getWritableDatabase();
        String query= "SELECT * FROM "+TABLE_NAME+" WHERE 1";
        //us = new UserSpace();
        us = new Saver();

        //cursor points to a location in you results
        Cursor c=db.rawQuery(query,null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position afte the last row means the end of the results
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("data"))!=null){
                name +=c.getString(c.getColumnIndex("data"));
                name +="\n";
            }
            us.setName(name);
            Log.d(TAG,"name="+name);
            c.moveToNext();
        }
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("password"))!=null){
                password +=c.getString(c.getColumnIndex("password"));
                password +="\n";
            }
            us.setPass(password);
            c.moveToNext();
        }
        dbString=dbString+" "+dbString1;
        dbString1="";
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("address"))!=null){
                address +=c.getString(c.getColumnIndex("address"));
                address +="\n";
            }
            us.setAddress(address);
            c.moveToNext();
        }
        dbString=dbString+" "+dbString1;
        dbString1="";
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("phone"))!=null){
                phone +=c.getString(c.getColumnIndex("phone"));
                phone +="\n";
            }
            us.setPhone(phone);
            c.moveToNext();
        }
        dbString=dbString+" "+dbString1;
        dbString1="";

        c.close();
        db.close();
        return us;
    }
}
