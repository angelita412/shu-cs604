package shu.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter { 
	public static final String KEY_ROWID = "_id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_FNAME = "f_name";
    public static final String KEY_LNAME = "l_name";
    public static final String KEY_SQUESTION = "s_question";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DATE = "date";
    public static final String KEY_JENTRY = "j_entry";
    
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "journal";
    private static final String USERS_TABLE = "users";
    private static final String JOURNAL_TABLE = "journal_entries";
    private static final int DATABASE_VERSION = 2;

    private static final String USERS_CREATE =
        "create table users (_id integer primary key autoincrement, " +
        "username text not null, " +
        "f_name text not null, " +
        "l_name text not null, " +
        "s_question text not null, " +
        "password text not null); " ;
    private static final String JOURNAL_CREATE =
    	"create table journal_entries (_id integer primary key autoincrement, " +
    	"username text not null, date text not null, j_entry text not null);";
        
    private final Context context;
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	//db.execSQL("DROP TABLE journal_entries");
        	//db.execSQL("DROP TABLE users");
            db.execSQL(USERS_CREATE);
            db.execSQL(JOURNAL_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }    
  //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a user into the users table---
    public long insertUser(String username, String f_name,String l_name,String s_question,String password) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_FNAME, f_name);
        initialValues.put(KEY_LNAME, l_name);
        initialValues.put(KEY_SQUESTION, s_question);
        initialValues.put(KEY_PASSWORD, password);
        return db.insert(USERS_TABLE, null, initialValues);
    }
    
    //---updates user password---
	public boolean updatePassword(long rowId, String password) 
	{
	    ContentValues args = new ContentValues();
	    args.put(KEY_PASSWORD, password);
	    return db.update(USERS_TABLE, args, 
	                     KEY_ROWID + "=" + rowId, null) > 0;
	}

	//--check if username and password match--
    public boolean checkPassword (String username ,String password) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, USERS_TABLE, new String[] {
                		KEY_PASSWORD},
                		KEY_USERNAME + "=" + username,
                		null, 
                		null, 
                		null, 
                		null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return KEY_PASSWORD.matches(password);
    }
    
    //--check if security answer matches--
    public boolean checkSecAnswer (String s_answer, String username) throws SQLException 
    {
        Cursor mCursor =
                db.query(true,USERS_TABLE, new String[]{KEY_SQUESTION},
                		KEY_USERNAME + "=" + username,
                		null, 
                		null, 
                		null, 
                		null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return KEY_SQUESTION.matches(s_answer);
    }
    
    //--insert a journal entry--
    public long insertPage(String username, String date, String j_entry) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_JENTRY, j_entry);
        return db.insert(JOURNAL_TABLE, null, initialValues);
    }

    //---deletes a particular page---
    public boolean deletePage(long rowId) 
    {
        return db.delete(JOURNAL_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
    

    //---retrieves all the titles---
    public Cursor getAllPages() 
    {
    	 Cursor mCursor = db.query(true, JOURNAL_TABLE, new String[] {
        		KEY_ROWID,
        		KEY_USERNAME,
        		KEY_DATE,
        		KEY_JENTRY,},KEY_USERNAME + " LIKE'username%'",null,null, 
                null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
 //---retrieves a particular journal entry---
    public Cursor getJournalPage(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, JOURNAL_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_DATE},
                		KEY_ROWID + "=" + rowId,
                		null, 
                		null, 
                		null, 
                		null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
  //---retrieves a particular user entry---
    public Cursor getUserById(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, USERS_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_USERNAME,
                		KEY_FNAME,
                		KEY_LNAME,
                		KEY_SQUESTION,
                		KEY_PASSWORD},
                		KEY_ROWID + "=" + rowId,
                		null, 
                		null, 
                		null, 
                		null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
  //--retrieves a particular journal page---
    public Cursor getPageById(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, JOURNAL_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_DATE,
                		KEY_JENTRY},
                		KEY_ROWID + "=" + rowId,
                		null, 
                		null, 
                		null, 
                		null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

  //---updates an existing journal page---
    public boolean updatePage(long rowId, String j_entry) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_JENTRY, j_entry);
        return db.update(JOURNAL_TABLE, args, 
                         KEY_ROWID + "=" + rowId, null) > 0;
    }
  //--- get first record ---
    public Cursor getFirstJournalEntry()
    {
    	Cursor mCursor =
            db.query(true, JOURNAL_TABLE, new String[] {
            		KEY_ROWID,
            		KEY_USERNAME,
            		KEY_DATE,
            		KEY_JENTRY},
            		null,
            		null, 
            		null, 
            		null, 
            		null, "1");
    if (mCursor != null) {
        mCursor.moveToFirst();
    }
    return mCursor;
    }
  //--- get first user ---
    public Cursor getFirstUser()
    {
    	Cursor mCursor =
            db.query(true, USERS_TABLE, new String[] {
            		KEY_ROWID,
            		KEY_USERNAME,
            		KEY_FNAME,
            		KEY_LNAME,
            		KEY_SQUESTION,
            		KEY_PASSWORD},
            		null,
            		null, 
            		null, 
            		null, 
            		null, "1");
    if (mCursor != null) {
        mCursor.moveToFirst();
    }
    return mCursor;
    }
}