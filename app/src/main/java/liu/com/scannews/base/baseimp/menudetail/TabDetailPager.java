package liu.com.scannews.base.baseimp.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import liu.com.scannews.base.BaseMenuDetailPager;
import liu.com.scannews.domain.NewsMenuData;

/**
 * Created by Administrator on 2016/5/23.
 */
public class TabDetailPager extends BaseMenuDetailPager{

    private NewsMenuData.NewsTabData mTabData;
    private TextView mTextView;

    public TabDetailPager(Activity activity, NewsMenuData.NewsTabData tabData) {
        super(activity);

        mTabData=tabData;
    }

    @Override
    protected View initView() {
        TextView view=new TextView(mActivity);
        //view.setText("菜单详情页-互动");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);
        return view;
    }
}
