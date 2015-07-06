package com.example.liueq.testcircleindicator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator;
import me.relex.circleindicator.CircleIndicatorNoAnime;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPager viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
        List<View> viewList = new ArrayList<View>();

        for(int i = 0 ;i < 5; i++){
            TextView textView = new TextView(this);
            textView.setBackgroundColor(randonColor());
            viewList.add(textView);
        }

//        MyPagerAdapter pagerAdapter = new MyPagerAdapter(viewList, this);
        MyPagerAdapter pagerAdapter2 = new MyPagerAdapter(viewList, this);

//        viewPager.setAdapter(pagerAdapter);
        viewPager2.setAdapter(pagerAdapter2);

//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        CircleIndicatorNoAnime indicatorNoAnime = (CircleIndicatorNoAnime) findViewById(R.id.indicator2);
//        indicator.setViewPager(viewPager);
        indicatorNoAnime.setViewPager(viewPager2);
    }

    private int randonColor(){
        int [] colors = {Color.YELLOW, Color.GREEN, Color.BLUE, Color.RED, Color.GRAY, Color.WHITE, Color.CYAN, Color.MAGENTA};

        Random r = new Random();
        return colors[r.nextInt(4)];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
