package com.levylin.richedittext;

/**
 * 光标位置信息
 * Created by LinXin on 2017/8/28.
 */
class SelectionInfo {
    RichItem focusItem;//光标对应的EditText在adapter的位置
    int start;//光标所在的位置
    int end;
}
