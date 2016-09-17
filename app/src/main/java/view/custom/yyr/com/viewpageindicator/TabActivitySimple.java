package view.custom.yyr.com.viewpageindicator;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class TabActivitySimple extends AppCompatActivity {

    TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_activity_simple);
        mTabLayout =(TabLayout) findViewById(R.id.tablayout);
        mTabLayout.setSelectedTabIndicatorColor(Color.BLACK);
        mTabLayout.setTabTextColors(Color.GRAY,Color.RED);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        ViewPager   viewPager=(ViewPager) findViewById(R.id.viewpage);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(viewPager);
    }
      class  MyFragmentPagerAdapter  extends  FragmentPagerAdapter{
         ArrayList<String> mTextList = new ArrayList<String>();
         ArrayList<Fragment> mFragmentsList =new ArrayList<Fragment>();

          public MyFragmentPagerAdapter(FragmentManager fm) {
              super(fm);
              initDatas();
          }

        @Override
        public Fragment getItem(int position) {
            return mFragmentsList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentsList.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTextList.get(position);//页卡标题
        }
        private void initDatas() {
            for(int i=0;i<14;i++) {
                mTextList.add("测试--->" + i);
                Fragment  fragment=SimpleFragment.newInstance("测试--->" + i);
                mFragmentsList.add(fragment);
            }
        }
    };


}
