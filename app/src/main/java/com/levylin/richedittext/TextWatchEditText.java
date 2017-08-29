package com.levylin.richedittext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 副本文文字View
 * Created by LinXin on 2017/5/5.
 */
@SuppressLint("AppCompatCustomView")
public class TextWatchEditText extends EditText implements TextWatcher {

    private OnTextChangedListener mTextChangedListener;

    public TextWatchEditText(Context context) {
        super(context);
        init(context);
    }

    public TextWatchEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TextWatchEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (mTextChangedListener != null) {
            mTextChangedListener.onTextChanged(s, start, before, count);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public void setOnTextChangedListener(OnTextChangedListener listener) {
        this.mTextChangedListener = listener;
    }

    interface OnTextChangedListener {
        void onTextChanged(CharSequence s, int start, int before, int count);
    }
}
