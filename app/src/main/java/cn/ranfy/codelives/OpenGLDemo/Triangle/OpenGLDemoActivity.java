package cn.ranfy.codelives.OpenGLDemo.Triangle;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.ranfy.codelives.OpenGLDemo.OpenGLUtils;
import cn.ranfy.codelives.R;

public class OpenGLDemoActivity extends AppCompatActivity {

    private GLSurfaceView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (OpenGLUtils.checkSupported(this)) {
            setView();
        } else {
            setContentView(R.layout.activity_open_gldemo);
        }
    }

    /**
     * generate view
     */
    public void setView(){
        if(view == null){
            view = new GLSurfaceView(this);
            view.setRenderer(new GLRender());
            setContentView(view);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(view!=null){
            view.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(view !=null){
            view.onResume();
        }else{
            setView();
        }
    }
}
