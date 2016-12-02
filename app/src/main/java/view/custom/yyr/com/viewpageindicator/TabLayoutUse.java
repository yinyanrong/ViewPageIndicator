package view.custom.yyr.com.viewpageindicator;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class TabLayoutUse extends AppCompatActivity {
    TabLayout  tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_use);
        //初始化
        tabLayout =(TabLayout)findViewById(R.id.tablayout);
        ViewPager   viewPager=(ViewPager)findViewById(R.id.viewpage);
        //设置adapter
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
       int  count= tabLayout.getTabCount();
        Log.i("TAG",count+"---count->");
        for (int i=0;i<count;i++){
            TabLayout.Tab tab=tabLayout.getTabAt(i);
            tab.setText("窗前"+i);
            tab.setIcon(R.mipmap.ic_launcher);
        }
    }
    //        tabLayout.addTab(tabLayout.newTab().setText("title1").setIcon(R.mipmap.ic_launcher),0);
//        tabLayout.addTab(tabLayout.newTab().setText("title1").setIcon(R.mipmap.ic_launcher),1);
//        tabLayout.addTab(tabLayout.newTab().setText("title1").setIcon(R.mipmap.ic_launcher),2);
//        tabLayout.addTab(tabLayout.newTab().setText("title1").setIcon(R.mipmap.ic_launcher),3);
    class  MyFragmentPagerAdapter  extends FragmentPagerAdapter {
//        ArrayList<String> mTextList = new ArrayList<String>();
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
        //        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTextList.get(position);//页卡标题
//        }
        private void initDatas() {
            for(int i=0;i<4;i++) {
                Fragment  fragment=SimpleFragment.newInstance("Fragmnet" + i);
                mFragmentsList.add(fragment);
            }
        }
    };
}
