package com.example.doan_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    ListView lstContact;
    ArrayList<ContactModel> contactArrayList;
    ArrayAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lstContact = findViewById(R.id.lstContact);
        contactArrayList = new ArrayList<>();
        contactAdapter = new ArrayAdapter(MainActivity3.this,
                android.R.layout.simple_list_item_1,
                contactArrayList);

        lstContact.setAdapter(contactAdapter);
        //getContact();
        CheckPermissions();
    }

    private void getContact() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(uri,null, null,
                null, null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactArrayList.add(new ContactModel(name, phone,1));
        }

        contactAdapter.notifyDataSetChanged();
    }

    public void CheckPermissions(){
        if((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},100);
        }else {
            getContact();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                CheckPermissions();
            }else {
                Toast.makeText(MainActivity3.this, "Permission Deny", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
