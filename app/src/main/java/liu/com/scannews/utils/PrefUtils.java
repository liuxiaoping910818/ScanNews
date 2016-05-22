package liu.com.scannews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/4/25.
 */
public class PrefUtils {

    public static void putBoolean(String key,boolean value,Context context){

        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);

        sp.edit().putBoolean(key,value).commit();
    }

    public static boolean getBoolean(String key,boolean defValue,Context context){

        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    public static void putString(String key,String value,Context context){

        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    public static String getString(String key,String defValue,Context context){

        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getString(key, defValue);

    }

    public static void putInt(String key,int value,Context context){

        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    public static int getInt(String key,int defValue,Context context){

        SharedPreferences sp=context.getSharedPreferences("config",Context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }
    public static void remove(String key, Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

}
