package com.example.messmanagementproject;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mydatabasehelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "booking.db";
    private static final int DATABASE_VERSION = 1;

    public Mydatabasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE info_table (id INTEGER PRIMARY KEY AUTOINCREMENT, info TEXT, isBooked INTEGER)");
        db.execSQL("CREATE TABLE admin_data (id INTEGER PRIMARY KEY, booking_count INTEGER)");
        db.execSQL("INSERT INTO admin_data (id, booking_count) VALUES (1, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS info_table");
        db.execSQL("DROP TABLE IF EXISTS admin_data");
        onCreate(db);
    }

    // ✔️ Moved these methods outside of onUpgrade ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    public void addInfo(String info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("info", info);
        cv.put("isBooked", 0);
        db.insert("info_table", null, cv);
    }

    public Cursor getUnbookedInfo() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM info_table WHERE isBooked = 0", null);
    }

    public void bookInfo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Mark the info as booked
        ContentValues cv = new ContentValues();
        cv.put("isBooked", 1);
        db.update("info_table", cv, "id=?", new String[]{String.valueOf(id)});

        // Increment the admin booking count
        db.execSQL("UPDATE admin_data SET booking_count = booking_count + 1 WHERE id = 1");
    }

    public int getBookingCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT booking_count FROM admin_data WHERE id = 1", null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }
    public void clearAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("info_table", null, null); // Delete all rows from info_table

        // Reset the booking count in admin_data table
        ContentValues cv = new ContentValues();
        cv.put("booking_count", 0);
        db.update("admin_data", cv, "id = ?", new String[]{"1"});
    }

}

