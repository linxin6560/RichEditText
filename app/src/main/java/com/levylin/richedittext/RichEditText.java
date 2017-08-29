package com.levylin.richedittext;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 富文本编辑器
 * Created by LinXin on 2017/5/5.
 */
public class RichEditText extends RecyclerView implements RichAdapter.Listener {

    public static final String TEST_IMAGE = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1230357351,92223899&fm=11&gp=0.jpg";

    private MyItemTouchHelper mTouchHelper;
    private List<RichItem> mItems;
    private RichAdapter mAdapter;
    private List<ViewHolder> savedViewHolders;
    private SelectionInfo mSelectionInfo;

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
        mAdapter = new RichAdapter(mItems, this);
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
        mSelectionInfo = new SelectionInfo();
    }

    private void setIsStartDragged(boolean isStartDragged) {
        for (ViewHolder holder : savedViewHolders) {
            if (holder instanceof RichAdapter.IRichViewHolder) {
                ((RichAdapter.IRichViewHolder) holder).setIsStartDragged(isStartDragged);
            }
        }
    }

    private void test() {
        RichItem item = new RichItem();
        item.setContent("1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl(TEST_IMAGE);
        mItems.add(item);

        item = new RichItem();
        item.setContent("2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl(TEST_IMAGE);
        mItems.add(item);

        item = new RichItem();
        item.setContent("3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl(TEST_IMAGE);
        mItems.add(item);

        item = new RichItem();
        item.setContent("4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl(TEST_IMAGE);
        mItems.add(item);

        item = new RichItem();
        item.setContent("5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl(TEST_IMAGE);
        mItems.add(item);

        item = new RichItem();
        item.setContent("6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666");
        mItems.add(item);

        item = new RichItem();
        item.setImageUrl(TEST_IMAGE);
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
        int selection = mSelectionInfo.end;
        int imagePosition;
        //EditText获取到焦点的对象
        RichItem focusedItem = mSelectionInfo.focusItem;
        int position = mItems.indexOf(focusedItem);
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
        if (lastItem.getItemType() == RichItem.TYPE_IMAGE) {
            mItems.add(RichItem.makeEmpty());
        }
    }

    @Override
    public void onSaveSelection(RichItem currentItem, int start, int end) {
        mSelectionInfo.focusItem = currentItem;
        mSelectionInfo.start = start;
        mSelectionInfo.end = end;
    }

    @Override
    public void onPressDelAtFirst(int position) {
        if (position != 0) {
            RichItem item = mItems.get(position);
            View preView = getLayoutManager().findViewByPosition(position - 1);
            RecyclerView.ViewHolder preHolder = getChildViewHolder(preView);
            RichItem focusItem;
            int selection;
            if (preHolder instanceof ImageViewHolder) {
                mItems.remove(position - 1);
                mAdapter.notifyDataSetChanged();
                focusItem = item;
                selection = 0;
            } else {
                RichItem preItem = mItems.get(position - 1);
                CharSequence content = preItem.getContent();
                preItem.setContent(content.toString() + item.getContent().toString());
                mItems.remove(position);
                mAdapter.notifyDataSetChanged();
                focusItem = preItem;
                selection = content.length();
            }
            mSelectionInfo.focusItem = focusItem;
            mSelectionInfo.start = selection;
            mSelectionInfo.end = selection;
        }
    }

    @Override
    public void startDrag(ViewHolder viewHolder) {
        mTouchHelper.startDrag(viewHolder);
    }

    @Override
    public SelectionInfo getSelectionInfo() {
        return mSelectionInfo;
    }

    @Override
    public void onBind(ViewHolder viewHolder) {
        savedViewHolders.add(viewHolder);
    }
}
