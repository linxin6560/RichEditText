package com.levylin.richedittext;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.List;

/**
 * 富文本适配器
 * Created by LinXin on 2017/8/28.
 */
class RichAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView mRecyclerView;
    private List<RichItem> mRichItemList;
    private boolean isStartDragged = false;
    private Listener mListener;

    RichAdapter(List<RichItem> list, Listener listener) {
        this.mRichItemList = list;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) parent;
        }
        switch (viewType) {
            case RichItem.TYPE_TEXT: {
                View view = inflater.inflate(R.layout.rich_item_text, parent, false);
                return new TextViewHolder(view, mListener);
            }
            case RichItem.TYPE_IMAGE: {
                View view = inflater.inflate(R.layout.rich_item_image, parent, false);
                return new ImageViewHolder(view, mListener);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RichItem item = mRichItemList.get(position);
        if (mListener != null) {
            mListener.onBind(holder);
        }
        if (holder instanceof IRichViewHolder) {
            IRichViewHolder richViewHolder = (IRichViewHolder) holder;
            richViewHolder.bind(item);
            richViewHolder.setIsStartDragged(isStartDragged);
        }
    }

    public void setIsStartDragged(boolean startDragged) {
        isStartDragged = startDragged;
    }

    @Override
    public int getItemCount() {
        return mRichItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mRichItemList.get(position).getItemType();
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        if (holder instanceof TextViewHolder) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            EditText editText = textViewHolder.getEditText();
            if (editText.hasFocus()) {
                mListener.onSaveSelection(textViewHolder.getRichItem(), editText.getSelectionStart(), editText.getSelectionEnd());
            }
        }
    }

    interface IRichViewHolder {
        void bind(RichItem item);

        void setIsStartDragged(boolean isStartDragged);
    }

    interface Listener {

        //由点击EditText产生焦点发生变化
        void onSaveSelection(RichItem currentItem, int start, int end);

        //光标在第一位时点击删除按钮
        void onPressDelAtFirst(int position);

        //开始拖动
        void startDrag(RecyclerView.ViewHolder viewHolder);

        //获取光标位置
        SelectionInfo getSelectionInfo();

        //绑定ViewHolder，让外部保存ViewHolder
        void onBind(RecyclerView.ViewHolder viewHolder);
    }
}