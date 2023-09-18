package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Listener;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.helper.ItemTouchHelperListener;
import com.example.myapplication.helper.RecyclerItemTouchHelper;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.LongListener;
import com.example.myapplication.model.User;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("MissingInflatedId")
public class CategoryActivity extends Fragment implements ItemTouchHelperListener,Listener, LongListener {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private List<Category> list;
    private List<Category> list1;
    private List<User> user_list;
    private DatabaseHelper databaseHelper;
    private TextView all_sum;
    private CategoryAdapter adapter;
    private ImageButton cat_back;
    FragmentActivity fragmentActivity;
    private ConstraintLayout root_view;
    private BottomSheetDialog bottomSheetDialog;
//    private int holat=0;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category,container,false);

        recyclerView = view.findViewById(R.id.cat_res);
        fab = view.findViewById(R.id.fab_cat);
        all_sum = view.findViewById(R.id.all_sum);
        cat_back = view.findViewById(R.id.cat_back);
        root_view = view.findViewById(R.id.root_category);

//        holat = getArguments().getInt("holat",0);

//        Toast.makeText(getActivity(),holat+"",Toast.LENGTH_SHORT).show();
        loadData();

        fab.setOnClickListener(v -> {
//            holat = getArguments().getInt("holat",0);

            AddCategory category = new AddCategory();
//            Bundle bundle = new Bundle();
//            bundle.putInt("holat",holat);
//            category.setArguments(bundle);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.category_container, category).addToBackStack(null);
            transaction.commit();
        });


        cat_back.setOnClickListener(v -> {

            MainFragment mainFragment = new MainFragment();
//            Bundle bundle = new Bundle();
//            bundle.putInt("holat",holat);
//            mainFragment.setArguments(bundle);
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.category_container, mainFragment).addToBackStack(null);
//            transaction.commit();

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.category_container, new MainFragment()).addToBackStack(null);
                transaction.commit();

            }
        };
//        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), callback);
        return view;
    }

    private void loadData() {

        databaseHelper = new DatabaseHelper(getActivity());
        user_list = databaseHelper.getUsers();
        list = databaseHelper.getCategory();

//        for (Category category : list1){
//               if (category.getHolat()==holat){
//                   list.add(category);
//               }
//        }

        adapter = new CategoryAdapter(list,user_list,getActivity());
        adapter.setListener(this);
        adapter.setLongListener(this);

        initView();


        recyclerView.setAdapter(adapter);

        int sum=0;
        if(list!=null){

        for (int i = 0; i < list.size(); i++) {
            int c_id = list.get(i).getId();

            for(User user:user_list){
                if (c_id == user.getC_id()){
                    sum+=user.getSumma();
                }
            }
        }


        int a=0,b=0;

        if(sum>=1000 && sum < 1000000){
            a = sum/1000;

            StringBuilder input1 = new StringBuilder("");
            StringBuilder input2 = new StringBuilder("");

            input1.append(sum+"");
            input1.reverse();
            String s = input1.substring(0,3);

            input2.append(s).reverse();

            all_sum.setText("Jami: "+a+"."+input2+" so`m");
        }else if (sum<1000){
            all_sum.setText("Jami: "+sum+" so`m");
        }else if(sum>1000000 && sum < 1000000000){
            a = sum/1000000;

            StringBuilder input1 = new StringBuilder("");
            StringBuilder input2 = new StringBuilder("");
            StringBuilder input3 = new StringBuilder("");

            input1.append(sum+"");
            input1.reverse();
            String s = input1.substring(0,3);
            String s1 = input1.substring(3,6);

            input2.append(s).reverse();
            input3.append(s1).reverse();

            all_sum.setText("Jami: "+a+"."+input3+"."+input2+" so`m");
        } else if (sum>1000000000) {
            a = sum / 1000000000;

            StringBuilder input1 = new StringBuilder("");
            StringBuilder input2 = new StringBuilder("");
            StringBuilder input3 = new StringBuilder("");
            StringBuilder input4 = new StringBuilder("");

            input1.append(sum + "");
            input1.reverse();
            String s = input1.substring(0, 3);
            String s1 = input1.substring(3, 6);
            String s2 = input1.substring(6, 9);

            input2.append(s).reverse();
            input3.append(s1).reverse();
            input4.append(s2).reverse();


            all_sum.setText("Jami: " + a + "." + input4 + "." + input3 + "." + input2 + " so`m");
        }else {
            all_sum.setText("Jami: "+sum+" so`m");
        }
        }
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
//                bundle.putInt("holat",holat);
                userActivity.setArguments(bundle);

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.category_container, userActivity).addToBackStack(null);
        transaction.commit();
    }

    private void initView(){

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        ItemTouchHelper.SimpleCallback itemTouchHelper = new RecyclerItemTouchHelper(0,ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof  CategoryAdapter.ViewHolder){
            String name = list.get(viewHolder.getAdapterPosition()).getName();

            Category category = list.get(viewHolder.getAdapterPosition());
            int index = viewHolder.getAdapterPosition();

            databaseHelper.deleteCategory(category.getId());
            adapter.removeItem(index);

            int sum=0;



            if(list!=null) {

                for (int i = 0; i < list.size(); i++) {
                    int c_id = list.get(i).getId();

                    for (User user : user_list) {
                        if (c_id == user.getC_id()  ) {
                            sum += user.getSumma();
                        }
                    }
                }
            }
            for (int i = 0; i < list.size(); i++) {
                int c_id = list.get(i).getId();

                for(User user:user_list){
                    if (c_id == user.getC_id()){
                        sum+=user.getSumma();
                    }
                }
            }

            int a=0,b=0;

            if(sum>=1000 && sum < 1000000){
                a = sum/1000;

                StringBuilder input1 = new StringBuilder("");
                StringBuilder input2 = new StringBuilder("");

                input1.append(sum+"");
                input1.reverse();
                String s = input1.substring(0,3);

                input2.append(s).reverse();

                all_sum.setText("Jami: "+a+"."+input2+" so`m");
            }else if (sum<1000){
                all_sum.setText("Jami: "+sum+" so`m");
            }else if(sum>1000000 && sum < 1000000000){
                a = sum/1000000;

                StringBuilder input1 = new StringBuilder("");
                StringBuilder input2 = new StringBuilder("");
                StringBuilder input3 = new StringBuilder("");

                input1.append(sum+"");
                input1.reverse();
                String s = input1.substring(0,3);
                String s1 = input1.substring(3,6);

                input2.append(s).reverse();
                input3.append(s1).reverse();

                all_sum.setText("Jami: "+a+"."+input3+"."+input2+" so`m");
            } else if (sum>1000000000) {
                a = sum/1000000000;

                StringBuilder input1 = new StringBuilder("");
                StringBuilder input2 = new StringBuilder("");
                StringBuilder input3 = new StringBuilder("");
                StringBuilder input4 = new StringBuilder("");

                input1.append(sum+"");
                input1.reverse();
                String s = input1.substring(0,3);
                String s1 = input1.substring(3,6);
                String s2 = input1.substring(6,9);

                input2.append(s).reverse();
                input3.append(s1).reverse();
                input4.append(s2).reverse();

                all_sum.setText("Jami: "+a+"."+input4+"."+input3+"."+input2+" so`m");
            }


//            all_sum.setText("Jami: "+sum+" so`m");


          Snackbar snackbar = Snackbar.make(root_view, name+" o'chirildi! ",Snackbar.LENGTH_LONG);
          snackbar.setAction("BEKOR QILISH ", new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  databaseHelper.insertCategory(category.getId(),category.getName());
                    adapter.undoItem(category,index);
              }
          });

          snackbar.setActionTextColor(Color.YELLOW);
          snackbar.show();
        }
    }



    @Override
    public void longListener(int id,String name) {
         bottomSheetDialog = new BottomSheetDialog(getActivity());

        View view = getLayoutInflater().inflate(R.layout.bottom_dialog_category, null,false);
        Button button = view.findViewById(R.id.bottom_cat_btn);
        EditText edit_name = view.findViewById(R.id.bottom_cat_name);
        edit_name.setText(name);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateCategory(id,edit_name.getText()+"");
                bottomSheetDialog.dismiss();
                loadData();
            }
        });
    }

}



