package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.helper.ItemTouchHelperListener;
import com.example.myapplication.helper.RecyclerItemTouchHelper;
import com.example.myapplication.helper.RecyclerItemTouchHelper1;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.LongListener;
import com.example.myapplication.model.LongListenerUser;
import com.example.myapplication.model.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("MissingInflatedId")
public class UserActivity extends Fragment implements ListenerUser, ItemTouchHelperListener, LongListenerUser {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private UserAdapter adapter;
    private List<User> list = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    private TextView all_sum_user;
    private int c_id=1;
    private ImageButton back_arrow;
    FragmentActivity fragmentActivity;
    private ConstraintLayout root_view;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.user_activity,container,false);

        recyclerView = view.findViewById(R.id.user_res);
        back_arrow = view.findViewById(R.id.user_back_activity);
        fab = view.findViewById(R.id.fab_user);
        all_sum_user = view.findViewById(R.id.all_sum_user);
        root_view = view.findViewById(R.id.root_user);

       loadData();


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

    void loadData(){
        databaseHelper = new DatabaseHelper(getActivity());
        c_id = getArguments().getInt("c_id");

        int sum = 0;
        for (User user: databaseHelper.getUsers()){
            if (user.getC_id()==c_id){
                list.add(user);
                sum+=user.getSumma();
            }
        }

        all_sum_user.setText("Jami: " +sum+" so'm");

        adapter = new UserAdapter(getActivity(),list);
        adapter.setListener(this);
        adapter.setLongListener(this);
        initView();

        recyclerView.setAdapter(adapter);

    }

    private void initView(){

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecyclerItemTouchHelper1(0,ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        fragmentActivity = (FragmentActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDelete(int userId) {
//        Toast.makeText(getActivity(),"clik "+userId,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpdate(int id, String name, int summa) {
//        Toast.makeText(getActivity(),"clik "+name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof  UserAdapter.ViewHolder){
            String nameDelete = list.get(viewHolder.getAdapterPosition()).getName();

            User user = list.get(viewHolder.getAdapterPosition());
            int index = viewHolder.getAdapterPosition();

            databaseHelper.deleteUser(user.getId());
            adapter.removeItem(index);

            int sum = 0;
            for (User user1: databaseHelper.getUsers()){
                if (user1.getC_id()==c_id){
                    sum+=user1.getSumma();
                }
            }

            all_sum_user.setText("Jami: " +sum+" so'm");

            Snackbar snackbar = Snackbar.make(root_view, nameDelete+" o'chirildi! ",Snackbar.LENGTH_LONG);

            snackbar.setAction("BEKOR QILISH ", v -> {

                databaseHelper.insertUser(user.getId(),user.getName(), user.getSumma(), user.getHolat(),user.getC_id());
                adapter.undoItem(user,index);
            });

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }



    @Override
    public void longListenerUser(int id, String name, int sum) {
     BottomSheetDialog   bottomSheetDialog = new BottomSheetDialog(getActivity());

        View view = getLayoutInflater().inflate(R.layout.bottom_dialog_user, null,false);
         Button button = view.findViewById(R.id.bottom_user_btn);
        EditText edit_name = view.findViewById(R.id.bottom_user_name);
        EditText edit_summa = view.findViewById(R.id.bottom_user_summa);

        edit_name.setText(name);
        edit_summa.setText(sum+"");

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateUser(id,edit_name.getText()+"", Integer.parseInt(edit_summa.getText()+""));
                loadData();
                bottomSheetDialog.dismiss();
            }
        });
    }
}


