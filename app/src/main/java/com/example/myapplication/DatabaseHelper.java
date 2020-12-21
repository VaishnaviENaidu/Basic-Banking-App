package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(7395167309,'Lucy Shore',8420.00,'lucyshore04@gmail.com','XXXXXXXXXXXX4213','CAB84156257')");
        db.execSQL("insert into user_table values(3985741068,'Rohan Patel',992.67,'rohanpatel.01@gmail.com','XXXXXXXXXXXX7841','BCA98765432')");
        db.execSQL("insert into user_table values(2970115468,'Kyra Mehra',2377.56,'Kyra01@gmail.com','XXXXXXXXXXXX3412','ABC87654321')");
        db.execSQL("insert into user_table values(6374999584,'Jane Thompson',560.01,'jane.05@gmail.com','XXXXXXXXXXXX8826','CAB76543210')");
        db.execSQL("insert into user_table values(9941462857,'Mihir Rane',7660.48,'mihir.06@gmail.com','XXXXXXXXXXXX0786','BCA65432109')");
        db.execSQL("insert into user_table values(8820591436,'Anjali Nair',800.16,'anjalin.07@gmail.com','XXXXXXXXXXXX3321','ABC54321098')");
        db.execSQL("insert into user_table values(9086553719,'Kushi Naidu',767.00,'kushi007@gmail.com','XXXXXXXXXXXX4523','CAB43210987')");
        db.execSQL("insert into user_table values(9987238120,'Meera Shetty',3339.22,'meera.09@gmail.com','XXXXXXXXXXXX6894','BCA32109876')");
        db.execSQL("insert into user_table values(7549971108,'Krish Shah',480.46,'krishshah.10@gmail.com','XXXXXXXXXXXX2135','ABC21098765')");
        db.execSQL("insert into user_table values(2018998056,'Harsh Mehta',2000.90,'Harsh.mehta@gmail.com','XXXXXXXXXXXX6513','CAB10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
