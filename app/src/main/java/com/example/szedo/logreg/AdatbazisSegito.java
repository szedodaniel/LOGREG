package com.example.szedo.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/*
        HASZNÁLHATÓ LINKEK:
        http://sqlitebrowser.org/
        https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase.html
        https://developer.android.com/training/data-storage/sqlite.html
        https://developer.android.com/training/data-storage/room/index.html
 */

public class AdatbazisSegito extends SQLiteOpenHelper {

    //ADATBÁZIS FELÉPÍTÉSE

    public static final String DATABASE_NAME = "Felhasznalo.db";    //adatbázis file név
    public static final String TABLE_NAME = "Felhasznalo_zabla";    //tábla neve

    public static final String COL_1 = "ID";            //első oszlop neve
    public static final String COL_2 = "FNEV";    //második oszlop neve
    public static final String COL_3 = "TNEV";   //harmadik oszlop neve
    public static final String COL_4 = "JELSZO";   //harmadik oszlop neve


    //konstruktor felvétele

    public AdatbazisSegito(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    //LÉTREHOZZUK A TÁBLÁT ÉS A BENNE LÉVŐ OSZLOPOKHOZ TÍPUST ADUNK
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FNEV TEXT,TNEV TEXT, JELSZO TEXT)");
    }

    //DOBJA EL A TÁBLÁT HA MÁR ILYEN LÉTEZIK

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    //adat felvétel

    public boolean adatRogzites(String felhasznalo,String teljesnev, String jelszo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, felhasznalo);
        contentValues.put(COL_3, teljesnev);
        contentValues.put(COL_4, jelszo);


        long eredmeny = db.insert(TABLE_NAME, null, contentValues);

        if (eredmeny == -1)
        {
            return false;           //sikertelen adatfelvétel
        }else
            return true;            //sikeres adatfelvétel
    }

    //adatlekérdezés

    public Cursor adatLekerdezes(String fnev,String jelszo)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor eredmeny = db.rawQuery("SELECT *FROM " + TABLE_NAME + " WHERE " + COL_2 + "=? AND " + COL_4 + "=?", new String[]{fnev, jelszo});


        return eredmeny;
    }
    public Cursor felhasznaloLekerdezes(String fnev)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor eredmeny = db.rawQuery("SELECT *FROM " + TABLE_NAME + " WHERE " + COL_2, new String[]{fnev});
        return eredmeny;
    }
}
