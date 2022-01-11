package com.example.app_covid_nangcap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Sqlite extends SQLiteOpenHelper {

    public static final String TableName = "Information";
    public static final String Id = "Id";
    public static final String Name = "Fullname";
    public static final String Birthday = "Birthday";
    public static final String Gender = "Gender";
    public static final String Citizen = "Citizen";
    public static final String Phone = "Phone";
    public static final String Address = "Address";
    public static final String Departure = "Departure";
    public static final String DepartureDay = "DepartureDay";
    public static final String Destination = "Destination";
    public static final String DestinationDay = "DestinationDay";
    public static final String Schedule = "Schedule";
    public static final String Health = "Health";
    public Sqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // sql tạo bảng
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + TableName + "(" +
                Id + " Integer PRIMARY KEY AUTOINCREMENT, " +
                Name + " Text, " +
                Birthday + " Text, " +
                Gender + " Text, " +
                Citizen + " Text, " +
                Phone + " Text, " +
                Address + " Text, " +
                Departure + " Text, " +
                DepartureDay + " Text, " +
                Destination + " Text, " +
                DestinationDay + " Text, " +
                Schedule + " Text, " +
                Health + " Text); ";


//        Thực thi
        sqLiteDatabase.execSQL(sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //xóa table đã có
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableName);
        //tạo lại bảng
        onCreate(sqLiteDatabase);

    }

    public void addHoaDon(InforModel hd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name, hd.getName());
        values.put(Birthday, hd.getBirthday());
        values.put(Gender, hd.getGender());
        values.put(Citizen, hd.getCitizen());
        values.put(Phone, hd.getPhone());
        values.put(Address, hd.getAddress());
        values.put(Departure, hd.getDeparture());
        values.put(DepartureDay, hd.getDepartureDay());
        values.put(Destination, hd.getDestination());
        values.put(DestinationDay, hd.getDestinationDay());
        values.put(Schedule, hd.getSchuedule());
        values.put(Health, hd.getHealth());
        long rowInserted = db.insert(TableName, null, values);
        db.close();
        if (rowInserted != -1) {
            //Insert success.

        } else {
            //Inser failed.
        }
    }

    public void editHoaDon(int id,InforModel hd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, hd.getId());
        values.put(Name, hd.getName());
        values.put(Phone, hd.getPhone());
        values.put(Birthday, hd.getBirthday());
        values.put(Gender, hd.getGender());
        values.put(Citizen, hd.getCitizen());
        values.put(Address, hd.getAddress());
        values.put(Departure, hd.getDeparture());
        values.put(DepartureDay, hd.getDepartureDay());
        values.put(Destination, hd.getDestination());
        values.put(DestinationDay, hd.getDestinationDay());
        values.put(Schedule, hd.getSchuedule());
        values.put(Health, hd.getHealth());
        db.update(TableName,values,Id + "=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public ArrayList<InforModel> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<InforModel> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TableName +" ORDER BY "+Id +" DESC";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                InforModel ct = new InforModel();
                ct.setId(cursor.getInt(0));
                ct.setName(cursor.getString(1));
                ct.setBirthday(cursor.getString(2));
                ct.setGender(cursor.getString(3));
                ct.setCitizen(cursor.getString(4));
                ct.setPhone(cursor.getString(5));
                ct.setAddress(cursor.getString(6));
                ct.setDeparture(cursor.getString(7));
                ct.setDepartureDay(cursor.getString(8));
                ct.setDestination(cursor.getString(9));
                ct.setDestinationDay(cursor.getString(10));
                ct.setSchuedule(cursor.getString(11));
                ct.setHealth(cursor.getString(12));
                list.add(ct);
            }
        }
        return list;
    }
    public void deleteHoaDon(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TableName +" WHERE ID = " + id;
        db.execSQL(sql);
        db.close();
    }
}
