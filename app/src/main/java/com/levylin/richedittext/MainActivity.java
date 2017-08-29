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
                mEditText.addImage(RichEditText.TEST_IMAGE);
            }
        });

        mEditText = (RichEditText) findViewById(R.id.rich_edt);
    }
}
