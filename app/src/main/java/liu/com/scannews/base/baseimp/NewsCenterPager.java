package liu.com.scannews.base.baseimp;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

import liu.com.scannews.MainActivity;
import liu.com.scannews.base.BaseMenuDetailPager;
import liu.com.scannews.base.BasePager;
import liu.com.scannews.base.baseimp.menudetail.InteractMenuDetailPager;
import liu.com.scannews.base.baseimp.menudetail.NewsMenuDetailPager;
import liu.com.scannews.base.baseimp.menudetail.PhotosMenuDetailPager;
import liu.com.scannews.base.baseimp.menudetail.TopicMenuDetailPager;
import liu.com.scannews.domain.NewsMenuData;
import liu.com.scannews.global.Constants;
import liu.com.scannews.utils.CacheUtils;

/**
 * 新闻中心
 *
 * @author Kevin
 * @date 2015-8-10
 */
public class NewsCenterPager extends BasePager {

    // 菜单详情页集合
    private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;
    private NewsMenuData mNewsMenuData;// 新闻分类信息网络数据

    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        System.out.println("新闻中心初始化...");
        tvTitle.setText("新闻");

        // 1.首先先看本地有没有缓存
        // 2.有缓存,直接加载缓存
        String cache = CacheUtils.getCache(Constants.CATEGORIES_URL, mActivity);
        if (!TextUtils.isEmpty(cache)) {
            // 有缓存
            System.out.println("发现缓存....");
            processResult(cache);
        }

        // 即使发现有缓存,仍继续调用网络, 获取最新数据
        getDataFromServer();
    }

    /**
     * 从服务器获取数据 需要权限: <uses-permission
     * android:name="android.permission.INTERNET"/>
     */
    private void getDataFromServer() {
        HttpUtils utils = new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, Constants.CATEGORIES_URL,
                new RequestCallBack<String>() {

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        // 请求成功
                        String result = responseInfo.result;// 获取json字符串
                        // System.out.println("result:" + result);
                        processResult(result);
                        // 写缓存
                        CacheUtils.setCache(Constants.CATEGORIES_URL, result,
                                mActivity);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        // 请求失败
                        error.printStackTrace();
                        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    /**
     * 解析json数据
     *
     * @param result
     */
    protected void processResult(String result) {
        // gson->json
        Gson gson = new Gson();
        mNewsMenuData = gson.fromJson(result, NewsMenuData.class);
        System.out.println("解析结果:" + mNewsMenuData);

        // 获取侧边栏对象
        MainActivity mainUI = (MainActivity) mActivity;
        LeftMenuFragment leftMenuFragment = mainUI.getLeftMenuFragment();
        // 将网络数据设置给侧边栏
        leftMenuFragment.setData(mNewsMenuData.data);

        // 初始化4个菜单详情页
        mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity,
                mNewsMenuData.data.get(0).children));
        mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity));
        mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity));

        // 菜单详情页-新闻作为初始页面
        setCurrentMenuDetailPager(0);
    }

    // 给新闻中心页面的FrameLayout填充布局
    protected void setCurrentMenuDetailPager(int position) {
        BaseMenuDetailPager pager = mMenuDetailPagers.get(position);
        // 移除之前所有的view对象, 清理屏幕
        flContent.removeAllViews();
        flContent.addView(pager.mRootView);
        pager.initData();// 初始化数据

        // 更改标题
        tvTitle.setText(mNewsMenuData.data.get(position).title);
    }

}
