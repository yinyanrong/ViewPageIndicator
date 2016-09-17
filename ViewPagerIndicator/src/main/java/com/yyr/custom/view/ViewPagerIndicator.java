package com.yyr.custom.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yyr.custom.R;

import java.util.HashMap;
import java.util.List;


/**
 * Created by My on 2016/9/7.
 */
public class ViewPagerIndicator extends LinearLayout {
    String  TAG=ViewPagerIndicator.class.getSimpleName();
    private int mIndicatorColor;//指示器的颜色
    private int mItemVisibleCount;//tab的个数
    private int DEFAULT_TAB_COUNT = 4;//默认的tab的个数
    private int DEFAULT_INDICATOR_COLOR = 0xff0000FF;//默认的indicator的颜色
    private int DEFAULT_TABCHECK_COLOR = 0xffff6600;
    private int DEFAULT_TABUNCHECK_COLOR = 0xff000000;
    private  int mTabCheckColor;
    private  int mTabUnCheckColor;

    private int mHeight = 5; // 指示符的高度，固定了
    private int mTop;//整个Tab的高度
    private int mWidth;//Indicator的宽
    private Paint mPaint;//Indicator的画笔
    private int mChildCount;//XML中的tab个数
    private int mLeft; // 指示符的left
    private HashMap<Boolean,TextView>  viewMap=new HashMap<Boolean, TextView>();
    private  ViewPager mViewPager;


    private OnIndicatorChangeListener mIndicatorListener;
    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray tA = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, 0, 0);
        mIndicatorColor = tA.getColor(R.styleable.ViewPagerIndicator_indicatorColor, DEFAULT_INDICATOR_COLOR);
        mItemVisibleCount = tA.getInt(R.styleable.ViewPagerIndicator_columnSun, DEFAULT_TAB_COUNT);
        mTabCheckColor = tA.getInt(R.styleable.ViewPagerIndicator_tabCheckColor, DEFAULT_TABCHECK_COLOR);
        mTabUnCheckColor = tA.getInt(R.styleable.ViewPagerIndicator_tabUnCheckColor, DEFAULT_TABUNCHECK_COLOR);
        tA.recycle();
        //初始化画笔
        mPaint = new Paint();
        mPaint.setColor(mIndicatorColor);
        mPaint.setAntiAlias(true);//抗锯齿设置
        mWidth=getScreenWidth()/mItemVisibleCount;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTop = getMeasuredHeight(); // 测量的高度即指示符的顶部位置
        int width = getMeasuredWidth(); // 获取测量的总宽度
        int height = mTop + mHeight; // 重新定义一下测量的高度
        setMeasuredDimension(width, height);
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        Rect rect = new Rect(mLeft, mTop, mWidth + mLeft, mTop + mHeight);
        canvas.drawRect(rect, mPaint);
        super.dispatchDraw(canvas);
    }

    public  void setViewPager(ViewPager mViewPager){
        this.mViewPager=mViewPager;
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                scroll(position,positionOffset);
                if(null!=mIndicatorListener){
                    mIndicatorListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
                }
            }
            @Override
            public void onPageSelected(int position) {
                Log.e("TEST","onPageSelected");
               TextView  textView= viewMap.get(true);
                //第一次的时候为null
                if(textView!=null){
                    textView.setTextColor(mTabUnCheckColor);

                }
                TextView  textView1=(TextView) getChildAt(position);
                textView1.setTextColor(mTabCheckColor);
                viewMap.put(true,textView1);
                if(null!=mIndicatorListener){
                    mIndicatorListener.onPageSelected(position);

                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                if(null!=mIndicatorListener) {
                    mIndicatorListener.onPageScrollStateChanged(state);
                }
            }
        });

    }
    public void   setTabTitle(List<String> tabList){
        mChildCount=tabList.size();
        for(int  i =0;i<tabList.size();i++){
            final int j=i;
            TextView textView=  generateTextView(tabList.get(i));
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
            addView(textView);
        }
    }
    public void  setCurrent(int  currentPage){
        mViewPager.setCurrentItem(currentPage);
        viewMap.put(true,(TextView) getChildAt(currentPage));
    }

    public  TextView  generateTextView(String text){
        TextView textView=new TextView(getContext());
        LinearLayout.LayoutParams  layoutParams=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        layoutParams.width=mWidth;
        textView.setText(text);
        textView.setTextColor(mTabUnCheckColor);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        return  textView;
    }
    /**
     * 指示符滚动 和Linearlayout的位置
     * @param position 现在的位置
     * @param offset   偏移量 0 ~ 1
     */
    public void scroll(int position, float offset) {
        /**
         * 当item的个数大于  屏幕可见的个数的  并且 当前的posion >= 倒数第二个table的时候
         * 触发
         */
        if(mChildCount> mItemVisibleCount && position>=(mItemVisibleCount-2)){

            //移动到倒数第二个的时候让Linearlayout 停止移动
            if(position>=mChildCount-2){

            }else{
                this.scrollTo((position-(mItemVisibleCount-2))*mWidth+(int)(mWidth*offset),0);
            }
        }
        mLeft = (int) ((position + offset) * mWidth);
        invalidate();
    }
    public int getScreenWidth() {
        WindowManager windowManager=(WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics  displayMetrics=new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    };
    public interface  OnIndicatorChangeListener{
        public abstract void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
        public abstract  void onPageSelected(int position);
        public abstract  void onPageScrollStateChanged(int state);
    }
    public  void addIndicatorChangeListener(OnIndicatorChangeListener mIndicatorListener){
        this.mIndicatorListener=mIndicatorListener;
    }
}

