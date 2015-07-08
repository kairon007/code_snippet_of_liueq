package com.example.liueq.testactiontransition;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liueq.testactiontransition.helper.ItemTouchHelperAdapter;
import com.example.liueq.testactiontransition.helper.ItemTouchHelperViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by liueq on 7/7/15.
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    public final String TAG = "RecyclerListAdapter";

    private Context mContext;
    private List<String> mList = new ArrayList<String>();
    private OnStartDragListener mOnStartDragListener;

    public RecyclerListAdapter(Context context, List<String> list, OnStartDragListener listener){
        this.mList.addAll(list);
        mContext = context;
        mOnStartDragListener = listener;
    }

    public interface OnStartDragListener{
        void onStartDrag(RecyclerView.ViewHolder viewHolder);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        viewHolder.mTextView.setText(mList.get(i));
        /*手动设置监听会覆盖掉默认的长按和滑动事件*/
//        viewHolder.mTextView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if(MotionEvent.ACTION_DOWN == MotionEventCompat.getActionMasked(event)){
//                    mOnStartDragListener.onStartDrag(viewHolder);
//                }
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 当Item移动的时候，交换list中的位置，必须要notify
     * @param fromPosition The start position of the moved item.
     * @param toPosition   Then resolved position of the moved item.
     *
     */
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 当Item滑动消失的时候，从list中移除，同样必须要notify
     * @param position The position of the item dismissed.
     *
     */
    @Override
    public void onItemDismiss(int position) {
        Log.i(TAG, "onItemDismiss position is " + position);
        mList.remove(position);
        notifyItemRemoved(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{

        public final TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);

        }

        /**
         * 根据状态变化来设定item的视觉效果
         */
        @Override
        public void onItemSelected() {
            mTextView.setBackgroundColor(Color.GRAY);
            mTextView.setTextColor(Color.WHITE);
        }

        @Override
        public void onItemClear() {
            mTextView.setTextColor(Color.BLACK);
            mTextView.setBackgroundColor(0);
        }
    }
}
