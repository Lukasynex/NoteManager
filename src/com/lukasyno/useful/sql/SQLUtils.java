package com.lukasyno.useful.sql;

import java.io.IOException;
import java.util.GregorianCalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLUtils {
	protected static final String TAG = "DataAdapter";
	private static int ID = 224;
	private final Context mContext;
	private SQLiteDatabase mDb;
	private DataBaseHelper mDbHelper;

	public SQLUtils(Context context) {
		this.mContext = context;
		mDbHelper = new DataBaseHelper(mContext);
	}

	public SQLUtils createDatabase() throws SQLException {
		try {
			mDbHelper.createDataBase();
		} catch (IOException mIOException) {
			Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
			throw new Error("UnableToCreateDatabase");
		}
		return this;
	}

	public SQLUtils open() throws SQLException {
		try {
			mDbHelper.openDataBase();
			mDbHelper.close();
			mDb = mDbHelper.getReadableDatabase();
		} catch (SQLException mSQLException) {
			Log.e(TAG, "open >>" + mSQLException.toString());
			throw mSQLException;
		}
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public Cursor getAnswerByQuest(String begin_letters, boolean regex,
			String indexik) {
		try {
			// String sql
			// ="SELECT Email FROm Employees where Name LIKE '"+begin_letters+"%' ";
			String sql = "";
			if (regex)
				sql = "SELECT ID,PYTANIE, ODPOWIEDZ FROM SIECI where "
						+ begin_letters;
			else
				sql = "SELECT ID,PYTANIE, ODPOWIEDZ FROM SIECI where PYTANIE LIKE '%"
						+ begin_letters + "%'" + " and ID > " + indexik;
			Cursor mCur = mDb.rawQuery(sql, null);
			if (mCur != null) {
				mCur.moveToNext();
			}
			return mCur;
		} catch (SQLException mSQLException) {
			Log.e(TAG, "getTestData >>" + mSQLException.toString());
			throw mSQLException;
		}

	}

	public Cursor getTestData(int id) {
		try {
			// String sql
			// ="SELECT EmployeeId, Name, Email FROm Employees where EmployeeId="+id;
			String sql = "SELECT ID, PYTANIE, ODPOWIEDZ FROm SIECI where ID="
					+ id;

			Cursor mCur = mDb.rawQuery(sql, null);
			if (mCur != null) {
				mCur.moveToNext();
			}
			return mCur;
		} catch (SQLException mSQLException) {
			Log.e(TAG, "getTestData >>" + mSQLException.toString());
			throw mSQLException;
		}
	}

	//TODO:
	
	
	
	public boolean saveNote(String content, String tag, GregorianCalendar date) {
		try {
			ContentValues cv = new ContentValues();
			//NOTE schema:
			//id, content, tag, date
			cv.put("ID", ++ID);
			cv.put("PYTANIE", content);
			cv.put("ODPOWIEDZ", tag);
			cv.put("date", date.toString());
			

			mDb.insert("SIECI", null, cv);

			Log.d("SaveSIECI", "informationsaved");
			return true;

		} catch (Exception ex) {
			Log.d("SaveSIECI", ex.toString());
			return false;
		}
	}

	public boolean SaveEmployee(String name, String email) {
		try {
			ContentValues cv = new ContentValues();
			cv.put("Name", name);
			cv.put("Email", email);

			mDb.insert("Employees", null, cv);

			Log.d("SaveEmployee", "informationsaved");
			return true;

		} catch (Exception ex) {
			Log.d("SaveEmployee", ex.toString());
			return false;
		}
	}
}
