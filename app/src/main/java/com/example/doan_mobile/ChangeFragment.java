package com.example.doan_mobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChangeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeFragment extends ListFragment {
    ListView listView;
    Button button;
    ArrayList<ContactModel> arrayOfContacts;
    ArrayList<ContactModel> arrayOfContactsCopy;
    ContactAdapter adapter;
    int pos;

    ContactHelper contactHelper;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangeFragment newInstance(String param1, String param2) {
        ChangeFragment fragment = new ChangeFragment();
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu2, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_change, container, false);
        contactHelper = new ContactHelper(ChangeFragment.this.getContext());
        contactHelper.createContactTable();
        arrayOfContacts = new ArrayList<>();
        arrayOfContactsCopy =new ArrayList<>();
        adapter = new ContactAdapter(this.getContext(), arrayOfContacts);
        setListAdapter(adapter);
        ListView lv = (ListView)view.findViewById(android.R.id.list);
        TextView emptyText = (TextView)view.findViewById(android.R.id.empty);
        lv.setEmptyView(emptyText);
        button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeChange();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void makeChange(){
        ArrayList<ContactModel> contactModels = contactHelper.getContact11();
        for (ContactModel a : contactModels){
            if(a!=null){
                contactHelper.updateContact11(a);
                arrayOfContacts.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(ChangeFragment.this.getActivity(),"Change Successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(ChangeFragment.this.getActivity(), "No Number Have To Change", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mnu_insert2){
//            arrayOfUsers.add(new UserModel("Tom","LA City", R.drawable.azerbaijan));
//            adapter.notifyDataSetChanged();
            Intent intent = new Intent(getActivity(),
                    MainActivity5.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
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
        super.onResume();
        adapter.clear();
        arrayOfContacts.addAll(contactHelper.getContact11());
        arrayOfContactsCopy.addAll(contactHelper.getContact11());
        adapter.notifyDataSetChanged();
    }
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
