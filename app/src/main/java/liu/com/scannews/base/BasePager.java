package liu.com.scannews.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import liu.com.scannews.R;

/**
 * 五个标签页的基类
 *
 * @author Kevin
 * @date 2015-8-10
 */
public abstract class BasePager {

    public Activity mActivity;

    public View mRootView;

    public TextView tvTitle;
    public ImageButton btnMenu;
    public FrameLayout flContent;

    public BasePager(Activity activity){

        mActivity=activity;
        initView();
    }

    public void initView(){

        mRootView=View.inflate(mActivity, R.layout.base_pager,null);
        //标题栏
        tvTitle= (TextView) mRootView.findViewById(R.id.tv_title);

        //菜单
        btnMenu= (ImageButton) mRootView.findViewById(R.id.btn_menu);

        //中间的内容
        flContent= (FrameLayout) mRootView.findViewById(R.id.fl_content);
    }

    public abstract  void initData();
}
