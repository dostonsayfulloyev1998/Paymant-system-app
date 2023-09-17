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

          holder.id.setText(list.get(position).getId()+"");
          holder.name.setText(list.get(position).getName().trim());
          holder.summa.setText(list.get(position).getSumma()+" so`m");
          holder.sana.setText(list.get(position).getSana()+"");

        char c = d.getName().trim().charAt(0);

        holder.logo.setText((c+"").toUpperCase());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                longListener.longListenerUser(list.get(position).getId(), list.get(position).getName(), list.get(position).getSumma());
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

