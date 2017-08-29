package com.levylin.richedittext;

/**
 * Created by LinXin on 2017/8/28.
 */

class RichItem {
    static final int TYPE_TEXT = 1;
    static final int TYPE_IMAGE = 2;

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

    @Override
    public String toString() {
        return "RichItem{" +
                "itemType=" + itemType +
                ", content=" + content +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public static RichItem makeEmpty() {
        return new RichItem();
    }
}
