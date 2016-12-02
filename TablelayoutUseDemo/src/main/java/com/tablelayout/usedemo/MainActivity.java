package com.tablelayout.usedemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tablelayout.usedemo.fragment.SimpleFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * TableLayout 的一般使用
     */
    private TabLayout   mTableLayout;
    private ViewPager   mViewPage;
    private ArrayList<Fragment>  mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTableLayout=(TabLayout)findViewById(R.id.tablelayout);
        mViewPage=(ViewPager)findViewById(R.id.viewpage);
        mViewPage.setAdapter(new MyViewPageAdapter(getSupportFragmentManager()));
        mTableLayout.setupWithViewPager(mViewPage);
    }

    private   class  MyViewPageAdapter  extends  FragmentPagerAdapter{
        String titles[]={"title1","title2","title3"};

        public MyViewPageAdapter(FragmentManager fm) {
            super(fm);
            mFragments=new ArrayList<Fragment>();
            mFragments.add(SimpleFragment.newInstance(titles[0]));
            mFragments.add(SimpleFragment.newInstance(titles[1]));
            mFragments.add(SimpleFragment.newInstance(titles[2]));
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
