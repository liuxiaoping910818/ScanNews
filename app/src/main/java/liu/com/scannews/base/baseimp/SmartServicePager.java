package liu.com.scannews.base.baseimp;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import liu.com.scannews.base.BasePager;

/**
 * 智慧服务
 * Created by Administrator on 2016/5/21.
 */
public class SmartServicePager  extends BasePager{
    public SmartServicePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {

        tvTitle.setText("智慧服务");

        TextView view = new TextView(mActivity);
        view.setText("智慧服务");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        flContent.addView(view);
    }
}
