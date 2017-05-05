package com.levylin.richedittext;

import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by huangyuxiang on 2017/4/26.
 */

public class MyItemTouchHelper extends ItemTouchHelper {
    public MyItemTouchHelper(MyItemTouchHelperCallback.OnMoveListener onMoveListener) {
        super(new MyItemTouchHelperCallback(onMoveListener));
    }
}
