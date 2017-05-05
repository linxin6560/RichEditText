package com.levylin.richedittext;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 富文本编辑器
 * Created by LinXin on 2017/5/5.
 */
public class RichEditText extends RecyclerView {

    private static final int TYPE_TEXT = 1;
    private static final int TYPE_IMAGE = 2;

    private MyItemTouchHelper mTouchHelper;
    private List<RichItem> mItems;
    private RichAdapter mAdapter;
    private List<ViewHolder> savedViewHolders;
    private SelectionInfo selectionInfo;

    public RichEditText(Context context) {
        super(context);
        init(context);
    }

    public RichEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RichEditText(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mItems = new ArrayList<>();

        test();

        savedViewHolders = new ArrayList<>();

        setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new RichAdapter(mItems);
        setAdapter(mAdapter);

        mTouchHelper = new MyItemTouchHelper(new MyItemTouchHelperCallback.OnMoveListener() {
            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                Collections.swap(mItems, srcPosition, targetPosition);
                mAdapter.notifyItemMoved(srcPosition, targetPosition);
                return true;
            }

            @Override
            public void onClearView(RecyclerView recyclerView, ViewHolder viewHolder) {
                setIsStartDragged(false);
                mAdapter.setIsStartDragged(false);
            }
        });
        mTouchHelper.attachToRecyclerView(this);

        setRecyclerListener(new RecyclerListener() {
            @Override
            public void onViewRecycled(ViewHolder holder) {
                savedViewHolders.remove(holder);
            }
        });
        selectionInfo = new SelectionInfo();
    }

    private void getFocusedEditTextPosition() {
        for (ViewHolder holder : savedViewHolders) {
            if (holder instanceof RichAdapter.TextViewHolder) {
                EditText editText = ((RichAdapter.TextViewHolder) holder).getEditText();
                if (editText.isFocused()) {
                    selectionInfo.editTextPosition = holder.getAdapterPosition();
                    selectionInfo.selection = editText.getSelectionEnd();
                    break;
                }
            }
        }
    }

    private void setIsStartDragged(boolean isStartDragged) {
        for (ViewHolder holder : savedViewHolders) {
            if (holder instanceof IRichViewHolder) {
                ((IRichViewHolder) holder).setIsStartDragged(isStartDragged);
            }
        }
    }

    private void test() {
        RichItem item = new RichItem();
        item.setContent("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493975269090&di=66147b5d4252a4db217cefa9d3225564&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20141217%2F1440x900_23b73a6904487a6.jpg");
        mItems.add(item);

        item = new RichItem();
        item.setContent("2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493975269090&di=66147b5d4252a4db217cefa9d3225564&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20141217%2F1440x900_23b73a6904487a6.jpg");
        mItems.add(item);

        item = new RichItem();
        item.setContent("3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493975269090&di=66147b5d4252a4db217cefa9d3225564&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20141217%2F1440x900_23b73a6904487a6.jpg");
        mItems.add(item);

        item = new RichItem();
        item.setContent("4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493975269090&di=66147b5d4252a4db217cefa9d3225564&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20141217%2F1440x900_23b73a6904487a6.jpg");
        mItems.add(item);

        item = new RichItem();
        item.setContent("5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493975269090&di=66147b5d4252a4db217cefa9d3225564&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20141217%2F1440x900_23b73a6904487a6.jpg");
        mItems.add(item);

        item = new RichItem();
        item.setContent("6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493975269090&di=66147b5d4252a4db217cefa9d3225564&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20141217%2F1440x900_23b73a6904487a6.jpg");
        mItems.add(item);

        item = new RichItem();
        item.setContent("7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777");
        mItems.add(item);
    }

    /**
     * 添加图片
     *
     * @param imageFile
     */
    public void addImage(String imageFile) {

        getFocusedEditTextPosition();
        int position = selectionInfo.editTextPosition;
        int selection = selectionInfo.selection;
        int imagePosition;
        //EditText获取到焦点的对象
        RichItem focusedItem = mItems.get(position);
        CharSequence focusedContent = focusedItem.getContent();

        System.out.println("selection=" + selection + ",focusedContent.length()=" + focusedContent.length());
        if (selection == 0) {//光标在头部
            System.out.println("光标在头部");
            imagePosition = position;
        } else if (selection == focusedContent.length()) {//光标在尾部
            System.out.println("光标在尾部");
            imagePosition = position + 1;
        } else {//光标在中间
            System.out.println("光标在中间");
            imagePosition = position + 1;
            //根据光标的位置拆分选中的内容
            CharSequence newContent = focusedContent.subSequence(0, selection);
            focusedItem.setContent(newContent);
            //添加拆分出来的内容到下面一项
            CharSequence splitContent = focusedContent.subSequence(selection, focusedContent.length());
            RichItem splitItem = new RichItem();
            splitItem.setContent(splitContent);
            mItems.add(position + 1, splitItem);
        }
        selectionInfo.imagePosition = imagePosition;
        //添加图片
        RichItem item = new RichItem();
        item.setImageUrl(imageFile);
        mItems.add(imagePosition, item);
        ensureImageIsNotAtLast();//确保图片不是在最后一项
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 确保图片不在最后一项，因为需要有光标，如果如果图片在最后一项，则需要添加一个空的item
     */
    private void ensureImageIsNotAtLast() {
        RichItem lastItem = mItems.get(mItems.size() - 1);
        if (lastItem.getItemType() == TYPE_IMAGE) {
            mItems.add(RichItem.makeEmpty());
        }
    }

    /**
     * 光标位置信息
     */
    private static class SelectionInfo {
        int editTextPosition;//光标对应的EditText在adapter的位置
        int selection;//光标所在的位置
        int imagePosition;//图片所在的位置
    }

    private static class RichItem {

        private int itemType = TYPE_TEXT;
        private CharSequence content;
        private String imageUrl;

        public void setContent(CharSequence content) {
            this.content = content;
            itemType = TYPE_TEXT;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            itemType = TYPE_IMAGE;
        }

        public CharSequence getContent() {
            return content;
        }

        public int getItemType() {
            return itemType;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public static RichItem makeEmpty() {
            return new RichItem();
        }
    }

    private class RichAdapter extends Adapter<ViewHolder> {

        private List<RichItem> mRichItemList;
        private boolean isStartDragged = false;

        public RichAdapter(List<RichItem> list) {
            this.mRichItemList = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            switch (viewType) {
                case TYPE_TEXT: {
                    View view = inflater.inflate(R.layout.rich_item_text, parent, false);
                    return new TextViewHolder(view);
                }
                case TYPE_IMAGE: {
                    View view = inflater.inflate(R.layout.rich_item_image, parent, false);
                    return new ImageViewHolder(view);
                }
            }
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            savedViewHolders.add(holder);
            RichItem item = mRichItemList.get(position);
            if (holder instanceof IRichViewHolder) {
                ((IRichViewHolder) holder).bind(item);
                ((IRichViewHolder) holder).setIsStartDragged(isStartDragged);
            }
        }

        private void setIsStartDragged(boolean startDragged) {
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

        private class TextViewHolder extends ViewHolder implements IRichViewHolder {

            private TextWatchEditText mEditText;
            private TextView mTextView;

            public TextViewHolder(View itemView) {
                super(itemView);
                mEditText = (TextWatchEditText) itemView.findViewById(R.id.rich_item_text_edt);
                mTextView = (TextView) itemView.findViewById(R.id.rich_item_text_tv);
            }

            @Override
            public void bind(final RichItem item) {
                mEditText.setOnTextChangedListener(new TextWatchEditText.OnTextChangedListener() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        item.setContent(s);
                    }
                });
                mEditText.setText(item.getContent());
                mTextView.setText(item.getContent());

                if (getAdapterPosition() == selectionInfo.imagePosition + 1) {
                    mEditText.requestFocus();
                    mEditText.setSelection(0);
                }
            }

            @Override
            public void setIsStartDragged(boolean isStartDragged) {
                if (isStartDragged) {
                    mEditText.setVisibility(GONE);
                    Editable editable = mEditText.getText();
                    if (!TextUtils.isEmpty(editable)) {
                        mTextView.setVisibility(VISIBLE);
                        mTextView.setText(editable);
                    } else {
                        mTextView.setVisibility(GONE);
                    }
                } else {
                    mTextView.setVisibility(GONE);
                    mEditText.setVisibility(VISIBLE);
                }
            }

            public TextWatchEditText getEditText() {
                return mEditText;
            }
        }

        private class ImageViewHolder extends ViewHolder implements IRichViewHolder {

            private SimpleDraweeView mDraweeView;

            public ImageViewHolder(View itemView) {
                super(itemView);
                mDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.rich_item_image_imv);
                mDraweeView.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mAdapter.setIsStartDragged(true);
                        mTouchHelper.startDrag(ImageViewHolder.this);
                        return true;
                    }
                });
            }

            @Override
            public void bind(RichItem item) {
                mDraweeView.setImageURI(item.getImageUrl());
            }

            @Override
            public void setIsStartDragged(boolean isStartDragged) {
                ViewGroup.LayoutParams lp = mDraweeView.getLayoutParams();
                if (isStartDragged) {
                    lp.height = 200;
                } else {
                    lp.height = 400;
                }
                mDraweeView.setLayoutParams(lp);
            }
        }
    }

    public interface IRichViewHolder {
        void bind(RichItem item);

        void setIsStartDragged(boolean isStartDragged);
    }
}