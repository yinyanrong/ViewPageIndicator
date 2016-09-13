package view.custom.yyr.com.viewpageindicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yyr.custom.view.ViewPagerIndicator;

import java.util.ArrayList;;

public class MainActivity extends AppCompatActivity {
    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPage;
    private ArrayList<String> mTextList = new ArrayList<String>();
    private ArrayList<Fragment>  fragmentsList=new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.indicator);
        mViewPage = (ViewPager) findViewById(R.id.container);
        initDatas();
        mIndicator.setViewPager(mViewPage);
        mIndicator.setTabTitle(mTextList,3);
        mViewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentsList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentsList.size();
            }
        });
        mIndicator.addIndicatorChangeListener(new ViewPagerIndicator.OnIndicatorChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void initDatas() {
        for(int i=0;i<14;i++) {
            mTextList.add("测试--->" + i);
        }
        for (String tx: mTextList){
            Fragment  fragment=SimpleFragment.newInstance(tx);
            fragmentsList.add(fragment);
        }
    }
}
