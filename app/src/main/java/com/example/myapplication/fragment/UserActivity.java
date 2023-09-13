package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Listener;
import com.example.myapplication.ListenerUser;
import com.example.myapplication.R;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.helper.RecyclerItemTouchHelper;
import com.example.myapplication.helper.RecyclerItemTouchHelper1;
import com.example.myapplication.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends Fragment implements ListenerUser {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private UserAdapter adapter;
    private List<User> list = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private TextView all_sum_user;
    private int c_id=1;
    private ImageButton back_arrow;
    FragmentActivity fragmentActivity;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_activity,container,false);

        recyclerView = view.findViewById(R.id.user_res);
        back_arrow = view.findViewById(R.id.user_back_activity);
        fab = view.findViewById(R.id.fab_user);
        all_sum_user = view.findViewById(R.id.all_sum_user);

        databaseHelper = new DatabaseHelper(getActivity());

         c_id = getArguments().getInt("c_id");

         int sum = 0;

         initView();

        for (User user:  databaseHelper.getUsers()){
            if (user.getC_id()==c_id){
                list.add(user);
                sum+=user.getSumma();
            }
        }

        all_sum_user.setText("Jami: " +sum+" so'm");

        adapter = new UserAdapter(getActivity(),list);
        adapter.setListener(this);

        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {

            Bundle bundle = new Bundle();
            bundle.putInt("c_id", c_id );
            AddUser userFragment = new AddUser();
            userFragment.setArguments(bundle);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.category_container, userFragment).addToBackStack(null);
            transaction.commit();
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Bundle bundle = new Bundle();
                CategoryActivity categoryActivityFragment = new CategoryActivity();
                categoryActivityFragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.category_container, categoryActivityFragment).addToBackStack(null);
                transaction.commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);


        back_arrow.setOnClickListener(v -> {
            CategoryActivity categoryActivityFragment = new CategoryActivity();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.category_container, categoryActivityFragment).addToBackStack(null);
            transaction.commit();
        });

        return view;
    }

    private void initView(){

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecyclerItemTouchHelper1(0, ItemTouchHelper.LEFT, new RecyclerItemTouchHelper1.RecyclerItemTouchHelperrListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

            }
        });
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        fragmentActivity = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDelete(int userId) {
        Toast.makeText(getActivity(),"clik "+userId,Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onUpdate(int id, String name, int summa) {
//        Toast.makeText(getActivity(),"clik "+name,Toast.LENGTH_SHORT).show();
    }
}

