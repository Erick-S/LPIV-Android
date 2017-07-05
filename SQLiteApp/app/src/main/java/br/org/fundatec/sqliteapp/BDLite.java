package br.org.fundatec.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by tecnico on 28/06/2017.
 */

public class BDLite extends SQLiteOpenHelper {

    private static Integer version = 1;

    public BDLite(Context context) {
        super(context, "bdlite.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE person" +
                " ( id INTEGER PRIMARY KEY, name  TEXT, gender TEXT, birth TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }

    //C
    public Person insertPerson(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  cv = new ContentValues();
        cv.put("name", person.getName());
        cv.put("gender", person.getGender());
        cv.put("birth", person.getBirth());
        Long id = db.insert("person", null, cv);
        person.setId(id);
        return person;
    }

    //R
    public ArrayList<Person> selectAll(){
        ArrayList<Person> people = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name, gender, birth FROM person;", null);
        if(cursor.moveToFirst()){
            do{
                Person p = new Person();
                p.setId(cursor.getLong(0));
                p.setName(cursor.getString(1));
                p.setGender(cursor.getString(2));
                p.setBirth(cursor.getString(3));
                people.add(p);
            }while(cursor.moveToNext());
        }
        return people;
    }

    public Person select(Long id){
        Person person = new Person();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, name, gender, birth FROM person WHERE id = ?", new String[]{id.toString()});
        if(cursor.moveToFirst()){
            person.setId(cursor.getLong(0));
            person.setName(cursor.getString(1));
            person.setGender(cursor.getString(2));
            person.setBirth(cursor.getString(3));
        }
        return person;
    }

    //U
    public Person update(Person person){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  cv = new ContentValues();
        cv.put("name", person.getName());
        cv.put("gender", person.getGender());
        cv.put("birth", person.getBirth());
        db.update("person", cv, "id = ?", new String[]{person.getId().toString()});
        return person;
    }

    //D
    public void delete(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM person WHERE id = ?", new String[]{id.toString()});
    }

}
