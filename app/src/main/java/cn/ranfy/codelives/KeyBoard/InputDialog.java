package cn.ranfy.codelives.KeyBoard;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import cn.ranfy.codelives.R;

/**
 * Created by ransom on 2017/9/28 11:11.
 */

public class InputDialog extends Dialog {

    private BackEditText input;
    private Context context;

    public InputDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public InputDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected InputDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_input);

        setCancelable(true);
        setCanceledOnTouchOutside(true);

        //全屏处理
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setAttributes(lp);

        initView();
    }

    private void initView() {

        input = (BackEditText) findViewById(R.id.edit_input);

        // press back keyword
        input.setCallBack(new BackEditText.PressBackCallBack() {
            @Override
            public void callBack() {
                dismiss();
            }
        });

        //make sure that if edit has focus,keyboard will be activated;
        input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//使得点击 Dialog 中的EditText 可以弹出键盘
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);//总是显示键盘
                }
            }
        });
    }


    /**
     * if dialog is dismiss and keyboard will be dismiss too.
     */
    @Override
    public void dismiss() {
        View view = getCurrentFocus();
        if (view instanceof BackEditText) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
        super.dismiss();
    }
}
