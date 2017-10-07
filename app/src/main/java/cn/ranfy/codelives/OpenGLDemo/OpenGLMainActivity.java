package cn.ranfy.codelives.OpenGLDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.ranfy.codelives.OpenGLDemo.Shader.ShaderActivity;
import cn.ranfy.codelives.OpenGLDemo.Triangle.OpenGLDemoActivity;
import cn.ranfy.codelives.R;

public class OpenGLMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_glmain);
    }

    public void triangle(View view) {
        startActivity(new Intent(this,OpenGLDemoActivity.class));
    }

    public void shader(View view) {
        startActivity(new Intent(this,ShaderActivity.class));
    }
}
