package com.example.doan_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<ContactModel> {
    // SuccessResponse successResponse;

    public ContactAdapter(@NonNull Context context, @NonNull ArrayList<ContactModel> objects) {
        super(context, 0, objects);
    }
    //    public interface SuccessResponse{
//        void onSuccess();
//    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactModel contact = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvPhoneNumber = convertView.findViewById(R.id.tvPhoneNumber);
        ImageView imgSex = convertView.findViewById(R.id.imgSex);

        tvName.setText(contact.name);
        //tvPhoneNumber.setText(String.valueOf(contact.phonenumber));
        tvPhoneNumber.setText(contact.phonenumber);
        if(contact.sex > 0) {
            imgSex.setImageResource(contact.sex);
        }

        return convertView;
    }
}

