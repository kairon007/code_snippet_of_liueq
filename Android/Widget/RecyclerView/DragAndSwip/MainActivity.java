/**
 * Title: RecyclerView
 * Tag: RecyclerView, list, grid
 * Update: 2015/07/28
 * Description: 带有drag和swipe的recyclerView
 */


package com.example.liueq.testactiontransition;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.liueq.testactiontransition.helper.SimpleItemTouchHelperCallback;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements RecyclerListAdapter.OnStartDragListener{

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	//创建普通的RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

	//实现了ItemTouchHelperAdapter的RecyclerView.Adapter	
        String [] strs = {"Hello", "My", "name", "is", "liueq", "how", "are", "you", "today"};
        List<String> list = Arrays.asList(strs);
        RecyclerListAdapter adapter = new RecyclerListAdapter(this, list, this);

        recyclerView.setAdapter(adapter);

        /* 开始滑动和移动
	 * SimpleItemTouchHelper定义了实际的逻辑
	 * 然而将其应用到ViewHolder上则是通过ItemTouchHelperAdapter 和ItemTouchHelperViewHolder来通知
	 */
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }


    /**
     * 创建Grid形式的Recycler，和List形式的大同小异，区别只是需要禁用swipe，并且drag是4个方向
     */
    private void createGridRecyclerView(){
	RecyclerView recyclerView = new RecyclerView(this);
	recyclerView.setHasFixedSize(true);
	recyclerView.setAdapter(adapter);
	final int spanCount = 2;//Grid的列数
	final GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount);
	recyclerView.setLayoutManager(layoutManager);

	ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
	mItemTouchHelper =  new ItemTouchHelper(callback);
	mItemTouchHelper.attachToRecyclerView(recyclerView);
    }


    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
