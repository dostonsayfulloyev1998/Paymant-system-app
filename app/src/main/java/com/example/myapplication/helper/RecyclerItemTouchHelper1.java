package com.example.myapplication.helper;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.UserAdapter;

public class RecyclerItemTouchHelper1 extends ItemTouchHelper.SimpleCallback {

    private  RecyclerItemTouchHelperrListener listener;

    public RecyclerItemTouchHelper1(int drapDirs, int swipeDirs, RecyclerItemTouchHelperrListener listener) {
        super(drapDirs,swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder,direction,viewHolder.getAdapterPosition());
    }

    @Override
    public void onSelectedChanged (RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null){
        final View foregroundview = ((UserAdapter.ViewHolder) viewHolder).view_foreground;
        getDefaultUIUtil().onSelected(foregroundview);
    }
}

    @Override
    public void onChildDrawOver( Canvas c,  RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foreground = ((UserAdapter.ViewHolder) viewHolder).view_foreground;
        getDefaultUIUtil().onDrawOver(c,recyclerView,foreground,dX/3,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDraw(Canvas c,  RecyclerView recyclerView,  RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foreground = ((UserAdapter.ViewHolder) viewHolder).view_foreground;

            getDefaultUIUtil().onDraw(c,recyclerView,foreground,dX/3,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void clearView( RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foreground = ((UserAdapter.ViewHolder)viewHolder).view_foreground;
        getDefaultUIUtil().clearView(foreground);
    }


    public interface  RecyclerItemTouchHelperrListener{
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
    }
}
