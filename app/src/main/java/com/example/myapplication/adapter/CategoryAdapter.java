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
import com.example.myapplication.model.LongListener;
import com.example.myapplication.model.User;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> list;
    private List<User> userList;
    private Context context;
    private Listener listener;

    private LongListener longListener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public CategoryAdapter(List<Category> categories, List<User> userList, Context context) {
        this.list = categories;
        this.userList = userList;
        this.context = context;
    }

    public void setLongListener(LongListener longListener) {
        this.longListener = longListener;
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

        int a=0,b=0;

        if(sum>=1000 && sum < 1000000){
            a = sum/1000;

            StringBuilder input1 = new StringBuilder("");
            StringBuilder input2 = new StringBuilder("");

            input1.append(sum+"");
            input1.reverse();
            String s = input1.substring(0,3);

            input2.append(s).reverse();

            holder.summa.setText("Jami: "+a+"."+input2+" so`m");
        }else if (sum<1000){
            holder.summa.setText("Jami: "+sum+" so`m");
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

            holder.summa.setText("Jami: "+a+"."+input3+"."+input2+" so`m");
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

            holder.summa.setText("Jami: "+a+"."+input4+"."+input3+"."+input2+" so`m");
        }

        if (list!=null){
            holder.name.setText(list.get(i).getName().trim());
            char c = list.get(i).getName().trim().charAt(0);
//            holder.logo.setText((c+"").toUpperCase());

            holder.id.setText(list.get(i).getId()+"");
//            holder.holat.setText(list.get(i).getHolat()+"");
        }


        holder.itemView.setOnClickListener(v ->
                listener.onCLick(Integer.parseInt(holder.id.getText()+""))
        );

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                longListener.longListener(list.get(i).getId(),list.get(i).getName());
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
       if (list!=null)
        return list.size();

       else
           return 0;
    }

    public void removeItem(int pos){
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    public void undoItem( Category category,int pos){
        list.add(pos,category);
        notifyItemInserted(pos);
    }

 public void refrresh(){
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,id,summa,logo,holat;
        ImageView edit,delete;
        View view;
       public RelativeLayout view_background,view_foreground;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            view_background = view.findViewById(R.id.background);
            view_foreground = view.findViewById(R.id.foreground);
            name = itemView.findViewById(R.id.name);

            delete = itemView.findViewById(R.id.cat_delete);

            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.cat_id);
            summa = itemView.findViewById(R.id.all_sum_item);
            holat = itemView.findViewById(R.id.holat);
        }
    }
}
