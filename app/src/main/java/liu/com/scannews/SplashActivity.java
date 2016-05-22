package liu.com.scannews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import liu.com.scannews.utils.PrefUtils;

public class SplashActivity extends Activity{

    private RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        // 旋转, 缩放, 渐变
        // 旋转
        RotateAnimation animRotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animRotate.setDuration(1000);
        animRotate.setFillAfter(true);

        // 缩放
        ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        animScale.setDuration(1000);
        animScale.setFillAfter(true);

        // 渐变
        AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);

        // 动画集合
        AnimationSet animSet = new AnimationSet(true);
        animSet.addAnimation(animRotate);
        animSet.addAnimation(animScale);
        animSet.addAnimation(animAlpha);

        rlRoot.startAnimation(animSet);

        animSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 判断是否需要跳到新手引导
                boolean isGuideShow = PrefUtils.getBoolean("is_guide_show",
                        false, getApplicationContext());

                if (isGuideShow) {
                    // 动画结束后跳主页面
                    startActivity(new Intent(getApplicationContext(),
                            MainActivity.class));
                } else {
                    // 跳到新手引导
                    startActivity(new Intent(getApplicationContext(),
                            GuideActivity.class));
                }

                finish();
            }
        });
    }
}
