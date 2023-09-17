package com.example.myapplication.helper;

import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.CategoryAdapter;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private  ItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int drapDirs, int swipeDirs, ItemTouchHelperListener listener) {
        super(drapDirs,swipeDirs);
        this.listener = listener;

    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if (listener!=null){
            listener.onSwiped(viewHolder);
       }
    }

    @Override
    public void onSelectedChanged (RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null){
            final View foregroundview = ((CategoryAdapter.ViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onSelected(foregroundview);
        }
    }

    @Override
    public void onChildDrawOver( Canvas c,  RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foreground = ((CategoryAdapter.ViewHolder) viewHolder).view_foreground;
        getDefaultUIUtil().onDrawOver(c,recyclerView,foreground,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void onChildDraw(Canvas c,  RecyclerView recyclerView,  RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        final View foreground = ((CategoryAdapter.ViewHolder) viewHolder).view_foreground;
            getDefaultUIUtil().onDraw(c,recyclerView,foreground,dX,dY,actionState,isCurrentlyActive);
    }

    @Override
    public void clearView( RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foreground = ((CategoryAdapter.ViewHolder)viewHolder).view_foreground;
        getDefaultUIUtil().clearView(foreground);
    }

}
