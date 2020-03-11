package com.example.ifttw.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RoutineDB";
    private static final String TABLE_NAME_ACTIVE = "ActiveRoutine";
    private static final String TABLE_NAME_INACTIVE = "InactiveRoutine";
    private static final String KEY_ID = "id";
    private static final String KEY_FUNCTIONALITY = "functionality";
    private static final String KEY_CONDITION = "conditions";
    private static final String KEY_ACTION = "actions";
    private static final String[] COLUMNS = { KEY_ID, KEY_FUNCTIONALITY, KEY_CONDITION, KEY_ACTION };



    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public SQLiteDatabaseHandler(Context context, String name, int version, SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATION_TABLE = "CREATE TABLE ActiveRoutine ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "functionality TEXT, "
                + "conditions TEXT, "
                + "actions TEXT)";
        sqLiteDatabase.execSQL (CREATION_TABLE);

        CREATION_TABLE = "CREATE TABLE InactiveRoutine ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "functionality TEXT, "
                + "conditions TEXT, "
                + "actions TEXT)";

        sqLiteDatabase.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACTIVE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_INACTIVE);
        this.onCreate(sqLiteDatabase);
    }

    public boolean addRoutine(Routine routine, boolean active){
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(KEY_ID, routine.getId());
        contentValues.put(KEY_FUNCTIONALITY, routine.getFunctionality());
        contentValues.put(KEY_CONDITION, routine.getCondition());
        contentValues.put(KEY_ACTION, routine.getAction());
        if(active){
            result = db.insert(TABLE_NAME_ACTIVE, null, contentValues);
        }else{
            result = db. insert(TABLE_NAME_INACTIVE, null, contentValues);
        }
        if(result ==-1){
            return false;
        }else{
            return true;
        }
    }

    public List<Routine> allRoutines(boolean active){
        List<Routine> routines = new ArrayList<>();
        String selectQuery;
        if(active){
            selectQuery = "SELECT * FROM ActiveRoutine";
        }else{
            selectQuery = "SELECT * FROM InactiveRoutine";
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                Routine routine = new Routine();
                routine.setFunctionality(cursor.getString(1));
                routine.setCondition(cursor.getString(2));
                routine.setAction(cursor.getString(3));
                routines.add(routine);
            } while (cursor.moveToNext());
        }
        db.close();
        return routines;
    }

}
