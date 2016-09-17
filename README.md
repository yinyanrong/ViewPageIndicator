# ViewPageIndicator
自定义的ViewPageIndicator 实现了Indicator 的滑动与viewpage的绑定

##效果图##
![image](https://github.com/yinyanrong/ViewPageIndicator/blob/master/img/20160912232216543.gif)
#使用方式 #
#Xml中引用#
    <com.yyr.custom.view.ViewPagerIndicator
       android:id="@+id/indicator"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:paddingBottom="10dip"
       android:paddingTop="10dip"
	    yyr:columnSun="6"//当前的所显示的行数
        yyr:tabCheckColor="#ff66ff"//tab选中的颜色
        yyr:tabUnCheckColor="#999999"//tab未选中的颜色
        yyr:indicatorColor="#dd5500"//指示器的颜色
    >
    </com.yyr.custom.view.ViewPagerIndicator>
	
	
	  
	
	
#代码中使用#
        mIndicator.setViewPager(mViewPage);
        mIndicator.setTabTitle(mTextList);//设置tab
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
		
##注意 ：   mIndicator.setCurrent(3); 是在设置adapter之后才起作用 ##
		
##还可以监听 viewpage滑动的状态##
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
		
		
		
		
