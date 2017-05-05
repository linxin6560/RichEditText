package com.levylin.richedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RichEditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add_image_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.addImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493975269090&di=66147b5d4252a4db217cefa9d3225564&imgtype=0&src=http%3A%2F%2Fh5.86.cc%2Fwalls%2F20141217%2F1440x900_23b73a6904487a6.jpg");
            }
        });

        mEditText = (RichEditText) findViewById(R.id.rich_edt);
    }
}
