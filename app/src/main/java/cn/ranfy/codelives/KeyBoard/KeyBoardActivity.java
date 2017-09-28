package cn.ranfy.codelives.KeyBoard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.ranfy.codelives.R;

public class KeyBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_board);
    }

    public void hideKeyBoard(View view) {
        startActivity(new Intent(this,HideKeyBoardActivity.class));
    }

    public void FlowInKeyBoard(View view) {
        new InputDialog(this,R.style.inputdialog).show();
    }
}
