package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Listener;
import com.example.myapplication.ListenerUser;
import com.example.myapplication.R;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.LongListener;
import com.example.myapplication.model.LongListenerUser;
import com.example.myapplication.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<User> list;
    private ListenerUser listener;
    private LongListenerUser longListener;

    public UserAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    public void setListener(ListenerUser listener) {
        this.listener = listener;
    }

    public void setLongListener(LongListenerUser longListener) {
        this.longListener = longListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
      User d = list.get(position);

      int sum = list.get(position).getSumma();

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

          holder.id.setText(list.get(position).getId()+"");
          holder.name.setText(list.get(position).getName().trim());
          holder.sana.setText(list.get(position).getSana()+"");

        char c = d.getName().trim().charAt(0);

        holder.logo.setText((c+"").toUpperCase());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                longListener.longListenerUser(list.get(position).getId(), list.get(position).getName(), list.get(position).getSumma(),position);
                return true;
            }
        });
    }



    public void add_user(){
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public void removeItem(int pos){
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    public void undoItem(User user, int pos){
        list.add(pos,user);
        notifyItemInserted(pos);
    }

    public void updateChanged(int i,User user) {
        list.set(i,user);
        notifyItemChanged(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,summa,id,sana,logo;
        ImageButton edit,delete;
      public   RelativeLayout view_background,view_foreground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.user_id);
            name=itemView.findViewById(R.id.user_name);
            summa=itemView.findViewById(R.id.user_summa);
            sana=itemView.findViewById(R.id.user_sana);
            logo=itemView.findViewById(R.id.logo_user);


            delete=itemView.findViewById(R.id.user_delete);


            view_foreground = itemView.findViewById(R.id.foreground);
            view_background = itemView.findViewById(R.id.background);


        }
    }
}

