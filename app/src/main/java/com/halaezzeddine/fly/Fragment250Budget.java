package com.halaezzeddine.fly;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment250Budget#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment250Budget extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button book1;
    Button book2;
    private long id;
    private flyHelper dbHelper;

    public Fragment250Budget() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment250Budget.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment250Budget newInstance(String param1, String param2) {
        Fragment250Budget fragment = new Fragment250Budget();
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

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment250_budget, container, false);

        book1 = v.findViewById(R.id.button);
        book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form form = new Form("Beirut",
                        "Antalya", "10 2 2022",
                        "10 5 2022", "Economy");



                try{
                    //getData();
                    id=dbHelper.addForm(form);

                }
                catch (Exception ex){

                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
}