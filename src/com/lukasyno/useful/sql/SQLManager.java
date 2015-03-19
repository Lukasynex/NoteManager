package com.lukasyno.useful.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLManager extends SQLiteOpenHelper {
	private SQLManager instance = null;
	public final static int DATABASE_VERSION = 1;
    public final String DATABASE_NAME; //should ends with ".db" suffix
	public SQLCommander command;
	
    private SQLManager(Context context, String DBname) {
		super(context, DBname, null, DATABASE_VERSION);
		DATABASE_NAME = DBname;
		command =  new SQLCommander(DATABASE_NAME);
    }
    SQLManager getInstance(Context context, String DBname){
    	if(instance==null)
    		instance = new SQLManager(context, DBname);
    	return instance;
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(command.CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(command.DELETE);
        onCreate(db);
	}

}
