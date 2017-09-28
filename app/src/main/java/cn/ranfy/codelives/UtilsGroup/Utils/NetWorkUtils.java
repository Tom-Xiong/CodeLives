package cn.ranfy.codelives.UtilsGroup.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

/**
 * Created by ransom on 2017/9/28 10:11.
 */

public class NetWorkUtils {
        /**
         * 判断是否有网络链接
         * @param context
         * @return
         */
        public static boolean isConnectingToInternet(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //新版本调用方法获取网络状态
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] networks = connectivityManager.getAllNetworks();
                NetworkInfo networkInfo;
                for (Network mNetwork : networks) {
                    networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                    if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        return true;
                    }
                }
            } else {
                //否则调用旧版本方法
                if (connectivityManager != null) {
                    NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                    if (info != null) {
                        for (NetworkInfo anInfo : info) {
                            if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                                Log.d("Network",
                                        "NETWORKNAME: " + anInfo.getTypeName());
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] networks = mConnectivityManager.getAllNetworks();
                NetworkInfo networkInfo;
                for (Network mNetwork : networks) {
                    networkInfo = mConnectivityManager.getNetworkInfo(mNetwork);
                    if (networkInfo.getType()==ConnectivityManager.TYPE_WIFI && networkInfo.getState()==NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
                return false;
            }else{
                NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (mWiFiNetworkInfo != null) {
                    return mWiFiNetworkInfo.getState() == NetworkInfo.State.CONNECTED?true: false;
                }else{
                    return false;
                }
            }

        }
        return false;
    }
}
