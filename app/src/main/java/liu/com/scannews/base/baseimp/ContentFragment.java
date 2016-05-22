package liu.com.scannews.base.baseimp;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;

import liu.com.scannews.R;
import liu.com.scannews.base.BaseFragment;
import liu.com.scannews.base.BasePager;

/**
 * 主页面Fragment
 *
 * @author Kevin
 * @date 2015-8-10
 */
public class ContentFragment extends BaseFragment {

    @ViewInject(R.id.vp_content)
    private ViewPager mViewPager;

    private ArrayList<BasePager> mPagers;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        ViewUtils.inject(this, view); // 注入view和事件
        // mViewPager = (ViewPager) view.findViewById(R.id.vp_content);
        return view;
    }

    @Override
    public void initData() {
        // 初始化5个标签页面
        mPagers = new ArrayList<BasePager>();
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NewsCenterPager(mActivity));
        mPagers.add(new SmartServicePager(mActivity));
        mPagers.add(new GovAffairsPager(mActivity));
        mPagers.add(new SettingPager(mActivity));

        mViewPager.setAdapter(new ContentAdapter());
    }

    class ContentAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPagers.get(position);
            container.addView(pager.mRootView);// 将页面布局添加到容器中
            pager.initData();//初始化数据
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
