package com.example.temporary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class IdeasDataBaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Ideas_DB";
    public static final String TABLE_IDEAS = "Ideas";
    public static final String KEY_ID = "ID";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SHORT = "shortdesc";
    public static final String KEY_LONG = "longdesc";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_IMAGE = "image";

    public IdeasDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
        this.onCreate(db);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("drop table Ideas");
        db.execSQL("create table Ideas (ID INTEGER PRIMARY KEY AUTOINCREMENT, title text, shortdesc text, longdesc text, author integer, image text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    boolean insertIdea(Idea idea) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, idea.getId());
        contentValues.put(KEY_TITLE, idea.getTitle());
        contentValues.put(KEY_SHORT, idea.getShortdesc());
        contentValues.put(KEY_LONG, idea.getLongdesc());
        contentValues.put(KEY_AUTHOR, idea.getAuthor());
        contentValues.put(KEY_IMAGE, idea.getImage());
        db.insert(TABLE_IDEAS, null, contentValues);
        db.close();
        return true;
    }

    Idea parse(Cursor cursor) {
        cursor.moveToFirst();
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID)));
        String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
        String shortdesc = cursor.getString(cursor.getColumnIndex(KEY_SHORT));
        String longdesc = cursor.getString(cursor.getColumnIndex(KEY_LONG));
        int author =  Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AUTHOR)));
        String image = cursor.getString(cursor.getColumnIndex(KEY_IMAGE));
        return new Idea(id, title, shortdesc, longdesc, author, image);
    }

    Idea getIdeaById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Ideas where ID = " + id, null);
        return parse(res);
    }

    List<Idea> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Idea> ideas = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Ideas", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ideas.add(parse(cursor));
            cursor.moveToNext();
        }
        return ideas;
    }
}
