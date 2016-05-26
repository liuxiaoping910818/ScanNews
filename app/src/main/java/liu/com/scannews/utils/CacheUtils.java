package liu.com.scannews.utils;

import android.content.Context;

/**
 * Created by Administrator on 2016/5/22.
 */
public class CacheUtils {

    /**
     * 写缓冲
     * @param url
     * @param json
     * @param context
     */
    public static  void setCache(String url, String json, Context context){

        PrefUtils.putString(url,json,context);
    }

    /**
     * 读缓冲
     * @param url
     * @param context
     * @return
     */
    public static String getCache(String url,Context context){

        //如果缓冲定居 文件中，先找md5存不存在，如果 存在，说明有缓冲
        return PrefUtils.getString(url,null,context);
    }


}
