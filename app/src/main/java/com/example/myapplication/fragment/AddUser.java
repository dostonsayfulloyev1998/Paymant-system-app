package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;

@SuppressLint("MissingInflatedId")
public class AddUser extends Fragment {

    private DatabaseHelper databaseHelper;
    private ImageButton back_arrow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(getActivity());
        View view = inflater.inflate(R.layout.add_user,container,false);
        EditText edit_name = view.findViewById(R.id.edit_user_name);
        EditText edit_summa = view.findViewById(R.id.edit_user_summa);
        back_arrow  =view.findViewById(R.id.user_back);

        int c_id = getArguments().getInt("c_id");

        Button btn_add = view.findViewById(R.id.add_user);
        Button btn_demo = view.findViewById(R.id.add_user_demo);

        edit_summa.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((edit_name.getText()+"").trim().length() >0 && (edit_summa.getText()+"").trim().length()>0){
                    btn_add.setVisibility(View.VISIBLE);
                    btn_demo.setVisibility(View.GONE);
                }else {
                    btn_add.setVisibility(View.GONE);
                    btn_demo.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        edit_name.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((edit_name.getText()+"").trim().length() >0 && (edit_summa.getText()+"").trim().length()>0){
                    btn_add.setVisibility(View.VISIBLE);
                    btn_demo.setVisibility(View.GONE);
                }else {
                    btn_add.setVisibility(View.GONE);
                    btn_demo.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        btn_add.setOnClickListener(v -> {

            String name = edit_name.getText()+"";
            String summa = edit_summa.getText()+"";


               if (name.length()>0 && summa.length()>0){
                     boolean t=   databaseHelper.insertUser(name, Integer.parseInt(summa+""),1,c_id);
                     if (t){
                         Bundle bundle = new Bundle();
                         bundle.putInt("c_id", c_id );
                         UserActivity userFragment = new UserActivity();
                         userFragment.setArguments(bundle);

                         FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                         transaction.replace(R.id.category_container, userFragment).addToBackStack(null);
                         transaction.commit();
                     }
                     else {
                         Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
                     }
               }

               edit_name.setText("");
               edit_summa.setText("");

        });


        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Bundle bundle = new Bundle();
                bundle.putInt("c_id", c_id );
                UserActivity userFragment = new UserActivity();
                userFragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.category_container, userFragment).addToBackStack(null);
                transaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);

        back_arrow.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("c_id", c_id );
            UserActivity userFragment = new UserActivity();
            userFragment.setArguments(bundle);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.category_container, userFragment).addToBackStack(null);
            transaction.commit();
        });

        return view;
    }


}
