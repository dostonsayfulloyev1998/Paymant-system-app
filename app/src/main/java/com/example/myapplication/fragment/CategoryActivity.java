package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Listener;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.helper.RecyclerItemTouchHelper;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class CategoryActivity extends Fragment implements Listener{

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private List<Category> list;
    private List<User> user_list;
    private DatabaseHelper databaseHelper;
    private TextView all_sum;
    private CategoryAdapter adapter;
    private ImageButton cat_back;
    FragmentActivity fragmentActivity;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category,container,false);

        recyclerView = view.findViewById(R.id.cat_res);
        fab = view.findViewById(R.id.fab_cat);
        all_sum = view.findViewById(R.id.all_sum);
        cat_back = view.findViewById(R.id.cat_back);

        databaseHelper = new DatabaseHelper(getActivity());
        list = databaseHelper.getCategory();
        user_list = databaseHelper.getUsers();

        adapter = new CategoryAdapter(list,user_list,getActivity());

        adapter.setListener(this);

        initView();

        recyclerView.setAdapter(adapter);
        int sum=0;
        for(User user:user_list){
            sum+=user.getSumma();
        }

        all_sum.setText("Jami: "+sum+" so`m");

        fab.setOnClickListener(v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.category_container, new AddCategory());
            transaction.commit();
        });

        cat_back.setOnClickListener(v -> {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        fragmentActivity = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onCLick(int c_id) {

                UserActivity userActivity = new UserActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("c_id",c_id);
                userActivity.setArguments(bundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.category_container, userActivity).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onDelete(int catId) {

    }

    @Override
    public void onUpdate(int id, String name) {

    }

    private void initView(){

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, new RecyclerItemTouchHelper.RecyclerItemTouchHelperrListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

            }
        });
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);
    }


}



