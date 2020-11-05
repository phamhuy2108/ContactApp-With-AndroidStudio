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
import java.util.List;

public class ConAdapter extends ArrayAdapter<ContactModel> {
    // SuccessResponse successResponse;
    private Context mContext;
    private ArrayList<ContactModel> contactModels = new ArrayList<>();
    public static ArrayList<ContactModel> contactlist = new ArrayList<>();
    public ConAdapter(@NonNull Context context, @NonNull ArrayList<ContactModel> objects) {
        super(context, 0, objects);
        mContext = context;
        contactModels = objects;
    }
    public ConAdapter(Context context,int textViewResourceId,ArrayList<ContactModel>objects){
        super(context, textViewResourceId, objects);
    }
    //    public interface SuccessResponse{
//        void onSuccess();
//    }

    public void UpdateListView(ArrayList<ContactModel>contacts)
    {
        this.contactModels=contacts;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ContactModel contact = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact1, parent, false);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPhoneNumber = convertView.findViewById(R.id.tvPhoneNumber);
        ImageView imgSex = convertView.findViewById(R.id.imgSex);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);

        tvName.setText(contact.name);
//        final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                checked.set(position,checkBox.isChecked());
//            }
//        });
        //tvPhoneNumber.setText(String.valueOf(contact.phonenumber));
        tvPhoneNumber.setText(contact.phonenumber);
//        ContactModel contactModel = (ContactModel)getItem(position);
        if(contact.sex > 0) {
            imgSex.setImageResource(contact.sex);
        }
//        if(contact.isChecked()){
//            checkBox.setChecked(true);
//        }
//        else {
//            checkBox.setChecked(false);
//        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Code khi trạng thái check thay đổi
                if(b==true) {
                    contactlist.add(contact);
//                    Toast.makeText(
//                            compoundButton.getContext(),
//                            contact.name,
//                            Toast.LENGTH_SHORT).show();
                }
                else if(b==false)
                {
//                    int count =0;
//                    for(int a : contactlist){ dùng foreach thì sẽ không remove được nhưng for thì được
//                       // count++;
//                        if(contact.id == a){
//                            String c = Integer.toString(count);
//                            Toast.makeText(
//                                    compoundButton.getContext(),
//                                    c,
//                                    Toast.LENGTH_SHORT).show();
//                            //contactlist.remove(contactlist.indexOf(a));
//                            contactlist.remove(count);
//                    int count =1;
//                    for(int a : contactlist) {
//                        String c = Integer.toString(a);
//                        Toast.makeText(
//                                    compoundButton.getContext(),c
//                                    ,
//                                    Toast.LENGTH_SHORT).show();
//                        contactlist.remove(count);
//                        count++;
//                    }
                    for ( int i = 0;  i < contactlist.size(); i++){
                            if(contact == contactlist.get(i))
//                                Toast.makeText(
//                                    compoundButton.getContext(),contactlist.get(i).name
//                                    ,
//                                    Toast.LENGTH_SHORT).show();
                            contactlist.remove(i);

                        }
                    }

                }
        });

        return convertView;
    }
    public static ArrayList<ContactModel> getList(){
        return  contactlist;
    }
}

