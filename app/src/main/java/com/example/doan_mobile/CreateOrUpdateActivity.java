package com.example.doan_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.doan_mobile.ContactHelper;
import com.example.doan_mobile.ContactModel;
import com.example.doan_mobile.MainActivity;
import com.example.doan_mobile.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CreateOrUpdateActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText edtName, edtPhoneNumber;
    RadioButton rdb, rdb2, rdb3, rdb4;
    int createOrUpdate;
    ContactHelper contactHelper;
    int idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_update);

        contactHelper = new ContactHelper(this);

        rdb = findViewById(R.id.radioButton);
        rdb2 = findViewById(R.id.radioButton2);

        radioGroup = findViewById(R.id.radioGroup);


        edtName = findViewById(R.id.editText);
        edtPhoneNumber = findViewById(R.id.editText2);

        Intent intent = getIntent();
        createOrUpdate =
                intent.getIntExtra("createOrUpdate", 0);


        if (createOrUpdate == 1) {
            getSupportActionBar().setTitle("Create new user");
        }
        if (createOrUpdate == 2) {
            getSupportActionBar().setTitle("Update user");

            edtName.setText(intent.getStringExtra("name"));
            edtPhoneNumber.setText(intent.getStringExtra("phonenumber"));
            int sex = intent.getIntExtra("sex", 0);
            idUser = intent.getIntExtra("id", 0);
            switch (sex) {
                case R.drawable.male:
                    rdb.setChecked(true);
                    break;
                case R.drawable.female:
                    rdb2.setChecked(true);
                    break;

            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return super.onSupportNavigateUp();
    }

    MenuItem itemEdit, itemSave;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.process_menu, menu);

        itemEdit = menu.findItem(R.id.mnuEdit);
        itemEdit.setVisible(false);
        itemSave = menu.findItem(R.id.mnuSave);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {

            if (createOrUpdate == 1) {
                try {
                    Intent intent = new Intent();
                    int id = radioGroup.getCheckedRadioButtonId();
                    int sex=0;
                    switch (id) {
                        case R.id.radioButton:
                            intent.putExtra("sex", R.drawable.male);
                            sex = R.drawable.male;
                            break;
                        case R.id.radioButton2:
                            intent.putExtra("sex", R.drawable.female);
                            sex = R.drawable.female;
                            break;
                    }

                    intent.putExtra("name", edtName.getText().toString());
                    intent.putExtra("phonenumber", edtPhoneNumber.getText().toString());
                    //int phone = Integer.parseInt( edtPhoneNumber.getText().toString());
                    contactHelper.insertContact(new ContactModel(edtName.getText().toString(),
                                    edtPhoneNumber.getText().toString(),
                            sex  ));

                    setResult(101, intent);

                    Toast.makeText(this, "OKEEEEEEEEEEE", Toast.LENGTH_SHORT).show();
//                    Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
//                    ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
//
//                    ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
//                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
//                            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
//
//                    // Names
//                    if (edtName.getText().toString() != null) {
//                        ops.add(ContentProviderOperation
//                                .newInsert(ContactsContract.Data.CONTENT_URI)
//                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                                .withValue(ContactsContract.Data.MIMETYPE,
//                                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
//                                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
//                                        edtName.getText().toString()).build());
//                    }
//
//                    // Mobile Number
//                    if (edtPhoneNumber.getText().toString() != null) {
//                        ops.add(ContentProviderOperation
//                                .newInsert(ContactsContract.Data.CONTENT_URI)
//                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//                                .withValue(ContactsContract.Data.MIMETYPE,
//                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, edtPhoneNumber.getText().toString())
//                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
//                                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());
//                    }
//
//                    try {
//                        getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    Intent intenta = new Intent(this, MainActivity.class);
                    startActivity(intenta);

                } catch (Exception ex) {
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }

            if (createOrUpdate == 2) {
                String abc = edtPhoneNumber.getText().toString();
                try {
                    Intent intent = new Intent();
                    int id = radioGroup.getCheckedRadioButtonId();
                    int sex=0;
                    switch (id) {
                        case R.id.radioButton:
                            intent.putExtra("sex", R.drawable.male);
                            sex = R.drawable.male;
                            break;
                        case R.id.radioButton2:
                            intent.putExtra("sex", R.drawable.female);
                            sex = R.drawable.female;
                            break;

                    }

                    intent.putExtra("name", edtName.getText().toString());
                    //intent.putExtra("phonenumber", edtPhoneNumber.getText().toString());
                    intent.putExtra("phonenumber", edtPhoneNumber.getText().toString());
                    // int phone = Integer.parseInt( edtPhoneNumber.getText().toString());
                    contactHelper.updateContact(new ContactModel(edtName.getText().toString(),
                                    edtPhoneNumber.getText().toString(),
                            sex ,idUser ));

                    setResult(103, intent);
                    Toast.makeText(this, "OKE", Toast.LENGTH_SHORT).show();

                } catch (Exception ex) {
                   // Log.d(edtPhoneNumber.getText().toString(),"Lỗi tại đây nè mày");
                    Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        }


        return super.onOptionsItemSelected(item);
    }


}
