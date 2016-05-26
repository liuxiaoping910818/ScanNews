package liu.com.scannews.base.baseimp;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import liu.com.scannews.base.BasePager;

/**
 * 首页
 *
 * @author Kevin
 * @date 2015-8-10
 */
public class HomePager extends BasePager {

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        tvTitle.setText("新闻客户端");
        btnMenu.setVisibility(View.GONE);
        TextView view=new TextView(mActivity);
        view.setText("首页");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);
    }
}
