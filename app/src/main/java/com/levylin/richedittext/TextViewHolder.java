package com.levylin.richedittext;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 文字
 * Created by LinXin on 2017/8/29.
 */
class TextViewHolder extends RecyclerView.ViewHolder implements RichAdapter.IRichViewHolder {

    private RichAdapter.Listener mListener;
    private TextWatchEditText mEditText;
    private TextView mTextView;
    private RichItem richItem;

    TextViewHolder(View itemView, RichAdapter.Listener listener) {
        super(itemView);
        this.mEditText = (TextWatchEditText) itemView.findViewById(R.id.rich_item_text_edt);
        this.mTextView = (TextView) itemView.findViewById(R.id.rich_item_text_tv);
        this.mListener = listener;
    }

    @Override
    public void bind(final RichItem item) {
        richItem = item;
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText.hasFocus() && mListener != null) {
                    mListener.onSaveSelection(item, mEditText.getSelectionStart(), mEditText.getSelectionEnd());
                }
            }
        });
        mEditText.setOnTextChangedListener(new TextWatchEditText.OnTextChangedListener() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setContent(s);
            }
        });
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                    int startSelection = mEditText.getSelectionStart();
                    // 只有在光标已经顶到文本输入框的最前方，在判定是否删除之前的图片，或两个View合并
                    if (startSelection == 0 && mListener != null) {
                        mListener.onPressDelAtFirst(getAdapterPosition());
                    }
                }
                return false;
            }
        });
        mEditText.setText(item.getContent());
        mTextView.setText(item.getContent());

        if (mListener != null) {
            SelectionInfo info = mListener.getSelectionInfo();
            if (item.equals(info.focusItem)) {
                mEditText.requestFocus();
                mEditText.setSelection(info.start, info.end);
            }
        }
    }

    RichItem getRichItem() {
        return richItem;
    }

    TextWatchEditText getEditText() {
        return mEditText;
    }

    @Override
    public void setIsStartDragged(boolean isStartDragged) {
        if (isStartDragged) {
            mEditText.setVisibility(View.GONE);
            Editable editable = mEditText.getText();
            if (!TextUtils.isEmpty(editable)) {
                mTextView.setVisibility(View.VISIBLE);
                mTextView.setText(editable);
            } else {
                mTextView.setVisibility(View.GONE);
            }
        } else {
            mTextView.setVisibility(View.GONE);
            mEditText.setVisibility(View.VISIBLE);
        }
    }
}
