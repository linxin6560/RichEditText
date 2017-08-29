package com.levylin.richedittext;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 拖动帮助类
 * Created by huangyuxiang on 2017/4/26.
 */
class MyItemTouchHelper extends ItemTouchHelper {
    MyItemTouchHelper(MyItemTouchHelperCallback.OnMoveListener onMoveListener) {
        super(new MyItemTouchHelperCallback(onMoveListener));
    }
}
