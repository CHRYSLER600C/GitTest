package com.example.test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.test.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.tvTest1)
    TextView mTvTest1;
    @BindView(R.id.tvTest2)
    TextView mTvTest2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        mTvTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().register(SecondActivity.this);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onSecondActivityEvent(MainActivity.TestEvent2 event) {
        mTvTest1.setText(event.getMsg());
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
