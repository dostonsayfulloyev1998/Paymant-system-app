package com.example.myapplication;

public interface ListenerUser {
    void onDelete(int userId);

    void onUpdate(int id, String name, int summa);
}
