package com.example.doan_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ContactHelper {
    Context context;

    String dbName = "ContactDB.db";


    public ContactHelper(Context context) {
        this.context = context;
    }

    private SQLiteDatabase openDB() {
        return context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
    }

    private void closeDB(SQLiteDatabase db) {
        db.close();
    }

    public void createContactTable() {
        SQLiteDatabase db = openDB();
        String sql = "create table if not exists tblContact(" +
                "id integer PRIMARY KEY autoincrement, " +
                "name text, " +
                "phonenumber text," +
                "sex integer);";
        db.execSQL(sql);
        closeDB(db);
    }

    public void insertContact(ContactModel contact) {
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("name", contact.name);
        cv.put("phonenumber", contact.phonenumber);
        cv.put("sex", contact.sex);
        db.insert("tblContact", null, cv);
        closeDB(db);
    }
    public void insertContact1(String name,String phonenumber, int sex) {
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("phonenumber",phonenumber);
        cv.put("sex",sex);
        db.insert("tblContact", null, cv);
        closeDB(db);
    }

    public void updateContact(ContactModel contact) {
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        cv.put("name", contact.name);
        cv.put("phonenumber", contact.phonenumber);
        cv.put("sex", contact.sex);
        String[] id = {String.valueOf(contact.id)};
        int row = db.update("tblContact", cv, "id = ?", id);
        closeDB(db);
    }

    public void deleteContact(ContactModel contact) {
        String[] id = {String.valueOf(contact.id)};
        SQLiteDatabase db = openDB();
        db.delete("tblContact", "id = ?", id);
        closeDB(db);
    }

    public ArrayList<ContactModel> getContacts() {
        SQLiteDatabase db = openDB();
        ArrayList<ContactModel> arr = new ArrayList<>();
        String sql = "select * from tblContact";
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String phonenumber = csr.getString(2);
                    int sex = csr.getInt(3);
                    arr.add(new ContactModel(name, phonenumber, sex,id));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }

    private ContactModel getContact(int uId) {
        String sql="Select * from tblContact where id = "+ uId;
        SQLiteDatabase db = openDB();

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null)
            cursor.moveToFirst();
        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        String phonenumber = cursor.getString(2);
        int sex = cursor.getInt(3);
        closeDB(db);
        return new ContactModel(name,phonenumber,sex,id);
    }
//    public ContactModel getContact11() {
//        String c = "phonenumber.substring(4)";
//        String sql="Select * from tblContact where" + c + "=" + "01234" ;
//        SQLiteDatabase db = openDB();
//
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//        int id = cursor.getInt(0);
//        String name = cursor.getString(1);
//        String phonenumber = cursor.getString(2);
//        int sex = cursor.getInt(3);
//        closeDB(db);
//        return new ContactModel(name,phonenumber,sex,id);
//    }
    public ArrayList<ContactModel> getContacts11() {
       // String c = "phonenumber.substring(4)";
        SQLiteDatabase db = openDB();
        ArrayList<ContactModel> arr = new ArrayList<>();
        String sql = "Select * from tblContact where LENGTH(phonenumber)=" + 11  ;
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String phonenumber = csr.getString(2);
                    int sex = csr.getInt(3);
                    arr.add(new ContactModel(name, phonenumber, sex,id));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }
    public ArrayList<ContactModel> getContacts11Same() {
        // String c = "phonenumber.substring(4)";
        SQLiteDatabase db = openDB();
        ArrayList<ContactModel> arr = new ArrayList<>();
        String sql = "Select * from tblContact where phonenumber IN ( SELECT phonenumber FROM tblContact GROUP BY phonenumber HAVING COUNT(phonenumber) > 1) ORDER BY phonenumber"  ;
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String phonenumber = csr.getString(2);
                    int sex = csr.getInt(3);
                    arr.add(new ContactModel(name, phonenumber, sex,id));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }
    public ArrayList<ContactModel> getContact11() {
        // String c = "phonenumber.substring(4)";
        SQLiteDatabase db = openDB();
        ArrayList<ContactModel> arr = new ArrayList<>();
        String sql = "Select * from tblContact where substr(phonenumber,1,4) = "+"'0123'"
                + "OR substr(phonenumber,1,4)='0163'"
                + "OR substr(phonenumber,1,4)='0164'"
                + "OR substr(phonenumber,1,4)='0165'"
                + "OR substr(phonenumber,1,4)='0166'"
                + "OR substr(phonenumber,1,4)='0167'"
                + "OR substr(phonenumber,1,4)='0168'"
                + "OR substr(phonenumber,1,4)='0169'"
                + "OR substr(phonenumber,1,4)='0120'"
                + "OR substr(phonenumber,1,4)='0121'"
                + "OR substr(phonenumber,1,4)='0122'"
                + "OR substr(phonenumber,1,4)='0126'"
                + "OR substr(phonenumber,1,4)='0128'"
                + "OR substr(phonenumber,1,4)='0123'"
                + "OR substr(phonenumber,1,4)='0124'"
                + "OR substr(phonenumber,1,4)='0125'"
                + "OR substr(phonenumber,1,4)='0127'"
                + "OR substr(phonenumber,1,4)='0129'"
                + "OR substr(phonenumber,1,4)='0186'"
                + "OR substr(phonenumber,1,4)='0188'"
                + "OR substr(phonenumber,1,4)='0199'"
                + "AND length(phonenumber)=" + 11 ;
        String[] selectionArgs = {"abc"};
        Cursor csr = db.rawQuery(sql, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                do {
                    int id = csr.getInt(0);
                    String name = csr.getString(1);
                    String phonenumber = csr.getString(2);
                    int sex = csr.getInt(3);
                    arr.add(new ContactModel(name, phonenumber, sex,id));
                } while (csr.moveToNext());
            }
        }
        closeDB(db);
        return arr;
    }
    public void updateContact11(ContactModel contact) {
        SQLiteDatabase db = openDB();
        ContentValues cv = new ContentValues();
        int dauso = Integer.parseInt((contact.phonenumber).substring(0,4));
        String duoiso = (contact.phonenumber).substring(4);
        switch (dauso){
            case 162:
                String somoi = "032" + duoiso;
                cv.put("phonenumber", somoi);
                break;
            case 163:
                String somoi1 = "033" + duoiso;
                cv.put("phonenumber", somoi1);
                break;
            case 164:
                String somoi2 = "034" + duoiso;
                cv.put("phonenumber", somoi2);
                break;
            case 165:
                String somoi3 = "035" + duoiso;
                cv.put("phonenumber", somoi3);
                break;
            case 166:
                String somoi4 = "036" + duoiso;
                cv.put("phonenumber", somoi4);
                break;
            case 167:
                String somoi5 = "037" + duoiso;
                cv.put("phonenumber", somoi5);
                break;
            case 168:
                String somoi6 = "038" + duoiso;
                cv.put("phonenumber", somoi6);
                break;
            case 169:
                String somoi7 = "039" + duoiso;
                cv.put("phonenumber", somoi7);
                break;
            case 120:
                String somoi8 = "070" + duoiso;
                cv.put("phonenumber", somoi8);
                break;
            case 121:
                String somoi9 = "079" + duoiso;
                cv.put("phonenumber", somoi9);
                break;
            case 122:
                String somoi10 = "077" + duoiso;
                cv.put("phonenumber", somoi10);
                break;
            case 126:
                String somoi11 = "076" + duoiso;
                cv.put("phonenumber", somoi11);
                break;
            case 128:
                String somoi12 = "078" + duoiso;
                cv.put("phonenumber", somoi12);
                break;
            case 123:
                String somoi13 = "083" + duoiso;
                cv.put("phonenumber", somoi13);
                break;
            case 124:
                String somoi14 = "084" + duoiso;
                cv.put("phonenumber", somoi14);
                break;
            case 125:
                String somoi15 = "085" + duoiso;
                cv.put("phonenumber", somoi15);
                break;
            case 127:
                String somoi16 = "081" + duoiso;
                cv.put("phonenumber", somoi16);
                break;
            case 129:
                String somoi17 = "082" + duoiso;
                cv.put("phonenumber", somoi17);
                break;
            case 186:
                String somoi18 = "056" + duoiso;
                cv.put("phonenumber", somoi18);
                break;
            case 188:
                String somoi19 = "058" + duoiso;
                cv.put("phonenumber", somoi19);
                break;
//            case 199:
//                String somoi20 = "059" + duoiso;
//                cv.put("phonenumber", somoi20);
            default:
                String somoi20 = "059" + duoiso;
                cv.put("phonenumber", somoi20);
                break;
        }
//        String somoi = "083" + duoiso;
//        cv.put("phonenumber", somoi);
//        if(dauso.equals("0123")==true){
//            String somoi = "083" + duoiso;
//                cv.put("phonenumber", somoi);
//        }
//        else{
//            String somoi = "033" + duoiso;
//            cv.put("phonenumber", somoi);
//        }
        String[] id = {String.valueOf(contact.id)};
        db.update("tblContact", cv, "id = ?", id);
        closeDB(db);
    }
}
