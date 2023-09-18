package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.fragment.AddCategory;
import com.example.myapplication.fragment.CategoryActivity;
import com.example.myapplication.fragment.MainFragment;
import com.example.myapplication.helper.ItemTouchHelperListener;
import com.example.myapplication.helper.RecyclerItemTouchHelper;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("MissingInflatedId")
public class MainActivity extends AppCompatActivity {

//    private List<Category> list;
//    private List<User> userList;
//    private RecyclerView recyclerView;
//    private CategoryAdapter adapter;
//    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        databaseHelper = new DatabaseHelper(this);

//        recyclerView  = findViewById(R.id.res_cat);
//        laodData();
//        initView();
//
//        userList = databaseHelper.getUsers();
//
//
//        adapter = new CategoryAdapter(list, userList,this);
//        recyclerView.setAdapter(adapter);

        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.category_container, new CategoryActivity());
        transaction.commit();
    }

//    private void initView() {
//
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//
//        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT, this);
//        new ItemTouchHelper(itemTouchHelper);
//
//
//    }
//    private void laodData() {
//
//        list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            list.add(new Category("catefory item_ "+i));
//        }
//    }
//
//
//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
//
//    }


}