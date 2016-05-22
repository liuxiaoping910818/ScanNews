package liu.com.scannews.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/5/21.
 */
public abstract class BaseFragment  extends Fragment{
    
    public Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //获取所在的activity对象
        mActivity=getActivity();
    }

    //初始化Fragment布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       
        View view=initView();
        return view;
    }

    
    //Activity创建 结束
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 初始化布局, 子类必须实现
     */
    public abstract View initView();

    /**
     * 初始化数据, 子类可以不实现
     */
    public void initData() {

    }
}
