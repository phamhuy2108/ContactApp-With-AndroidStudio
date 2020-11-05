package com.example.doan_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {
    ListView lstBeforeAndAfters;
    ArrayList<BeforeAndAfter> beforeAndAfters;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        lstBeforeAndAfters = (ListView)findViewById(R.id.lstbeforeandafter);
        BeforeAndAfter item = new BeforeAndAfter("0162","032","Viettel");
        BeforeAndAfter item1 = new BeforeAndAfter("0163","033","Viettel");

        BeforeAndAfter item2 = new BeforeAndAfter("0164","034","Viettel");

        BeforeAndAfter item3 = new BeforeAndAfter("0165","035","Viettel");
        BeforeAndAfter item4 = new BeforeAndAfter("0166","036","Viettel");

        BeforeAndAfter item5 = new BeforeAndAfter("0167","037","Viettel");

        BeforeAndAfter item6 = new BeforeAndAfter("0168","038","Viettel");

        BeforeAndAfter item7 = new BeforeAndAfter("0169","039","Viettel");

        BeforeAndAfter item8 = new BeforeAndAfter("0120","070","Mobifone");
        BeforeAndAfter item9 = new BeforeAndAfter("0121","079","Mobifone");
        BeforeAndAfter item10 = new BeforeAndAfter("0122","077","Mobifone");
        BeforeAndAfter item11 = new BeforeAndAfter("0126","076","Mobifone");
        BeforeAndAfter item12 = new BeforeAndAfter("0128","078","Mobifone");
        BeforeAndAfter item13 = new BeforeAndAfter("0123","083","Vinaphone");
        BeforeAndAfter item14 = new BeforeAndAfter("0124","084","Vinaphone");
        BeforeAndAfter item15 = new BeforeAndAfter("0125","085","Vinaphone");
        BeforeAndAfter item16 = new BeforeAndAfter("0127","081","Vinaphone");
        BeforeAndAfter item17 = new BeforeAndAfter("0129","082","Vinaphone");
        BeforeAndAfter item18 = new BeforeAndAfter("0186","056","Vietnamobile");
        BeforeAndAfter item19 = new BeforeAndAfter("0188","058","Vietnamobile");
        BeforeAndAfter item20 = new BeforeAndAfter("0199","059","Gmobile");


        beforeAndAfters = new ArrayList<>();
        beforeAndAfters.add(item);
        beforeAndAfters.add(item1);
        beforeAndAfters.add(item2);
        beforeAndAfters.add(item3);
        beforeAndAfters.add(item4);
        beforeAndAfters.add(item5);
        beforeAndAfters.add(item6);
        beforeAndAfters.add(item7);
        beforeAndAfters.add(item8);
        beforeAndAfters.add(item9);
        beforeAndAfters.add(item10);
        beforeAndAfters.add(item11);
        beforeAndAfters.add(item12);
        beforeAndAfters.add(item13);
        beforeAndAfters.add(item14);
        beforeAndAfters.add(item15);
        beforeAndAfters.add(item16);
        beforeAndAfters.add(item17);
        beforeAndAfters.add(item18);
        beforeAndAfters.add(item19);
        beforeAndAfters.add(item20);
        adapter = new ArrayAdapter(MainActivity5.this,android.R.layout.simple_list_item_1,beforeAndAfters);
        lstBeforeAndAfters.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnu_insert2){
//            arrayOfUsers.add(new UserModel("Tom","LA City", R.drawable.azerbaijan));
//            adapter.notifyDataSetChanged();

        }

        return super.onOptionsItemSelected(item);
    }

}
