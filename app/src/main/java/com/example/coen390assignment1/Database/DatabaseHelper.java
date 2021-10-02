// COEN 390 - Assignment 1
// Nicholas Harris - 40111093
// harris.nicholas1998@gmail.com

package com.example.coen390assignment1.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
        this.context = context;
    }

    // Called when DB is first created in the application
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Build table using an SQL Query
        String CREATE_TABLE_COUNTER = String.format("CREATE TABLE %s (%s VARCHAR(20), %s INT);", Config.COUNTER_TABLE_NAME, Config.COLUMN_COUNTER_NAME, Config.COLUMN_COUNTER_VALUE) ;
        db.execSQL(CREATE_TABLE_COUNTER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add a counter name and value to the DB
    public long insertCounter(String name, int value) {
        long newId = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();
        cV.put(Config.COLUMN_COUNTER_NAME, name);
        cV.put(Config.COLUMN_COUNTER_VALUE, value);

        try {
            newId = db.insertOrThrow(Config.COUNTER_TABLE_NAME, null, cV);
        }
        catch (SQLiteException e) {
            Toast.makeText(context, "Operation failed: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            db.close();
        }
        return newId;
    }

    // Return all data stored in the DB as a List of tuples
    public List<Pair<String, Integer>> getAllCounters() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query(Config.COUNTER_TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null){
                if(cursor.moveToFirst()) {
                    List<Pair<String, Integer>> counters = new ArrayList<>();
                    do {
                        String name = cursor.getString(cursor.getColumnIndex(Config.COLUMN_COUNTER_NAME));
                        int value = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_COUNTER_VALUE));
                        counters.add(new Pair<>(name, value));
                    } while(cursor.moveToNext());
                    return counters;
                }
            }
        }
        catch (SQLiteException e) {
            Toast.makeText(context, "Operation failed: " + e, Toast.LENGTH_LONG).show();
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    // Clear the counter rows from the DB using an SQL Query
    public void clearDb(String name) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            String DROP_TABLE = String.format("DELETE FROM %s WHERE %s = '1' OR %s = '2' OR %s = '3';", Config.COUNTER_TABLE_NAME, Config.COLUMN_COUNTER_NAME, Config.COLUMN_COUNTER_NAME, Config.COLUMN_COUNTER_NAME);
            db.execSQL(DROP_TABLE);
        } catch (SQLiteException e) {
            Toast.makeText(context, "Operation failed: " + e, Toast.LENGTH_LONG).show();
        }
    }
}
