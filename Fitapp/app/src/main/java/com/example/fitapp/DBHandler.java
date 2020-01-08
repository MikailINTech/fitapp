package com.example.fitapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class DBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 14;
    private static final String DATABASE_NAME = "quoteDB.db";
    public static final String TABLE_NAME = " TABLEQUOTE ";
    public static final String COLUMN_ID = " QuoteID ";
    public static final String COLUMN_NAME = " QuoteText ";

    //-------------------------------------------------------------------------------------------------
    //initialize the database
    //-------------------------------------------------------------------------------------------------
    //public DBHandler(Context context) {
    //    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    //}
    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    //-------------------------------------------------------------------------------------------------



    //-------------------------------------------------------------------------------------------------
    //Methods
    //-------------------------------------------------------------------------------------------------

    //on Database creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =  " CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID +
                " INTEGER PRIMARY KEY , " + COLUMN_NAME + " TEXT ) ";
        db.execSQL(CREATE_TABLE);
    }


    //onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j) {
        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + "TABLEQUOTE");
        onCreate(db);
    }

    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }


    //Add an element to the database
    public void addHandler(Quote quote) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, quote.getID());
        values.put(COLUMN_NAME, quote.getQuoteText());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //Load Database
    public void loadAll() {
        int i = 1;
        //Liste des citations
        String quotesList[] ={
                "You can do it!",
                "Never give up",
                "Give your best",
                "Dépassez vos limites",
                "You got the power",
                "Unleash the power in you",
                "Go for it!"};
        while (i != quotesList.length-1) {
            Quote quote = new Quote(i, quotesList[i-1]);
            this.addHandler(quote);
            i=i+1;
        }
    }

    //request to database
    public Quote findHandler(String quotetext) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + quotetext + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Quote quote = new Quote();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            quote.setID(Integer.parseInt(cursor.getString(0)));
            quote.setQuoteText(cursor.getString(1));
            cursor.close();
        } else {
            //quote = null;
        }
        db.close();
        return quote;
    }

    public Quote findHandlerById(int id) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + "'" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Quote quote = new Quote();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cursor.moveToFirst();
            quote.setID(Integer.parseInt(cursor.getString(0)));
            quote.setQuoteText(cursor.getString(1));
            cursor.close();
        } else {
            //quote = null;
        }
        db.close();
        return quote;
    }


    //Delete element from database
    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + "WHERE" + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Quote quote = new Quote();
        if (cursor.moveToFirst()) {
            quote.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                            String.valueOf(quote.getID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    //Update database
    public boolean updateHandler(int ID, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }



    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}

