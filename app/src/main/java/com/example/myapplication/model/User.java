package com.example.myapplication.model;

public class User {
    private int id;
    private String name;
    private int summa;
    private int  holat;
    private int c_id;
    private String sana;

    public User(String name, int summa, int holat, int c_id, String sana) {
        this.name = name;
        this.summa = summa;
        this.holat = holat;
        this.c_id = c_id;
        this.sana = sana;
    }

    public User(int id, String name, int summa, int holat, int c_id, String sana) {
        this.id = id;
        this.name = name;
        this.summa = summa;
        this.holat = holat;
        this.c_id = c_id;
        this.sana = sana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }

    public int getHolat() {
        return holat;
    }

    public void setHolat(int holat) {
        this.holat = holat;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getSana() {
        return sana;
    }

    public void setSana(String sana) {
        this.sana = sana;
    }
}
