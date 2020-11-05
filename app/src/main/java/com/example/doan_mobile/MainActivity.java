package com.example.doan_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.example.doan_mobile.CallFragment;
import com.example.doan_mobile.ContactFragment;
import com.example.doan_mobile.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private ContactHelper contactHelper = new ContactHelper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//        toolbar.setTitle("Shop");
        toolbar.setTitle("Call");
        contactHelper.createContactTable();
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if(firstStart){
            showStartDialog();
        }
        loadFragment(new CallFragment());
    }
    private void showStartDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Đồng bộ danh bạ")
                .setMessage("Bạn có muốn đồng bộ danh bạ vào app không?")
                .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CheckPermissions();
                    }
                })
                .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_call:
                    toolbar.setTitle("Call");
                    fragment = new CallFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_contact:
                    toolbar.setTitle("Contact");
                    fragment = new ContactFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_change:
                    toolbar.setTitle("Change 11 number");
                    fragment = new ChangeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_same:
                    toolbar.setTitle("Same");
                    fragment = new SameFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
            //Log.d(name,"Lỗi sml luôn");
            contactHelper.insertContact(new ContactModel(name,phone,2131230851));
        }
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
                Toast.makeText(MainActivity.this, "Permission Deny", Toast.LENGTH_SHORT).show();
            }
        }

    }

}

