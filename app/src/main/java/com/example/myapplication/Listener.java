package com.example.myapplication;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.User;

public interface Listener {
    void onCLick(int c_id);
    void onDelete(int catId);
    void onUpdate(int id, String name);
}
