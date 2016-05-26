package liu.com.scannews.base;

import android.app.Activity;
import android.view.View;

/**侧边栏菜单详情页基类
 * Created by Administrator on 2016/5/23.
 */
public abstract class BaseMenuDetailPager {

    public Activity mActivity;

    public View mRootView;
    //菜单详情页根布局
    public BaseMenuDetailPager(Activity activity){

        mActivity=activity;
        mRootView=initView();
    }

    protected abstract View initView();

    public void initData(){

    }
}
