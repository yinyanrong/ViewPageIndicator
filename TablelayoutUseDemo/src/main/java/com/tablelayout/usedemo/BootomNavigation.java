package com.tablelayout.usedemo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.tablelayout.usedemo.fragment.SimpleFragment;
import com.tablelayout.usedemo.utils.LogUtils;

import java.util.ArrayList;

public class BootomNavigation extends AppCompatActivity {
    private TabLayout   mTableLayout;
    private ViewPager   mViewPage;
    private ArrayList<Fragment> mFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bootom_navigation);
        mTableLayout=(TabLayout)findViewById(R.id.tablelayout);
        mViewPage=(ViewPager)findViewById(R.id.viewpage);
        mViewPage.setAdapter(new MyViewPageAdapter(getSupportFragmentManager()));
        mTableLayout.setupWithViewPager(mViewPage);
        /**
         * 此处注意是 mTableLayout.getTabCount()
         *    而不是  mTableLayout.getChildCount()
         */
        int childCount=mTableLayout.getTabCount();

        LogUtils.e("childCount:"+childCount);
        /**
         *    因为setupWithViewPager 会把mTableLayout 中的Tab删掉
         *    获取tab的个数重新把 Tab  设置新的值
         */
        for (int i=0;i<childCount;i++){
            TabLayout.Tab tab=mTableLayout.getTabAt(i);
            tab.setCustomView( createTabView());
//            createTabView();
//            tab.setText("新title"+i);
//            tab.setIcon(R.mipmap.ic_launcher);
        }
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
    }
    private  View  createTabView(){
      View view  =  LayoutInflater.from(this).inflate(R.layout.bootom_tab_layout,null,false);
        return  view;
    }
    private   class  MyViewPageAdapter  extends FragmentPagerAdapter {
        String titles[]={"fragment1","fragment2","fragment3"};

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

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titles[position];
//        }
    }
}
