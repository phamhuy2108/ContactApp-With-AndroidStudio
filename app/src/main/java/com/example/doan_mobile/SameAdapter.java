package com.example.doan_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SameAdapter extends ArrayAdapter<ContactModel> {
    private Context mContext;
    public static CheckBox checkBox;
    private ArrayList<ContactModel> contactModels = new ArrayList<>();
    public static ArrayList<ContactModel> contactlist = new ArrayList<>();

    public SameAdapter(@NonNull Context context, @NonNull ArrayList<ContactModel> objects) {
        super(context, 0, objects);
        mContext = context;
        contactModels = objects;
    }
    public SameAdapter(Context context,int textViewResourceId,ArrayList<ContactModel>objects){
        super(context, textViewResourceId, objects);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ContactModel contact = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact1, parent, false);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPhoneNumber = convertView.findViewById(R.id.tvPhoneNumber);
        ImageView imgSex = convertView.findViewById(R.id.imgSex);

        checkBox = convertView.findViewById(R.id.checkBox);
        checkBox.setChecked(false);
        tvName.setText(contact.name);

        tvPhoneNumber.setText(contact.phonenumber);

        if(contact.sex > 0) {
            imgSex.setImageResource(contact.sex);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Code khi trạng thái check thay đổi
                if(b==true) {
                    contactlist.add(contact);
                    Toast.makeText(
                            compoundButton.getContext(),
                            contact.name,
                            Toast.LENGTH_SHORT).show();
                }
                else if(b==false)
                {
                    for ( int i = 0;  i < contactlist.size(); i++){
                        if(contact == contactlist.get(i))
                            contactlist.remove(i);
                    }
                }
            }
        });

        return convertView;
    }
    public static void clearCheckbox()
    {
        checkBox.setChecked(false);
    }

    public static ArrayList<ContactModel> getList(){
        return  contactlist;
    }
}

