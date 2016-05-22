package liu.com.scannews;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import liu.com.scannews.base.baseimp.ContentFragment;
import liu.com.scannews.base.baseimp.LeftMenuFragment;

public class MainActivity extends SlidingFragmentActivity{

    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT = "TAG_CONTENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        //添加侧边栏
        setBehindContentView(R.layout.left_menu);
        SlidingMenu slidingMenu=getSlidingMenu();

        //全屏触摸
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //屏幕预留200像素
        slidingMenu.setBehindOffset(200);

        intiFragment();


    }

    private void intiFragment() {

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();//开始事务
        transaction.replace(R.id.fl_content,new ContentFragment(),TAG_CONTENT);
        transaction.replace(R.id.fl_left_menu,new LeftMenuFragment(),TAG_LEFT_MENU);
        transaction.commit();//提交事务
    }
}
