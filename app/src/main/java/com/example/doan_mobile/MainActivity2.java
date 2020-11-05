package com.example.doan_mobile;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    ArrayList<ContactModel> arrayOfContacts;
    ArrayList<ContactModel> arrayOfContactsCopy;
    CheckBox checkBox;
    Button button;
    ConAdapter adapter;
    //ArrayAdapter<ContactModel> adapter;
    ArrayList<ContactModel>xoaso = ConAdapter.getList();

    int pos;

    ContactHelper contactHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        contactHelper = new ContactHelper(MainActivity2.this);
        contactHelper.createContactTable();

        arrayOfContacts = new ArrayList<>();
        arrayOfContactsCopy =new ArrayList<>();

        adapter = new ConAdapter(this, arrayOfContacts);
        listView =  findViewById(R.id.lvItems);
        listView.setAdapter(adapter);
        //checkBox = findViewById(R.id.checkBox);
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                //Code khi trạng thái check thay đổi
//                Toast.makeText(
//                        MainActivity2.this,
//                        "hgfhgfhgf",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                checkBox = (CheckBox) listView.getAdapter().getView(position,view , null).findViewById(R.id.checkBox);
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                } else {
                    checkBox.setChecked(true);
                }
//                Toast.makeText(MainActivity2.this, "Position " + position, Toast.LENGTH_LONG).show();
            }
        });
        registerForContextMenu(listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnu_insert){
//            arrayOfUsers.add(new UserModel("Tom","LA City", R.drawable.azerbaijan));
//            adapter.notifyDataSetChanged();
//            Intent intent = new Intent(MainActivity2.this,
//                    CreateOrUpdateActivity.class);
//            intent.putExtra("createOrUpdate", 1);
//            startActivityForResult(intent, 100);
        for(ContactModel a : xoaso){
            arrayOfContacts.remove(a);
            contactHelper.deleteContact(a);
            adapter.notifyDataSetChanged();
        }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        arrayOfContacts.addAll(contactHelper.getContacts());
        arrayOfContactsCopy.addAll(contactHelper.getContacts());
        adapter.notifyDataSetChanged();
    }
}
