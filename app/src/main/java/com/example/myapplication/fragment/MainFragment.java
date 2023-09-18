package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("MissingInflatedId")
public class MainFragment extends Fragment {

    private CardView card,card1;
    private TextView text_sum1,text_sum2;
    private   int HOLAT_1 = 1;
    private   int HOLAT_2 = 2;
    private DatabaseHelper databaseHelper;
    private List<User> list;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment,container,false);

        card = view.findViewById(R.id.card);
        card1 = view.findViewById(R.id.card1);
        text_sum1 = view.findViewById(R.id.summa);
        text_sum2 = view.findViewById(R.id.summa1);
        int sum1=0,sum2=0;

        databaseHelper = new DatabaseHelper(getActivity());
        list =  databaseHelper.getUsers();

        for (User user : list){
            if (user.getHolat()==1){
                sum1+=user.getSumma();
            }
            else if (user.getHolat()==2) {
               sum2+=user.getSumma();
            }
        }

        text_sum1.setText(sum1+"");
        text_sum2.setText(sum2+"");

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryActivity categoryActivity = new CategoryActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("holat",HOLAT_1);

                categoryActivity.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.category_container, categoryActivity).addToBackStack(null);
                transaction.commit();
            }
        });

         card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryActivity categoryActivity = new CategoryActivity();

                Bundle bundle = new Bundle();
                bundle.putInt("holat",HOLAT_2);
                categoryActivity.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.category_container, categoryActivity).addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
