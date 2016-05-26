package liu.com.scannews.base.baseimp.menudetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

import liu.com.scannews.MainActivity;
import liu.com.scannews.R;
import liu.com.scannews.base.BaseMenuDetailPager;
import liu.com.scannews.domain.NewsMenuData;


/**
 * 菜单详情页-新闻,就是项部的内个 分类
 *
 * ViewPagerIndicator使用流程: 1. 引入Library库 2. 布局文件中配置TabPageIndicator 3.
 * 将指针和Viewpager关联起来 4. 重写getPageTitle方法,返回每个页面的标题(PagerAdapter) 5.
 * 设置activity主题样式 6. 修改源码中的样式(修改图片, 文字颜色)
 *
 * @author Kevin
 * @date 2015-8-11
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager implements
        ViewPager.OnPageChangeListener {

    @ViewInject(R.id.vp_news_detail)
    private ViewPager mViewPager;
    @ViewInject(R.id.indicator)
    private TabPageIndicator mIndicator;

    private ArrayList<NewsMenuData.NewsTabData> mTabList;// 页签网络数据集合
    private ArrayList<TabDetailPager> mTabPagers;// 页签页面集合

    public NewsMenuDetailPager(Activity activity,
                               ArrayList<NewsMenuData.NewsTabData> children) {
        super(activity);
        mTabList = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.pager_menu_detail_news,
                null);
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        // 初始化12个页签
        mTabPagers = new ArrayList<TabDetailPager>();
        for (NewsMenuData.NewsTabData tabData : mTabList) {
            // 创建一个页签对象
            TabDetailPager pager = new TabDetailPager(mActivity, tabData);
            mTabPagers.add(pager);
        }

        mViewPager.setAdapter(new NewsMenuAdapter());
        // mViewPager.setOnPageChangeListener(this);

        // 此方法在viewpager设置完数据之后再调用
        mIndicator.setViewPager(mViewPager);// 将页面指示器和ViewPager关联起来
        mIndicator.setOnPageChangeListener(this);// 当viewpager和指针绑定时,需要将页面切换监听设置给指针
    }

    //adapter
    class NewsMenuAdapter extends PagerAdapter {
        // 返回页面指示器的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabList.get(position).title;
        }

        @Override
        public int getCount() {
            return mTabPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager pager = mTabPagers.get(position);
            container.addView(pager.mRootView);
            pager.initData();// 初始化数据
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    //  继承ViewPager.OnPageChangeListener后复写的方法
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        System.out.println("position:" + position);
        if (position == 0) {// 在第一个页签,允许侧边栏出现
            // 开启侧边栏
            setSlidingMenuEnable(true);
        } else {// 其他页签,禁用侧边栏, 保证viewpager可以正常向右滑动
            // 关闭侧边栏
            setSlidingMenuEnable(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 设置侧边栏可用不可用
     *
     * @param enable
     */
    private void setSlidingMenuEnable(boolean enable) {
        MainActivity mainUI = (MainActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();

        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            // 禁用掉侧边栏滑动效果
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }

    @OnClick(R.id.iv_next_page)
    public void nextPage(View view) {
        int currentItem = mViewPager.getCurrentItem();
        currentItem++;
        mViewPager.setCurrentItem(currentItem);
    }

}
