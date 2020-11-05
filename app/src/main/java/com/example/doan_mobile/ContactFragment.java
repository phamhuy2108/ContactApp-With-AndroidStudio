package com.example.doan_mobile;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends ListFragment {
    ListView listView;
    ArrayList<ContactModel> arrayOfContacts;
    ArrayList<ContactModel> arrayOfContactsCopy;
    ContactAdapter adapter;
    int pos;
    private static final int REQUEST_CALL =1;

    ContactHelper contactHelper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        registerForContextMenu(getListView());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        contactHelper = new ContactHelper(ContactFragment.this.getContext());
        contactHelper.createContactTable();
        arrayOfContacts = new ArrayList<>();
        arrayOfContactsCopy =new ArrayList<>();
        listView = v.findViewById(android.R.id.list);
        adapter = new ContactAdapter(this.getContext(), arrayOfContacts);
        setListAdapter(adapter);
        //registerForContextMenu(v.findViewById(R.id.lvItems));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
//        String number = arrayOfContacts.get(position).phonenumber;
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.setData(Uri.parse("tel:" +number));
//        startActivity(intent);
        DialogCallOrMessage(position);
        //Toast.makeText(ContactFragment.this.getActivity(), arrayOfContacts.get(position).name, Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<ContactModel> tmp = new ArrayList<>();
                for(ContactModel contactModel : arrayOfContactsCopy){
                    if(contactModel.name.toLowerCase().contains(newText.toLowerCase()) || contactModel.phonenumber.toLowerCase().contains(newText.toLowerCase())){
                        tmp.add(contactModel);
                    }
                }

                if(tmp.size() > 0){
                    adapter.clear();
                    adapter.addAll(tmp);
                    adapter.notifyDataSetChanged();
                }
                if(tmp.size()== 0){
                    listView.setVisibility(View.GONE);
                }

                if(newText.isEmpty()){
                    listView.setVisibility(View.VISIBLE);
                }

                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnu_insert){
//            arrayOfUsers.add(new UserModel("Tom","LA City", R.drawable.azerbaijan));
//            adapter.notifyDataSetChanged();
            Intent intent = new Intent(getActivity(),
                    CreateOrUpdateActivity.class);
            intent.putExtra("createOrUpdate", 1);
            startActivityForResult(intent, 100);

        }

        if(item.getItemId() == R.id.mnu_sort){
            Collections.sort(arrayOfContacts);
            adapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        if(item.getItemId() == R.id.mnu_message){
//            pos = adapterContextMenuInfo.position;
//            ContactModel contact = arrayOfContacts.get(adapterContextMenuInfo.position);
//            String number = contact.phonenumber;
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
////            intent.putExtra("createOrUpdate", 2);
////            intent.putExtra("name", contact.name);
////            String phone = String.valueOf(contact.phonenumber);
////            intent.putExtra("phonenumber", contact.phonenumber);
////            intent.putExtra("sex", contact.sex);
////            intent.putExtra("id", contact.id);
//            startActivity(intent);
//        }
        if(item.getItemId() == R.id.mnu_update){
            pos = adapterContextMenuInfo.position;
            ContactModel contact = arrayOfContacts.get(adapterContextMenuInfo.position);
//
//            user.names = "Leo";
//            user.hometowns ="Madrid";
//            user.flag = R.drawable.bahamas;
//
//            arrayOfUsers.set(adapterContextMenuInfo.position,user);
//            adapter.notifyDataSetChanged();

            Intent intent = new Intent(getActivity(), CreateOrUpdateActivity.class);
            intent.putExtra("createOrUpdate", 2);
            intent.putExtra("name", contact.name);
            String phone = String.valueOf(contact.phonenumber);
            intent.putExtra("phonenumber", contact.phonenumber);
            intent.putExtra("sex", contact.sex);
            intent.putExtra("id", contact.id);
            startActivityForResult(intent, 102);



        }
        if(item.getItemId() == R.id.mnu_delete){
            final ContactModel contact = arrayOfContacts.get(adapterContextMenuInfo.position);
            AlertDialog.Builder builder = new AlertDialog.Builder(ContactFragment.this.getActivity());
            builder.setTitle("CONTACTS APP");
            builder.setMessage("Are you delete  " +  contact.name +"?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    arrayOfContacts.remove(contact);
                    contactHelper.deleteContact(contact);
                    adapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });


            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }

        return super.onContextItemSelected(item);
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onResume();
        //getActivity().getWindow().set(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

        adapter.clear();
        arrayOfContacts.addAll(contactHelper.getContacts());
        arrayOfContactsCopy.clear();
        arrayOfContactsCopy.addAll(contactHelper.getContacts());
       adapter.notifyDataSetChanged();
    }
    private void DialogCallOrMessage(final int postiton){
        //Dialog dialog = new Dialog(this.getActivity(),R.style.Theme_Dialog);
        final Dialog dialog = new Dialog(this.getActivity());
        dialog.setContentView(R.layout.dialog_custom);
        //dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        Button btCall = (Button)dialog.findViewById(R.id.btCall);
        Button btMessage = (Button)dialog.findViewById(R.id.btMessage);

        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(ContactFragment.this.getActivity(),
                        Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ContactFragment.this.getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                }else {
                    String number = arrayOfContacts.get(postiton).phonenumber;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" +number));
                    dialog.cancel();
                    startActivity(intent);
                }
            }
        });
        btMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = postiton;
                ContactModel contact = arrayOfContacts.get(postiton);
                String number = contact.phonenumber;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
                dialog.cancel();
                startActivity(intent);
            }
        });
        dialog.show();
    }
    //    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.clear();
//        arrayOfContacts.addAll(contactHelper.getContacts());
//        arrayOfContactsCopy.addAll(contactHelper.getContacts());
//        adapter.notifyDataSetChanged();
//    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
