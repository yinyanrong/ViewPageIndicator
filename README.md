# ViewPageIndicator
自定义的ViewPageIndicator 实现了Indicator 的滑动与viewpage的绑定
#使用方式 #
#Xml中引用#
    <com.yyr.custom.view.ViewPagerIndicator
       android:id="@+id/indicator"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:paddingBottom="10dip"
       android:paddingTop="10dip"
    >
    </com.yyr.custom.view.ViewPagerIndicator>
#代码中使用#
        mIndicator.setViewPager(mViewPage);
        mIndicator.setTabTitle(mTextList,0);
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
