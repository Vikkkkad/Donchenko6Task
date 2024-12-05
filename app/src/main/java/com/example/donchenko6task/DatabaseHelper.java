package com.example.donchenko6task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.donchenko6task.models.Reminder;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reminders.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_REMINDERS = "reminders";
    private static final String COLUMN_ID = "reminderId"; // Изменено название столбца
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_TEXT = "text";
    private static final String COLUMN_DATETIME = "datetime";

    private static final String CREATE_TABLE_REMINDERS =
            "CREATE TABLE " + TABLE_REMINDERS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_TITLE + " TEXT, "
                    + COLUMN_TEXT + " TEXT, "
                    + COLUMN_DATETIME + " INTEGER)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы при первом запуске
        db.execSQL(CREATE_TABLE_REMINDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаление старой таблицы при обновлении
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REMINDERS);
        onCreate(db);
    }

    public long addReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, reminder.getTitle());
        values.put(COLUMN_TEXT, reminder.getText());
        values.put(COLUMN_DATETIME, reminder.getDateTime());

        long id = db.insert(TABLE_REMINDERS, null, values);
        db.close();
        return id;
    }

    public List<Reminder> getAllReminders() {
        List<Reminder> reminderList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_REMINDERS +
                " ORDER BY " + COLUMN_DATETIME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Reminder reminder = new Reminder(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT)),
                        cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATETIME))
                );

                reminder.setReminderId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));

                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return reminderList;
    }

    public void deleteReminder(long reminderId) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_REMINDERS,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(reminderId)});

        db.close();
    }
}