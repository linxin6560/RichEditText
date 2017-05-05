package com.levylin.richedittext;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by huangyuxiang on 2017/4/26.
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private OnMoveListener mOnMoveListener;

    public MyItemTouchHelperCallback(OnMoveListener moveListener){
        this.mOnMoveListener = moveListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(mOnMoveListener != null){
            return mOnMoveListener.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //empty
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    public interface OnMoveListener{
        boolean onMove(int srcPosition, int targetPosition);
        void onClearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if(mOnMoveListener != null){
            mOnMoveListener.onClearView(recyclerView, viewHolder);
        }
    }
}
