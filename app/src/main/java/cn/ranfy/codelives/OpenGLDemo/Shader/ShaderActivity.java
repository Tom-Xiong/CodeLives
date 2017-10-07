package cn.ranfy.codelives.OpenGLDemo.Shader;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.ranfy.codelives.OpenGLDemo.OpenGLUtils;
import cn.ranfy.codelives.R;

public class ShaderActivity extends AppCompatActivity {


    private GLSurfaceView glSurfaceView;

    private boolean rendererSet=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(OpenGLUtils.checkSupported(this)){
            this.glSurfaceView = new GLSurfaceView(this);
            this.glSurfaceView.setEGLContextClientVersion(2);

            this.glSurfaceView.setRenderer(new LYJRenderer(this));

            this.rendererSet=true;
            setContentView(glSurfaceView);
        }else{
            setContentView(R.layout.activity_shader);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(this.rendererSet){
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(this.rendererSet){
            glSurfaceView.onResume();
        }
    }
}
