package liu.com.scannews.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import liu.com.scannews.MainActivity;
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

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggle();
            }
        });
    }

    /**
     * 侧边栏展开或者收起的方法
     */
    protected  void toggle(){

        MainActivity mainUI= (MainActivity) mActivity;
        SlidingMenu slidingMenu=mainUI.getSlidingMenu();
        slidingMenu.toggle();

    }

    public abstract  void initData();
}
