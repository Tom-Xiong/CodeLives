package cn.ranfy.codelives;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.ranfy.codelives.KeyBoard.KeyBoardActivity;
import cn.ranfy.codelives.OpenGLDemo.OpenGLMainActivity;
import cn.ranfy.codelives.UtilsGroup.UtilsActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * to utilsActivity
     *
     * @param view
     */
    public void utils(View view) {
        startActivity(new Intent(this, UtilsActivity.class));
    }

    public void keyBoard(View view) {
        startActivity(new Intent(this,KeyBoardActivity.class));
    }

    public void opengl(View view) {
        startActivity(new Intent(this, OpenGLMainActivity.class));
    }
}
