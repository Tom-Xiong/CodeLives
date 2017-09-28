package cn.ranfy.codelives.UtilsGroup.Utils;

import android.app.Activity;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import cn.ranfy.codelives.MyApplication;

/**
 * Created by Administrator on 2017/9/25.
 */

public class ToastUtils {

    /**
     * show info in the center
     *
     * @param content
     */
    public static void ToastInCenter(final String content, final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Toast toast = Toast.makeText(activity, content, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, 1200);
            }
        });

    }

    public static void ToastBottom(String content){
        Toast.makeText(MyApplication.mInstance,content, Toast.LENGTH_SHORT).show();
    }

}
