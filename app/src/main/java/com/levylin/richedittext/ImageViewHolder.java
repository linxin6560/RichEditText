package com.levylin.richedittext;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 图片
 * Created by LinXin on 2017/8/29.
 */
class ImageViewHolder extends RecyclerView.ViewHolder implements RichAdapter.IRichViewHolder {

    private RichAdapter.Listener mListener;
    private SimpleDraweeView mDraweeView;

    ImageViewHolder(View itemView, RichAdapter.Listener listener) {
        super(itemView);
        mDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.rich_item_image_imv);
        mDraweeView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setIsStartDragged(true);
                if (mListener != null) {
                    mListener.startDrag(ImageViewHolder.this);
                }
                return true;
            }
        });
        this.mListener = listener;
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
