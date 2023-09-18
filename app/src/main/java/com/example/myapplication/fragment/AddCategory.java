package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;

@SuppressLint("MissingInflatedId")
public class AddCategory extends Fragment {

    private DatabaseHelper databaseHelper;
     private ImageButton back_arrow;
     private TextWatcher text;
//     private int holat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_category,container,false);

        back_arrow = view.findViewById(R.id.cat_back);

        Button btn_add = view.findViewById(R.id.add_category);
         Button btn_demo = view.findViewById(R.id.add_category_demo);
        EditText edit_name = view.findViewById(R.id.edit_category_name);
//        holat = getArguments().getInt("holat");

//        Toast.makeText(getActivity(),holat+"",Toast.LENGTH_SHORT).show();

        text = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(edit_name.getText().length() > 0){
                    btn_add.setVisibility(View.VISIBLE);
                    btn_demo.setVisibility(View.GONE);

                }
                else if (edit_name.getText().length() == 0) {
                    btn_add.setVisibility(View.GONE);
                    btn_demo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };


        edit_name.addTextChangedListener(text);

//        edit_name.setOnKeyListener((v, keyCode, event) -> {
//
//
//            return true;
//
//        });

        back_arrow.setOnClickListener(v -> {
            CategoryActivity userFragment = new CategoryActivity();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.category_container, userFragment);
            transaction.commit();
        });

        databaseHelper = new DatabaseHelper(getActivity());

        btn_add.setOnClickListener(v -> {

//            holat = getArguments().getInt("holat");

            databaseHelper.insertCategory(edit_name.getText()+"");

            CategoryActivity categoryActivity = new CategoryActivity();
//            Bundle bundle = new Bundle();
//            bundle.putInt("holat",holat);
//            categoryActivity.setArguments(bundle);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.category_container, categoryActivity);
            transaction.commit();
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {

                CategoryActivity categoryActivity = new CategoryActivity();
//                Bundle bundle = new Bundle();
//                bundle.putInt("holat",holat);
//                categoryActivity.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.category_container, categoryActivity);
                transaction.commit();


            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);


        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
