package cn.ranfy.codelives.UtilsGroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.ranfy.codelives.R;
import cn.ranfy.codelives.UtilsGroup.Utils.NetWorkUtils;
import cn.ranfy.codelives.UtilsGroup.Utils.ToastUtils;

public class UtilsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utils);
    }

    /**
     * click method toast in center
     *
     * @param view
     */
    public void toastInCenter(View view) {
        ToastUtils.ToastInCenter("yes i'm in center",this);
    }

    public void isNetWorking(View view) {
        if(NetWorkUtils.isConnectingToInternet(this)){
            ToastUtils.ToastInCenter("net is working",this);
        }else{
            ToastUtils.ToastInCenter("net is not working",this);
        }

    }

    public void isWifiNetworking(View view) {
        if(NetWorkUtils.isWifiConnected(this)){
            ToastUtils.ToastInCenter("wifi is working",this);
        }else{
            ToastUtils.ToastInCenter("wifi is not working",this);
        }
    }
}
