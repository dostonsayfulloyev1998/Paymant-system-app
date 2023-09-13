package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Listener;
import com.example.myapplication.R;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.User;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> list;
    private List<User> userList;
    private Context context;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public CategoryAdapter(List<Category> categories, List<User> userList, Context context) {
        this.list = categories;
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        int i =position;
        int c_id =  Integer.parseInt(list.get(i).getId()+"");
        int sum=0;

        for(User user:userList){
            if (user.getC_id()==c_id){
                sum+=user.getSumma();
            }
        }

        holder.name.setText(list.get(i).getName().trim());
        holder.summa.setText(sum+" so`m");

        char c = list.get(i).getName().trim().charAt(0);
        holder.logo.setText((c+"").toUpperCase());


        holder.id.setText(list.get(i).getId()+"");
        holder.itemView.setOnClickListener(v ->
                listener.onCLick(Integer.parseInt(holder.id.getText()+""))
        );



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int pos){
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    public void restoreItem( Category category,int pos){
        list.add(pos,category);
        notifyItemInserted(pos);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,id,summa,logo;
        ImageView edit,delete;
        View view;
       public RelativeLayout view_background,view_foreground;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            view_background = view.findViewById(R.id.background);
            view_foreground = view.findViewById(R.id.foreground);
            name = itemView.findViewById(R.id.name);

            edit = itemView.findViewById(R.id.cat_edit);
            delete = itemView.findViewById(R.id.cat_delete);


            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.cat_id);
            summa = itemView.findViewById(R.id.all_sum_item);
            logo = itemView.findViewById(R.id.logo_category);
        }
    }
}
